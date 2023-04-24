package com.appApi.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "Produto")
@EqualsAndHashCode @ToString
public class produtos implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long key;
	
	@Column(name = "nome", nullable = false, length = 150)
	private String nome;
	
	@Column(name = "marca", nullable = false, length = 150)
	private String marca;
	
	@Column(name = "categoria", nullable = false, length = 80)
	private String categoria;
	
	@Column(name = "fabricacao", nullable = true, length = 20)
	private String fabricacao;
	
	@Column(name = "validade", nullable = true, length = 20)
	private String validade;
	
	@Column(name = "preco", nullable = false)
	private double preco;

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getFabricacao() {
		return fabricacao;
	}

	public void setFabricacao(String fabricacao) {
		this.fabricacao = fabricacao;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	
	
	
	
}
