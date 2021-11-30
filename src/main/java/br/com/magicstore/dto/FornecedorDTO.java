package br.com.magicstore.dto;

public class FornecedorDTO {

	private Long id;
	private String empresa;
	private String cnpj;
	private String email;
	private String telefone;
	private String nomeContato;
	
	public FornecedorDTO(Long id, String empresa, String cnpj, String email, String telefone, String nomeContato) {
		this.id = id;
		this.empresa = empresa;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone = telefone;
		this.nomeContato = nomeContato;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getNomeContato() {
		return nomeContato;
	}
	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}
	
}
