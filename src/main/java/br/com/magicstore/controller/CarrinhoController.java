package br.com.magicstore.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.magicstore.dto.CarrinhoRequest;
import br.com.magicstore.dto.CarrinhoUsuarioRequest;
import br.com.magicstore.dto.PedidoRequest;
import br.com.magicstore.entity.Produto;
import br.com.magicstore.service.CarrinhoService;
import br.com.magicstore.service.CategoriaService;
import br.com.magicstore.service.ProdutoService;
import br.com.magicstore.service.UsuarioService;
import io.swagger.annotations.Api;

@Api(tags = "Carrinho API")
@RestController
@RequestMapping(value = "carrinho")
public class CarrinhoController {

	@Autowired
	private CarrinhoService service;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping
	public ModelAndView adicionarItem(@Validated CarrinhoRequest request, HttpSession session) {
		Produto pr = service.adicionarItem(request, session.getId());

		ModelAndView mv = new ModelAndView();
		mv.addObject("categorias", categoriaService.getAllCategoria());
		mv.addObject("produtos", produtoService.getProdutoByCategoria(pr.getCategoria().getId()));
//		mv.addObject("carrinho", new CarrinhoRequest());

		mv.setViewName("home/categorias");
		return mv;

	}
	
	@GetMapping
	public ModelAndView getCarrinho() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/carrinho");
		
		return mv;
	}
	
	@GetMapping("/checkout/{idUsuario}")
	public ModelAndView getCheckout(@PathVariable Long idUsuario) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("enderecoUsuario", usuarioService.findEnderecoByIdUsuario(idUsuario));
		
		BigDecimal valorCarrinhoByUsuario = service.getValorCarrinhoByUsuario(idUsuario);
		if(valorCarrinhoByUsuario.compareTo(BigDecimal.ZERO) != 0) {
			BigDecimal freteDefault = new BigDecimal(150);
			BigDecimal freteFinal = new BigDecimal(0);
			BigDecimal valorPedido = new BigDecimal(0);
			
			if(valorCarrinhoByUsuario.compareTo(freteDefault) < 1) {
				freteFinal = new BigDecimal(20);
			}
			
			valorPedido = valorPedido.add(valorCarrinhoByUsuario.add(freteFinal));
			mv.addObject("valorTotalCarrinho", valorCarrinhoByUsuario);
			mv.addObject("valorFrete", freteFinal);
			mv.addObject("valorPedido", valorPedido);
			mv.addObject("modalidadePagamento", service.getModalidadePagamento(valorPedido));
			mv.addObject("idUsuario", idUsuario);
			mv.addObject("pedido", new PedidoRequest());
			
			mv.setViewName("home/checkout");
			
			return mv;
		} else {
			return getCarrinho();
		}
		
	}
	
	@GetMapping("cart")
	public ModelAndView getCarrinhoByIdSessao(String idSessao, boolean logado, Long idUsuario) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("carrinhoSessao", service.getCarrinhoByIdSessao(idSessao, logado, idUsuario));
		mv.addObject("valorCarrinho", service.getValorCarrinhoByIdSessao(idSessao, idUsuario));
		mv.setViewName("home/carrinho");
		
		return mv;
	}
	
	@GetMapping("/valor/{idSessao}")
	public ModelAndView getValorCarrinhoByIdSessao(@PathVariable String idSessao) {
		
		ModelAndView mv = new ModelAndView();
//		mv.addObject("valorCarrinho", service.getValorCarrinhoByIdSessao(idSessao));
		return mv;
	}
	
	@PostMapping("/usuario")
	public ResponseEntity<Object> vinculaCarrinhoUsuario(@RequestBody CarrinhoUsuarioRequest request){
		
		service.vinculaCarrinhoUsuario(request);
		
		return null;
	}
	
	@DeleteMapping("/item/delete")
	public ModelAndView deletaItemCarrinho(Long idItemCarrinho, String idSessao, boolean logado, Long idUsuario){
		
		service.deletaItemCarrinho(idItemCarrinho);
		return getCarrinhoByIdSessao(idSessao, logado, idUsuario);
	}
	
}
