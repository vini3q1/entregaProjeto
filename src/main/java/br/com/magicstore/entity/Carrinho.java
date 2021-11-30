package br.com.magicstore.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "carrinho")
public class Carrinho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	@Column(name = "id_sessao")
	private String idSessao;
	
	private Long quantidade;
	
	@Column(name = "valor_carrinho")
	private BigDecimal valorCarrinho;
	
	private String status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_carrinho")
	private Date dataCarrinho;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorCarrinho() {
		return valorCarrinho;
	}

	public void setValorCarrinho(BigDecimal valorCarrinho) {
		this.valorCarrinho = valorCarrinho;
	}

	public Date getDataCarrinho() {
		return dataCarrinho;
	}

	public void setDataCarrinho(Date dataCarrinho) {
		this.dataCarrinho = dataCarrinho;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
