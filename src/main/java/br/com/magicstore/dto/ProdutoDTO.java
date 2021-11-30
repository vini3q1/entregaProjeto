package br.com.magicstore.dto;

public class ProdutoDTO {

	private Long id;
	private String nome;
	private String descricao;
	private CategoriaResponse categoria;
	private FornecedorDTO fornecedor;
	private Long quantidade;
	private double valor;
	private String imagem;

	public ProdutoDTO() {
		
	}
	
	public ProdutoDTO(Long id, String nome, String descricao, CategoriaResponse categoria, FornecedorDTO fornecedor,
			Long quantidade, double valor, String imagem) {

		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = categoria;
		this.fornecedor = fornecedor;
		this.quantidade = quantidade;
		this.valor = valor;
		this.imagem = imagem;
		
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

	public CategoriaResponse getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaResponse categoria) {
		this.categoria = categoria;
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

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

}
