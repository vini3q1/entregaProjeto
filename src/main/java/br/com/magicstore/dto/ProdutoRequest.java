package br.com.magicstore.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProdutoRequest {

	private Long id;
	private String nome;
	private String descricao;
	private Long idCategoria;
	private FornecedorDTO fornecedor;
	private Long quantidade;
	private double valor;
//	private MultipartFile imagem;

	public ProdutoRequest() {
		
	}
	
	public ProdutoRequest(Long id, String nome, String descricao, Long idCategoria, FornecedorDTO fornecedor,
			Long quantidade, double valor, MultipartFile imagem) {

		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.fornecedor = fornecedor;
		this.quantidade = quantidade;
		this.valor = valor;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public FornecedorDTO getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorDTO fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

//	public MultipartFile getImagem() {
//		return imagem;
//	}
//
//	public void setImagem(MultipartFile imagem) {
//		this.imagem = imagem;
//	}

}
