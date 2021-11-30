package br.com.magicstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.magicstore.entity.Categoria;
import br.com.magicstore.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	List<Produto> findByCategoria(Categoria categoria);
	
	@Query(value = "SELECT * FROM bd_magic_store.produto p ORDER BY RAND() LIMIT 4", nativeQuery = true)
	List<Produto> findRandom();

}
