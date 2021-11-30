package br.com.magicstore.dto;

public class CarrinhoResponse {

	private Long id;
	private ProdutoDTO produto;
	private String idSessao;
	private Long quantidade;
	private double valorCarrinho;
	
	public CarrinhoResponse(Long id, ProdutoDTO produto, String idSessao, Long quantidade, double valorCarrinho) {
		this.id = id;
		this.produto = produto;
		this.idSessao = idSessao;
		this.quantidade = quantidade;
		this.valorCarrinho = valorCarrinho;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProdutoDTO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoDTO produto) {
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
	public double getValorCarrinho() {
		return valorCarrinho;
	}
	public void setValorCarrinho(double valorCarrinho) {
		this.valorCarrinho = valorCarrinho;
	}
}
