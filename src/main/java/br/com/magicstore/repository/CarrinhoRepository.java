package br.com.magicstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magicstore.entity.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{

	List<Carrinho> findByIdSessaoAndStatus(String idSessao, String status);
	
	Optional<Carrinho> findByIdAndStatus(Long id, String status);
	
}
