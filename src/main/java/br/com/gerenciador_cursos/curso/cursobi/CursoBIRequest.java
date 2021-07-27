package br.com.gerenciador_cursos.curso.cursobi;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CursoBIRequest {

	@NotBlank
	private String nome;
	@NotBlank
	private String codigoCurso;
	@NotNull
	private Integer livre;
	@NotNull
	private Integer limitada;
	@NotNull
	private Integer obrigatoria;

	public CursoBIRequest(String nome, String codigoCurso, Integer livre, Integer limitada, Integer obrigatoria) {
		this.nome = nome;
		this.codigoCurso = codigoCurso;
		this.livre = livre;
		this.limitada = limitada;
		this.obrigatoria = obrigatoria;
	}

	public String getNome() {
		return nome;
	}

	public String getCodigoCurso() {
		return codigoCurso;
	}

	public Integer getLivre() {
		return livre;
	}

	public Integer getLimitada() {
		return limitada;
	}

	public Integer getObrigatoria() {
		return obrigatoria;
	}

	public CursoBI toModel() {
		return new CursoBI(nome, codigoCurso, livre, limitada, obrigatoria);
	}
}
