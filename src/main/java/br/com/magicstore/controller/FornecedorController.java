package br.com.magicstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.magicstore.dto.FornecedorDTO;
import br.com.magicstore.service.FornecedorService;
import io.swagger.annotations.Api;

@Api(tags = "Fornecedor API")
@RestController
@RequestMapping(value = "forcedor")
public class FornecedorController {

	@Autowired
	private FornecedorService service;
	
	@PostMapping
	public void cadastrar(@RequestBody FornecedorDTO request) {
		service.cadastrar(request);
	}
	
	
	@GetMapping
	public List<FornecedorDTO> getAllFornecedor() {
		return service.getAllFornecedor();
	}
	
}
