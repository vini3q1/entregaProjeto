package br.com.magicstore.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magicstore.dto.PedidoRequest;
import br.com.magicstore.entity.Carrinho;
import br.com.magicstore.entity.CarrinhoUsuarioPedido;
import br.com.magicstore.entity.FormaPagamentoUsuario;
import br.com.magicstore.entity.ModalidadePagamento;
import br.com.magicstore.entity.ParametrosGerais;
import br.com.magicstore.entity.Pedido;
import br.com.magicstore.entity.UsuarioEntity;
import br.com.magicstore.repository.CarrinhoRepository;
import br.com.magicstore.repository.CarrinhoUsuarioPedidoRepository;
import br.com.magicstore.repository.CarrinhoUsuarioRepository;
import br.com.magicstore.repository.FormaPagamentoUsuarioRepository;
import br.com.magicstore.repository.PedidoRepository;
import br.com.magicstore.utils.AppUtils;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@Autowired
	private CarrinhoUsuarioRepository carrinhoUsuarioRepository;
	
	@Autowired
	private CarrinhoUsuarioPedidoRepository carrinhoUsuarioPedidoRepository;
	
	@Autowired
	private FormaPagamentoUsuarioRepository fpuRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EnvioEmailService emailService;
	
	public String finalizarPedido(PedidoRequest request) {

		String codigoPedido = AppUtils.gerarCodigoPedido();
		
		Pedido pedido = new Pedido();
		pedido.setNumeroPedido(codigoPedido);
		pedido.setValorPedido(BigDecimal.valueOf(request.getValorTotal()));
		pedido.setDataPedido(AppUtils.getDateNow());
		pedido.setFrete(BigDecimal.valueOf(request.getValorFrete()));
		pedido.setStatus("APROVADO");
		
		this.salvarFormaPagamento(request);
		
		Pedido pedidoSave = repository.save(pedido);
		
		carrinhoUsuarioRepository.findByIdUsuario(request.getIdUsuario()).forEach(carrinho->{
			
			CarrinhoUsuarioPedido carus = new CarrinhoUsuarioPedido();
			carus.setPedido(pedidoSave);
			carus.setCarrinhoUsuario(carrinho);
			carrinhoUsuarioPedidoRepository.save(carus);
			
			Optional<Carrinho> ca = carrinhoRepository.findById(carrinho.getCarrinho().getId());
			if(ca.isPresent()) {
				ca.get().setStatus("FECHADO");
				carrinhoRepository.save(ca.get());
			}
			
		});
		
		Optional<UsuarioEntity> usuarioByIdUsuario = usuarioService.getUsuarioByIdUsuario(request.getIdUsuario());
		if(usuarioByIdUsuario.isPresent()) {
			
			StringBuilder sb = new StringBuilder();
			sb.append("Obrigado pela sua compra ");
			sb.append(usuarioByIdUsuario.get().getNome());
			sb.append(". Esse é o código do seu pedido - ");
			sb.append(codigoPedido);
			sb.append(" - Você pode acompanhar o andamento dele no nosso site!");
			
			emailService.enviarEmail(usuarioByIdUsuario.get(), sb.toString());
			
		}
		
		return codigoPedido;
	}

	private void salvarFormaPagamento(PedidoRequest request) {
		
		FormaPagamentoUsuario forma = new FormaPagamentoUsuario();
		ModalidadePagamento mp = new ModalidadePagamento(request.getIdModalidadePagamento());
		UsuarioEntity usuario = new UsuarioEntity(request.getIdUsuario());
		forma.setModalidadePagamento(mp);
		forma.setUsuario(usuario);
		forma.setNumeroCartao(request.getNumeroCartao());
		fpuRepository.save(forma);
		
	}

	public List<Pedido> getPedidosByIdUsuario(Long idUsuario) {
		
		Optional<UsuarioEntity> usuario = usuarioService.getUsuarioByIdUsuario(idUsuario);
		if(usuario.isPresent()) {
			List<Pedido> findByUsuario = carrinhoUsuarioPedidoRepository.findByUsuario(usuario.get());
			if(!findByUsuario.isEmpty()) {
				return findByUsuario;
			}
		}
		return null;
	}

}
