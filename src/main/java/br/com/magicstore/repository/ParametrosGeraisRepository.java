package br.com.magicstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magicstore.entity.ParametrosGerais;

@Repository
public interface ParametrosGeraisRepository extends JpaRepository<ParametrosGerais, Long>{

	Optional<ParametrosGerais> findByNome(String nome);
	
}
