package br.com.magicstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modalidade_pagamento")
public class ModalidadePagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Column(name = "quantidade_parcela")
	private Long quantidadeParcela;
	
	public ModalidadePagamento() {
	}
	
	public ModalidadePagamento(Long id) {
		this.id = id;
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

	public Long getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(Long quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}
	
}
