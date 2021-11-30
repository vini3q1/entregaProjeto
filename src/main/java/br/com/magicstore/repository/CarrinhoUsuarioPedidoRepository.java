package br.com.magicstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.magicstore.entity.CarrinhoUsuarioPedido;
import br.com.magicstore.entity.Pedido;
import br.com.magicstore.entity.UsuarioEntity;

@Repository
public interface CarrinhoUsuarioPedidoRepository extends JpaRepository<CarrinhoUsuarioPedido, Long>{

	@Query("SELECT p FROM CarrinhoUsuarioPedido cup JOIN cup.carrinhoUsuario cu JOIN cup.pedido p WHERE cu.usuario = :usuario GROUP BY p.id")
	List<Pedido> findByUsuario(UsuarioEntity usuario);
	
}
