package br.com.gerenciador_cursos.curso.cursoce;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.gerenciador_cursos.curso.cursobi.BachareladoInterdiciplinar;

public class CursoEspecificoRequest {

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
	
	private Long cursoBI_id;

	public CursoEspecificoRequest(String nome, String codigoCurso, Integer livre, Integer limitada, Integer obrigatoria, Long cursoBI_id) {
		this.nome = nome;
		this.codigoCurso = codigoCurso;
		this.livre = livre;
		this.limitada = limitada;
		this.obrigatoria = obrigatoria;
		this.cursoBI_id = cursoBI_id;
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

	public Long getCursoBI_id() {
		return cursoBI_id;
	}

	public CursoEspecifico toModel(EntityManager manager) {
		BachareladoInterdiciplinar bachareladoInterdiciplinar = manager.find(BachareladoInterdiciplinar.class, cursoBI_id);
		return new CursoEspecifico(nome, codigoCurso, livre, limitada, obrigatoria, bachareladoInterdiciplinar);
	}
}
