package br.com.gerenciador_cursos.curso.cursoespecifico;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinar;

public class CursoEspecificoRequest {

	@NotBlank
	private String nome;
	@NotBlank
	private String codigo;
	@NotNull
	private Integer livre;
	@NotNull
	private Integer limitada;
	@NotNull
	private Integer obrigatoria;
	
	private Long cursoBI_id;

	public CursoEspecificoRequest(String nome, String codigo, Integer livre, Integer limitada, Integer obrigatoria, Long cursoBI_id) {
		this.nome = nome;
		this.codigo = codigo;
		this.livre = livre;
		this.limitada = limitada;
		this.obrigatoria = obrigatoria;
		this.cursoBI_id = cursoBI_id;
	}

	public CursoEspecificoRequest() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getLivre() {
		return livre;
	}

	public void setLivre(Integer livre) {
		this.livre = livre;
	}

	public Integer getLimitada() {
		return limitada;
	}

	public void setLimitada(Integer limitada) {
		this.limitada = limitada;
	}

	public Integer getObrigatoria() {
		return obrigatoria;
	}

	public void setObrigatoria(Integer obrigatoria) {
		this.obrigatoria = obrigatoria;
	}

	public Long getCursoBI_id() {
		return cursoBI_id;
	}

	public void setCursoBI_id(Long cursoBI_id) {
		this.cursoBI_id = cursoBI_id;
	}

	public CursoEspecifico toModel(EntityManager manager) {
		BachareladoInterdiciplinar bachareladoInterdiciplinar = manager.find(BachareladoInterdiciplinar.class, cursoBI_id);
		return new CursoEspecifico(nome, codigo, livre, limitada, obrigatoria, bachareladoInterdiciplinar);
	}
}
