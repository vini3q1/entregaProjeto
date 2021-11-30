package br.com.magicstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.magicstore.dto.ContatoRequest;
import br.com.magicstore.service.ContatoService;

@RestController
@RequestMapping("/contato")
public class ContatoController {
	
	@Autowired
	private ContatoService service;

	@GetMapping
	public ModelAndView get() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("contato", new ContatoRequest());
		mv.setViewName("home/contato.html");
		return mv;
	}
	
	@PostMapping
	public ModelAndView cadastrarMensagem(@Validated ContatoRequest request) {
		service.cadastrar(request);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("contato", new ContatoRequest());
		mv.addObject("sucesso");
		mv.setViewName("home/contato.html");
		
		return mv;
	}
	
}
