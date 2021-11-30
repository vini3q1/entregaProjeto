package br.com.magicstore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.magicstore.dto.CarrinhoRequest;
import br.com.magicstore.dto.CategoriaRequest;
import br.com.magicstore.service.CarrinhoService;
import br.com.magicstore.service.CategoriaService;
import br.com.magicstore.service.ProdutoService;
import io.swagger.annotations.Api;

@Api(tags = "Categoria API")
@RestController
@RequestMapping("categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService service;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CarrinhoService carrinhoService;

	@PostMapping
	public ModelAndView cadastrarCategoria(@Validated CategoriaRequest request) {
		ModelAndView mv = new ModelAndView();

		try {
			service.cadastrarCategoria(request);
			mv.addObject("msg", "CADASTRO_SUCESSO");
		} catch (Exception e) {
			mv.addObject("msg", "CADASTRO_ERRO");
		}
		
		mv.addObject("categorias", service.getAllCategoria());
		mv.addObject("novaCategoria", new CategoriaRequest());
		mv.setViewName("administracao/categorias");
		
		return mv;
	}

	@GetMapping("all")
	public ModelAndView getAllCategoria(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("categorias", service.getAllCategoria());
		mv.addObject("produtos", produtoService.getProdutoByCategoria(4l));
		mv.addObject("carrinho", new CarrinhoRequest());
		session.setAttribute("sessionID", session.getId());

		mv.setViewName("home/categorias");
		return mv;
	}

	@GetMapping("all/noproduct")
	public ModelAndView getAllNoProductCategoria() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("categorias", service.getAllCategoria());
		mv.addObject("novaCategoria", new CategoriaRequest());
		mv.setViewName("administracao/categorias");

		return mv;
	}

//	@PostMapping("/carrinho")
//	public ModelAndView adicionarItem(@Validated CarrinhoRequest request, HttpSession session) {
//		Produto pr = carrinhoService.adicionarItem(request, session.getId());
//
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("categorias", service.getAllCategoria());
//		mv.addObject("produtos", produtoService.getProdutoByCategoria(pr.getCategoria().getId()));
////		mv.addObject("carrinho", new CarrinhoRequest());
//
//		mv.setViewName("home/categorias");
//		return mv;
//
//	}

}
