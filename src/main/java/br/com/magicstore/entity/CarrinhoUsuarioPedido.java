package br.com.magicstore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carrinho_usuario_pedido")
public class CarrinhoUsuarioPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "id_carrinho_usuario")
	private CarrinhoUsuario carrinhoUsuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public CarrinhoUsuario getCarrinhoUsuario() {
		return carrinhoUsuario;
	}

	public void setCarrinhoUsuario(CarrinhoUsuario carrinhoUsuario) {
		this.carrinhoUsuario = carrinhoUsuario;
	}
	
	
	
}
