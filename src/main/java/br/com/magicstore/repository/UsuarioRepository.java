package br.com.magicstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magicstore.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

	Optional<UsuarioEntity> findByEmail(String email);
	
	Optional<UsuarioEntity> findByEmailAndSenha(String email, String senha);
	
	Optional<UsuarioEntity> findByIdSessaoAndId(String idSessao, Long id);
	
}
