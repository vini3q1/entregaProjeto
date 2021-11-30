package br.com.magicstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;

@Api(tags = "Administracao API")
@RestController
@RequestMapping(value = "administracao")
public class AdministracaoController {

	@GetMapping
	public ModelAndView get() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("administracao/index");
		return mv;
	}
	
}
