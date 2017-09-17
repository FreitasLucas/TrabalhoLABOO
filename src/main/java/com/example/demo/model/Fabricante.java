package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import com.example.demo.model.Modelo;

@Entity
public class Fabricante {	
	@Id
	@GeneratedValue
	@Column(name="id_fabricante")
	private Long idFabricante;
	
	@NotEmpty(message="Nome obrigatório!")
	private String nome;
	
	@OneToMany(mappedBy = "fabricante", cascade = CascadeType.ALL)
	private Set<Modelo> modelos;

	public Long getIdFabricante() {
		return idFabricante;
	}

	public void setIdFabricante(Long idFabricante) {
		this.idFabricante = idFabricante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(Set<Modelo> modelos) {
		this.modelos = modelos;
	}
	
	
}
