package br.com.magicstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magicstore.entity.UsuarioEndereco;
import br.com.magicstore.entity.UsuarioEntity;

@Repository
public interface UsuarioEnderecoRepository extends JpaRepository<UsuarioEndereco, Long>{

	Optional<UsuarioEndereco> findByUsuario(UsuarioEntity usuario);
	
}
