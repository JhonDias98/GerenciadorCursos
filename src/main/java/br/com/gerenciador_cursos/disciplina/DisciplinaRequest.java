package br.com.gerenciador_cursos.disciplina;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DisciplinaRequest {

	@NotBlank
	private String nome;
	@NotNull
	private Integer teoria;
	@NotNull
	private Integer pratica;
	@NotNull
	private Integer individual;

	public DisciplinaRequest(String nome, Integer teoria, Integer pratica, Integer individual) {
		this.nome = nome;
		this.teoria = teoria;
		this.pratica = pratica;
		this.individual = individual;
	}

	public DisciplinaRequest() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTeoria() {
		return teoria;
	}

	public void setTeoria(Integer teoria) {
		this.teoria = teoria;
	}

	public Integer getPratica() {
		return pratica;
	}

	public void setPratica(Integer pratica) {
		this.pratica = pratica;
	}

	public Integer getIndividual() {
		return individual;
	}

	public void setIndividual(Integer individual) {
		this.individual = individual;
	}

	public Disciplina toModel() {
		return new Disciplina(nome, teoria, pratica, individual);
	}
}
