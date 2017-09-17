package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.example.demo.model.Fabricante;

@Entity
public class Modelo {
	
	@Id
	@GeneratedValue
	private Long idModelo;

	@ManyToOne
	@JoinColumn(name = "id_fabricante")
	@NotNull(message="Fabricante obrigatório!")
	private Fabricante fabricante;

	@NotEmpty(message="Nome obrigatório!")
	private String nome;
	
	@NotEmpty(message="Descrição obrigatória")
	private String descricao;

	public Long getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Long idModelo) {
		this.idModelo = idModelo;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
