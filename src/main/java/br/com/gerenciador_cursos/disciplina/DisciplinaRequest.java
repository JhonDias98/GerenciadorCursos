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

	public String getNome() {
		return nome;
	}

	public Integer getTeoria() {
		return teoria;
	}

	public Integer getPratica() {
		return pratica;
	}

	public Integer getIndividual() {
		return individual;
	}

	public Disciplina toModel() {
		return new Disciplina(nome, teoria, pratica, individual);
	}
}
