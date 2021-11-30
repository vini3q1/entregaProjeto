package br.com.magicstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magicstore.dto.FornecedorDTO;
import br.com.magicstore.entity.Fornecedor;
import br.com.magicstore.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository repository;

	public void cadastrar(FornecedorDTO request) {

//		TODO Validar se campos são nulos
		Fornecedor forn = new Fornecedor();
		forn.setEmpresa(request.getEmpresa());
		forn.setCnpj(request.getCnpj());
		forn.setEmail(request.getEmail());
		forn.setTelefone(request.getTelefone());
		forn.setNomeContato(request.getNomeContato());
		repository.save(forn);

	}

	public List<FornecedorDTO> getAllFornecedor() {

		List<Fornecedor> findAll = repository.findAll();
		List<FornecedorDTO> response = new ArrayList<>();

		findAll.forEach(forn -> response.add(new FornecedorDTO(forn.getId(), forn.getEmpresa(), forn.getCnpj(),
				forn.getEmail(), forn.getTelefone(), forn.getNomeContato())));
		return response;

	}

}
