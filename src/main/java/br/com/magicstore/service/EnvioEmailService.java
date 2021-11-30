package br.com.magicstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magicstore.entity.ParametrosGerais;
import br.com.magicstore.entity.UsuarioEntity;
import br.com.magicstore.repository.ParametrosGeraisRepository;
import br.com.magicstore.utils.AppUtils;

@Service
public class EnvioEmailService {

	@Autowired
	private ParametrosGeraisRepository parametrosRepository;
	
	public void enviarEmail(UsuarioEntity usuario, String texto) {
		
		Optional<ParametrosGerais> email = parametrosRepository.findByNome("EMAIL_SENDER");
		Optional<ParametrosGerais> senha = parametrosRepository.findByNome("PASS_EMAIL_SENDER");
		Optional<ParametrosGerais> host = parametrosRepository.findByNome("HOST");

		if (email.isPresent() && senha.isPresent() && host.isPresent()) {
			AppUtils.envioEmail(usuario.getEmail(), email.get().getValor(), senha.get().getValor(),
					texto);
		}
		
	}
	
}
