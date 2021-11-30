package br.com.magicstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magicstore.dto.CategoriaRequest;
import br.com.magicstore.dto.CategoriaResponse;
import br.com.magicstore.entity.Categoria;
import br.com.magicstore.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public void cadastrarCategoria(CategoriaRequest request) {
		repository.save(new Categoria(request.getNome()));
	}

	public List<CategoriaResponse> getAllCategoria() {
		
		List<Categoria> findAll = repository.findAll();
		List<CategoriaResponse> response = new ArrayList<>();
		
		findAll.forEach(ct -> 
			response.add(new CategoriaResponse(ct.getId(), ct.getNome())));
		
		return response;
	}

	public void deleteCategoria(Long idCategoria) {

		Optional<Categoria> findById = repository.findById(idCategoria);
		if(findById.isPresent()) {
			repository.delete(findById.get());
		}
		
	}

}
