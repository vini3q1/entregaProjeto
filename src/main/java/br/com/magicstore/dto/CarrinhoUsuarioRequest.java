package br.com.magicstore.dto;

public class CarrinhoUsuarioRequest {

	private DefaultIdStringRequest sessao; 
	private DefaultIdRequest usuario;

	public DefaultIdStringRequest getSessao() {
		return sessao;
	}
	public void setSessao(DefaultIdStringRequest sessao) {
		this.sessao = sessao;
	}
	public DefaultIdRequest getUsuario() {
		return usuario;
	}
	public void setUsuario(DefaultIdRequest usuario) {
		this.usuario = usuario;
	}
	
}
