package br.com.magicstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magicstore.dto.DefaultIdStringRequest;
import br.com.magicstore.dto.EnderecoRequest;
import br.com.magicstore.dto.LoginRequest;
import br.com.magicstore.dto.UsuarioRequest;
import br.com.magicstore.dto.UsuarioResetSenhaRequest;
import br.com.magicstore.entity.Endereco;
import br.com.magicstore.entity.FormaPagamentoUsuario;
import br.com.magicstore.entity.ParametrosGerais;
import br.com.magicstore.entity.PermissaoEntity;
import br.com.magicstore.entity.UsuarioEndereco;
import br.com.magicstore.entity.UsuarioEntity;
import br.com.magicstore.exception.DefaultException;
import br.com.magicstore.repository.EnderecoRepository;
import br.com.magicstore.repository.FormaPagamentoUsuarioRepository;
import br.com.magicstore.repository.ParametrosGeraisRepository;
import br.com.magicstore.repository.PermissaoRepository;
import br.com.magicstore.repository.UsuarioEnderecoRepository;
import br.com.magicstore.repository.UsuarioRepository;
import br.com.magicstore.utils.AppUtils;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PermissaoRepository permissaoRepository;

	@Autowired
	private UsuarioEnderecoRepository usuarioEnderecoRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ParametrosGeraisRepository parametrosRepository;
	
	@Autowired
	private FormaPagamentoUsuarioRepository formaPagamentoRepository;
	
	@Autowired
	private EnvioEmailService emailService;

	public void cadastrarUsuario(UsuarioRequest usuario) throws DefaultException {

		if (usuario.getEmail().isEmpty() || usuario.getNome().isEmpty() || usuario.getSenha().isEmpty()
				|| usuario.getCpf().isEmpty() || usuario.getRg().isEmpty()) {
			throw new DefaultException("Todos os campos são obrigatórios!");
		}

		if (!usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {

			UsuarioEntity usuarioEnt = new UsuarioEntity();
			usuarioEnt.setEmail(usuario.getEmail());
			usuarioEnt.setNome(usuario.getNome());
			usuarioEnt.setSenha(AppUtils.encryptPass(usuario.getSenha()));
			usuarioEnt.setCpf(usuario.getCpf());
			usuarioEnt.setRg(usuario.getRg());

			Endereco endereco = new Endereco();
			endereco.setLogradouro(usuario.getLogradouro());
			endereco.setBairro(usuario.getBairro());
			endereco.setCidade(usuario.getCidade());
			endereco.setEstado(usuario.getEstado());
			endereco.setNumero(usuario.getNumero());
			endereco.setCep(usuario.getCep());

			Optional<PermissaoEntity> permissao = permissaoRepository.findByNome("ROLE_CLIENT");
			if (permissao.isPresent()) {
				usuarioEnt.setPermissao(permissao.get());
			}

			UsuarioEntity usuarioSave = usuarioRepository.save(usuarioEnt);
			Endereco enderecoSave = enderecoRepository.save(endereco);

			UsuarioEndereco usuarioEndereco = new UsuarioEndereco();
			usuarioEndereco.setUsuario(usuarioSave);
			usuarioEndereco.setEndereco(enderecoSave);
			usuarioEnderecoRepository.save(usuarioEndereco);

		} else {
			throw new DefaultException("E-mail já cadastrado!");
		}

	}

	public Optional<UsuarioEntity> login(LoginRequest login, String idSessao) {

		Optional<UsuarioEntity> usuario = usuarioRepository.findByEmailAndSenha(login.getEmail(),
				AppUtils.encryptPass(login.getSenha()));
		if (usuario.isPresent()) {
			usuario.get().setIdSessao(idSessao);
			usuarioRepository.save(usuario.get());
		}

		return usuario;
	}

	public Optional<UsuarioEntity> getUsuarioBySessao(String idSessao, Long idUsuario) {

		return usuarioRepository.findByIdSessaoAndId(idSessao, idUsuario);

	}

	public Endereco findEnderecoByIdUsuario(Long idUsuario) {

		UsuarioEntity usuario = new UsuarioEntity();
		Endereco endereco = new Endereco();
		usuario.setId(idUsuario);
		Optional<UsuarioEndereco> findByUsuario = usuarioEnderecoRepository.findByUsuario(usuario);
		if (findByUsuario.isPresent()) {
			endereco = findByUsuario.get().getEndereco();

		}
		return endereco;

	}

	public Optional<UsuarioEntity> getUsuarioByIdUsuario(Long idUsuario) {
		return usuarioRepository.findById(idUsuario);
	}

	public void resetSenhaEnvioEmail(DefaultIdStringRequest emailUsuario) {

		Optional<UsuarioEntity> findById = usuarioRepository.findByEmail(emailUsuario.getId());
		if (findById.isPresent()) {
			Optional<ParametrosGerais> host = parametrosRepository.findByNome("HOST");

			if (host.isPresent()) {
				
				String montaLink = host.get().getValor() + "/reset/senha/usuario/" + findById.get().getEmail();
				
				StringBuilder sb = new StringBuilder();
				sb.append("Olá ");
				sb.append(findById.get().getNome());
				sb.append(". Vimos que solicitou o reset da sua senha.");
				sb.append(" Aqui está o link para realizar o reset. \n");
				sb.append(montaLink);
				sb.append(" Caso não tenha solicitado, pedimos que desconsidere este email;");
				emailService.enviarEmail(findById.get(), sb.toString());
			}

		}

	}

	public void resetSenha(UsuarioResetSenhaRequest usuario) {

		Optional<UsuarioEntity> findByEmail = usuarioRepository.findByEmail(usuario.getEmail());
		if(findByEmail.isPresent()) {
			
			UsuarioEntity usuarioEntity = findByEmail.get();
			usuarioEntity.setSenha(AppUtils.encryptPass(usuario.getSenha()));
			usuarioRepository.save(usuarioEntity);
			
		}
		
	}

	public List<FormaPagamentoUsuario> findCartoesByIdUsuario(Long idUsuario) {
		
		List<FormaPagamentoUsuario> response = new ArrayList<>();
		Optional<UsuarioEntity> findById = usuarioRepository.findById(idUsuario);
		if(findById.isPresent()) {
			
			List<FormaPagamentoUsuario> findByUsuario = formaPagamentoRepository.findByUsuario(findById.get());
			if(!findByUsuario.isEmpty()) {
				return findByUsuario;
			}
		}
		
		return response;
	}

	public void atualizarUsuario(UsuarioRequest usuario) {
	
		Optional<UsuarioEntity> findById = usuarioRepository.findById(usuario.getId());
		if(findById.isPresent()) {
			UsuarioEntity usuarioEntity = findById.get();
			usuarioEntity.setEmail(usuario.getEmail());
			usuarioEntity.setSenha(AppUtils.encryptPass(usuario.getSenha()));
			usuarioEntity.setNome(usuario.getNome());
			usuarioEntity.setCpf(usuario.getCpf());
			usuarioEntity.setRg(usuario.getRg());
			usuarioRepository.save(usuarioEntity);
		}
		
	}

	public void atualizarEndereco(EnderecoRequest endereco, Long idUsuario) {

		Optional<UsuarioEntity> findById = usuarioRepository.findById(idUsuario);
		if(findById.isPresent()) {
			Optional<UsuarioEndereco> findByUsuario = usuarioEnderecoRepository.findByUsuario(findById.get());
			if(findByUsuario.isPresent()) {
				
				Endereco endereco2 = findByUsuario.get().getEndereco();
				endereco2.setLogradouro(endereco.getLogradouro());
				endereco2.setCidade(endereco.getCidade());
				endereco2.setBairro(endereco.getBairro());
				endereco2.setEstado(endereco.getEstado());
				endereco2.setNumero(endereco.getNumero());
				endereco.setCep(endereco.getCep());
				enderecoRepository.save(endereco2);
				
				
				
			}
		}
		
		
		
	}

	public void atualizarCartoes(Long idCartao, String cartao, Long idUsuario) {

		Optional<UsuarioEntity> findById = usuarioRepository.findById(idUsuario);
		if(findById.isPresent()) {
			Optional<FormaPagamentoUsuario> findByUsuario = formaPagamentoRepository.findByIdAndUsuario(idCartao, findById.get());
			if(findByUsuario.isPresent()) {
				FormaPagamentoUsuario formaPagamentoUsuario = findByUsuario.get();
				formaPagamentoUsuario.setNumeroCartao(cartao);
				formaPagamentoRepository.save(formaPagamentoUsuario);
			}
			
		}
		
	}

	public void deletarCartao(Long idCartao, Long idUsuario) {
		
		Optional<UsuarioEntity> findById = usuarioRepository.findById(idUsuario);
		if(findById.isPresent()) {
			Optional<FormaPagamentoUsuario> findByUsuario = formaPagamentoRepository.findByIdAndUsuario(idCartao, findById.get());
			if(findByUsuario.isPresent()) {
				formaPagamentoRepository.delete(findByUsuario.get());
			}
		}
		
	}

}
