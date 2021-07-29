package br.com.gerenciador_cursos.disciplina;

public class DisciplinaResponse {

	private Long id;
	private String nome;

	public DisciplinaResponse(Disciplina disciplina) {
		this.id = disciplina.getId();
		this.nome = disciplina.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

}
