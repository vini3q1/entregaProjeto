package br.com.magicstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magicstore.dto.ContatoRequest;
import br.com.magicstore.entity.Contato;
import br.com.magicstore.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository repository;
	
	public void cadastrar(ContatoRequest request) {

		Contato contato = new Contato();
		contato.setNome(request.getNome());
		contato.setEmail(request.getEmail());
		contato.setMensagem(request.getMensagem());
		repository.save(contato);
		
	}

	
	
}
