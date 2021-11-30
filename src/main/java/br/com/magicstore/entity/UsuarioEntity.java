package br.com.magicstore.entity;

import javax.persistence.*;

@Entity
@Table(name = "usuario", schema = "bd_magic_store")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	
	private String nome;
	
	private String senha;
	
	private String cpf;
	
	private String rg;
	
	private String endereco;
	
	@ManyToOne
	@JoinColumn(name = "id_permissao")
	private PermissaoEntity permissao;
	
	private String idSessao;
	
	public UsuarioEntity() {
	}
	
	public UsuarioEntity(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public PermissaoEntity getPermissao() {
		return permissao;
	}

	public void setPermissao(PermissaoEntity permissao) {
		this.permissao = permissao;
	}
	
	public String getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}
	
}
