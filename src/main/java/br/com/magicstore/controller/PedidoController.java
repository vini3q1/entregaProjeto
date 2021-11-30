package br.com.magicstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.magicstore.dto.PedidoRequest;
import br.com.magicstore.service.PedidoService;
import io.swagger.annotations.Api;

@Api(tags = "Pedido API")
@RestController
@RequestMapping(value = "pedido")
public class PedidoController {

	@Autowired
	private PedidoService service;
	
	@PostMapping
	public ModelAndView finalizarPedido(@Validated PedidoRequest request) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("codigoPedido", service.finalizarPedido(request));
		mv.setViewName("home/pedidoFinalizado");
		return mv;
		
	}
	
	@GetMapping
	public ModelAndView getPedidosByIdUsuario(@RequestParam("idUsuario") Long idUsuario) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pedidos", service.getPedidosByIdUsuario(idUsuario));
		mv.setViewName("home/pedidos");
		return mv;
		
	}
	
}
