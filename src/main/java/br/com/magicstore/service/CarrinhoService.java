package br.com.magicstore.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magicstore.controller.UsuarioController;
import br.com.magicstore.dto.CarrinhoRequest;
import br.com.magicstore.dto.CarrinhoResponse;
import br.com.magicstore.dto.CarrinhoUsuarioRequest;
import br.com.magicstore.dto.CategoriaResponse;
import br.com.magicstore.dto.ProdutoDTO;
import br.com.magicstore.entity.Carrinho;
import br.com.magicstore.entity.CarrinhoUsuario;
import br.com.magicstore.entity.Categoria;
import br.com.magicstore.entity.ModalidadePagamento;
import br.com.magicstore.entity.Produto;
import br.com.magicstore.entity.UsuarioEntity;
import br.com.magicstore.repository.CarrinhoRepository;
import br.com.magicstore.repository.CarrinhoUsuarioRepository;
import br.com.magicstore.repository.ModalidadePagamentoRepository;
import br.com.magicstore.repository.ProdutoRepository;
import br.com.magicstore.repository.UsuarioRepository;
import br.com.magicstore.utils.AppUtils;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository repository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CarrinhoUsuarioRepository carrinhoUsuarioRepository;
	
	@Autowired
	private ModalidadePagamentoRepository modalidadeRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	public Produto adicionarItem(CarrinhoRequest request, String idSessao) {

//		TODO validar campos nuloss
		Optional<Produto> produto = produtoRepository.findById(request.getIdProduto());
		if (produto.isPresent()) {

			Carrinho carrinho = new Carrinho();
			carrinho.setProduto(produto.get());
			carrinho.setIdSessao(idSessao);
			carrinho.setQuantidade(request.getQuantidade());
			carrinho.setValorCarrinho(BigDecimal.valueOf(request.getValorCarrinho()).multiply(BigDecimal.valueOf(request.getQuantidade())));
			carrinho.setStatus("ABERTO");
			carrinho.setDataCarrinho(AppUtils.getDateNow());
			repository.save(carrinho);

		}
		return produto.get();
	}

	public List<CarrinhoResponse> getCarrinhoByIdSessao(String idSessao, boolean logado, Long idUsuario) {

		List<CarrinhoResponse> response = new ArrayList<>();
		
		if(logado && idUsuario != 0) {
			Optional<UsuarioEntity> usuarioByIdUsuario = usuarioService.getUsuarioByIdUsuario(idUsuario);
			if(usuarioByIdUsuario.isPresent()) {
				List<CarrinhoUsuario> findByUsuario = carrinhoUsuarioRepository.findByUsuarioAndStatus(usuarioByIdUsuario.get(), "ABERTO");
				if(!findByUsuario.isEmpty()) {
					for (CarrinhoUsuario carrinhoUsuario : findByUsuario) {
						Carrinho carrinho = carrinhoUsuario.getCarrinho();
						response.add(this.handleCarrinho(carrinho));
					}
				} else {
					List<Carrinho> listaCarrinho = repository.findByIdSessaoAndStatus(idSessao,"ABERTO");
					if(!listaCarrinho.isEmpty()) {
						listaCarrinho.forEach(item->
						carrinhoUsuarioRepository.save(new CarrinhoUsuario(usuarioByIdUsuario.get(), item)));
					}
					listaCarrinho.forEach(carrinho -> 
						response.add(this.handleCarrinho(carrinho))
					);
				}
			}
		} else {
			List<Carrinho> listaCarrinho = repository.findByIdSessaoAndStatus(idSessao,"ABERTO");
			listaCarrinho.forEach(carrinho -> 
				response.add(this.handleCarrinho(carrinho))
			);
		}
		
		return response;
	}

	private CarrinhoResponse handleCarrinho(Carrinho carrinho) {

		Produto pr = carrinho.getProduto();
		Categoria categoria = carrinho.getProduto().getCategoria();
		CategoriaResponse cate = new CategoriaResponse(categoria.getId(), categoria.getNome());
		ProdutoDTO produto = new ProdutoDTO(pr.getId(), pr.getNome(), pr.getDescricao(), cate, null,
				pr.getQuantidade(), pr.getValor().doubleValue(), pr.getImagem());
		
		return new CarrinhoResponse(carrinho.getId(), produto, carrinho.getIdSessao(),
				carrinho.getQuantidade(), carrinho.getValorCarrinho().doubleValue());
	}

	public void vinculaCarrinhoUsuario(CarrinhoUsuarioRequest request) {

		List<Carrinho> findByIdSessao = repository.findByIdSessaoAndStatus(request.getSessao().getId(),"ABERTO");
		if (!findByIdSessao.isEmpty()) {
			Optional<UsuarioEntity> usuario = usuarioRepository.findById(request.getUsuario().getId());
			if (usuario.isPresent()) {
				
				for (Carrinho carrinho2 : findByIdSessao) {
					if(carrinhoUsuarioRepository.findByUsuarioAndCarrinho(usuario.get(), carrinho2).isEmpty()) {
						findByIdSessao.forEach(
								carrinho -> carrinhoUsuarioRepository.save(new CarrinhoUsuario(usuario.get(), carrinho)));
					}
					
				}
				
				
			}
		}
	}

	public BigDecimal getValorCarrinhoByIdSessao(String idSessao, Long idUsuario) {
		
		BigDecimal total = new BigDecimal(0l); 
		Optional<UsuarioEntity> usuarioBySessao = usuarioService.getUsuarioBySessao(idSessao, idUsuario);
		if(usuarioBySessao.isPresent()) {
			
			List<CarrinhoUsuario> findByUsuario = carrinhoUsuarioRepository.findByUsuarioAndStatus(usuarioBySessao.get(), "ABERTO");
			for (CarrinhoUsuario carrinhoUsuario : findByUsuario) {
				BigDecimal valorCarrinho = carrinhoUsuario.getCarrinho().getValorCarrinho();
				total = valorCarrinho.add(total);
			}
			
		}

		return total;
	}

	public BigDecimal getValorCarrinhoByUsuario(Long idUsuario) {

		BigDecimal total = new BigDecimal(0l); 
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setId(idUsuario);
		List<CarrinhoUsuario> findByUsuario = carrinhoUsuarioRepository.findByUsuarioAndStatus(usuario, "ABERTO");
		for (CarrinhoUsuario carrinhoUsuario : findByUsuario) {
			BigDecimal valorCarrinho = carrinhoUsuario.getCarrinho().getValorCarrinho();
			total = valorCarrinho.add(total);
		}
		return total;
	}
	
	public List<ModalidadePagamento> getModalidadePagamento(){
		return modalidadeRepository.findAll();
	}

	public List<ModalidadePagamento>  getModalidadePagamento(BigDecimal valorPedido) {
		
		BigDecimal par = new BigDecimal(100);
		
		if(valorPedido.compareTo(par) >= 1) {
			return modalidadeRepository.findAll();
		} else {
			return modalidadeRepository.findCreditoAndDebito("Crédito - 1x", "Débito");
		}
	}

	public void deletaItemCarrinho(Long idItemCarrinho) {
		
		List<CarrinhoUsuario> findByCarrinho = carrinhoUsuarioRepository.findByCarrinhoAndStatus(idItemCarrinho, "ABERTO");
		if(!findByCarrinho.isEmpty()) {
			
			Long idCarrinho = findByCarrinho.get(0).getCarrinho().getId();
			findByCarrinho.forEach(item -> carrinhoUsuarioRepository.delete(item));
			repository.deleteById(idCarrinho);
			
		} else {
			repository.findByIdAndStatus(idItemCarrinho, "ABERTO").ifPresent(item->repository.delete(item));
		}
	}

}
