package br.com.magicstore.dto;

public class CarrinhoRequest {

	private Long idProduto;
	private Long quantidade;
	private double valorCarrinho;
	
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
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
