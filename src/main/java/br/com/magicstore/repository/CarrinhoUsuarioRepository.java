package br.com.magicstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.magicstore.entity.Carrinho;
import br.com.magicstore.entity.CarrinhoUsuario;
import br.com.magicstore.entity.UsuarioEntity;

@Repository
public interface CarrinhoUsuarioRepository extends JpaRepository<CarrinhoUsuario, Long>{
	
	@Query("SELECT cu FROM CarrinhoUsuario cu JOIN cu.usuario u JOIN cu.carrinho c WHERE c.idSessao = :idSessao")
	List<CarrinhoUsuario> findBySessaoAndUsuario(String idSessao);
	
	@Query("SELECT cu FROM CarrinhoUsuario cu JOIN cu.usuario u JOIN cu.carrinho c WHERE u = :usuario and c.status = :status")
	List<CarrinhoUsuario> findByUsuarioAndStatus(UsuarioEntity usuario, String status);
	
	@Query("SELECT cu FROM CarrinhoUsuario cu JOIN cu.usuario u JOIN cu.carrinho c WHERE u.id = :idUsuario")
	List<CarrinhoUsuario> findByIdUsuario(Long idUsuario);
	
	List<CarrinhoUsuario> findByUsuarioAndCarrinho(UsuarioEntity usuario, Carrinho carrinho);
	
	@Query("SELECT cu FROM CarrinhoUsuario cu JOIN cu.carrinho c WHERE c.id = :idItemCarrinho AND c.status = :status")
	List<CarrinhoUsuario> findByCarrinhoAndStatus(Long idItemCarrinho, String status);

}
