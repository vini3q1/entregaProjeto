package br.com.magicstore.dto;

public class PedidoRequest {

	private Long idUsuario;
	private Long idModalidadePagamento;
	private double valorTotal;
	private double valorFrete;
	private String numeroCartao;
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdModalidadePagamento() {
		return idModalidadePagamento;
	}
	public void setIdModalidadePagamento(Long idModalidadePagamento) {
		this.idModalidadePagamento = idModalidadePagamento;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public double getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(double valorFrete) {
		this.valorFrete = valorFrete;
	}
	
}
