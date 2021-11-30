package br.com.magicstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magicstore.entity.FormaPagamentoUsuario;
import br.com.magicstore.entity.UsuarioEntity;

@Repository
public interface FormaPagamentoUsuarioRepository extends JpaRepository<FormaPagamentoUsuario, Long>{

	List<FormaPagamentoUsuario> findByUsuario(UsuarioEntity usuario);
	
	Optional<FormaPagamentoUsuario> findByIdAndUsuario(Long id, UsuarioEntity usuario);
	
}
