package br.com.magicstore.dto;

public class LoginRequest {

	private String email;
	private String senha;
//	private String idSessao;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
//	public String getIdSessao() {
//		return idSessao;
//	}
//	public void setIdSessao(String idSessao) {
//		this.idSessao = idSessao;
//	}
	
}
