package br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoce;

import br.com.gerenciador_cursos.curso.relacionamento.TipoCurso;
import br.com.gerenciador_cursos.disciplina.Disciplina;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DisciplinaCursoCEResponse {

	private Long id;
	private String nome;
	private Integer teoria;
	private Integer pratica;
	private Integer individual;
	private Integer creditos;
	private String tipo;

	public DisciplinaCursoCEResponse(DisciplinaCursoCE cursos) {
		this.id = cursos.getDisciplina().getId();
		this.nome = cursos.getDisciplina().getNome();
		this.teoria = cursos.getDisciplina().getTeoria();
		this.pratica = cursos.getDisciplina().getPratica();
		this.individual = cursos.getDisciplina().getIndividual();
		this.creditos = cursos.getDisciplina().getCreditos();
		this.tipo = cursos.getTipoDoCurso().toString().toLowerCase(Locale.ROOT);
	}

	public Long getId() {
		return id;
	}

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

	public Integer getCreditos() {
		return creditos;
	}

	public String getTipo() {
		return tipo;
	}
}
