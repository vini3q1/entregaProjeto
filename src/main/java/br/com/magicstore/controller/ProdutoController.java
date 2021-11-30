package br.com.magicstore.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.magicstore.dto.CarrinhoRequest;
import br.com.magicstore.dto.ProdutoDTO;
import br.com.magicstore.dto.ProdutoRequest;
import br.com.magicstore.exception.DefaultException;
import br.com.magicstore.service.CategoriaService;
import br.com.magicstore.service.ProdutoService;
import io.swagger.annotations.Api;

@Api(tags = "Produto API")
@RestController
@RequestMapping(value = "produto")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@Autowired
	private CategoriaService categoriaService;

	@PostMapping("/cadastrar")
	public ModelAndView cadastrarProduto(@Validated ProdutoRequest request) throws DefaultException, IOException {

		ModelAndView mv = new ModelAndView();
		try {
			service.cadastrar(request);
			mv.addObject("msg", "CADASTRO_SUCESSO");
		} catch (Exception e) {
			mv.addObject("msg", "CADASTRO_ERRO");
		}
		
		mv.addObject("categorias", categoriaService.getAllCategoria());
		mv.addObject("produtos", service.getProdutoByCategoria(request.getIdCategoria()));
		mv.addObject("produto", new ProdutoRequest());

		mv.setViewName("administracao/produtos");
		
		return mv;
	}

	@GetMapping("/lista")
	public List<ProdutoDTO> getAllProduto() {
		return service.getAllProduto();
	}

	@GetMapping("/lista/{categoria}")
	public ModelAndView getProdutoByCategoria(@PathVariable("categoria") Long categoria) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("categorias", categoriaService.getAllCategoria());
		mv.addObject("produtos", service.getProdutoByCategoria(categoria));
		mv.addObject("carrinho", new CarrinhoRequest());

		mv.setViewName("home/categorias");
		return mv;
	}

	@GetMapping("/lista/administracao/{categoria}")
	public ModelAndView getProdutoByCategoriaAdm(@PathVariable("categoria") Long categoria) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("categorias", categoriaService.getAllCategoria());
		mv.addObject("produtos", service.getProdutoByCategoria(categoria));
		mv.addObject("produto", new ProdutoRequest());

		mv.setViewName("administracao/produtos");
		return mv;
	}

	@GetMapping("/lista/administracao")
	public ModelAndView getAllProdutoAdm() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("categorias", categoriaService.getAllCategoria());
		mv.addObject("novoProduto", new ProdutoRequest());
		mv.setViewName("administracao/novoProduto");

		return mv;
	}

	@PostMapping("/administracao/upload")
	public ModelAndView uploadImage(Long idProduto, @RequestPart("imagem") MultipartFile imagem, Long idCategoria) throws IOException {

		service.salvarImagem(idProduto, imagem);

		ModelAndView mv = new ModelAndView();
		mv.addObject("categorias", categoriaService.getAllCategoria());
		mv.addObject("produtos", service.getProdutoByCategoria(idCategoria));
		mv.addObject("produto", new ProdutoRequest());
		mv.setViewName("administracao/produtos");

		return mv;
	}
	
	@PutMapping("/administracao")
	public ModelAndView atualizarInfo(@Validated ProdutoRequest request) throws IOException {

		ModelAndView mv = new ModelAndView();
		
		try {
			service.atualizarInfo(request);
			mv.addObject("msg", "CADASTRO_SUCESSO");
		} catch (Exception e) {
			mv.addObject("msg", "CADASTRO_ERRO");
		}
		
		mv.addObject("categorias", categoriaService.getAllCategoria());
		mv.addObject("produtoEdit", service.getProdutoById(request.getId()));
		mv.addObject("produto", new ProdutoRequest());
		mv.setViewName("administracao/editarProduto");
		
		return mv;
	}
	
	@GetMapping("/administracao/editar")
	public ModelAndView viewEditarProduto(Long idProduto) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("categorias", categoriaService.getAllCategoria());
		mv.addObject("produtoEdit", service.getProdutoById(idProduto));
		mv.addObject("produto", new ProdutoRequest());
		mv.setViewName("administracao/editarProduto");

		return mv;
	}
	
	@DeleteMapping("/administracao/produto/delete")
	public ModelAndView deletearProduto(@RequestParam Long idProduto, @RequestParam Long idCategoria) throws IOException {
	
		ModelAndView mv = new ModelAndView();
		
		try {
			service.deletarProduto(idProduto);
			mv.addObject("msg", "REMOVIDO_SUCESSO");
		} catch (Exception e) {
			mv.addObject("msg", "REMOVIDO_ERRO");
		}
		
		mv.addObject("categorias", categoriaService.getAllCategoria());
		mv.addObject("produtos", service.getProdutoByCategoria(idCategoria));
		mv.addObject("produto", new ProdutoRequest());

		mv.setViewName("administracao/produtos");
		
		return mv;
	}
	
}
