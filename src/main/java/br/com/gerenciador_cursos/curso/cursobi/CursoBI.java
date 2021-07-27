package br.com.gerenciador_cursos.curso.cursobi;

import javax.persistence.*;

import br.com.gerenciador_cursos.curso.Curso;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursobi.DisciplinaCursoBI;

@Entity
public class CursoBI extends Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "cursoBI")
	private DisciplinaCursoBI cursoBIRelacionado;

	public CursoBI(String nome, String codigoCurso, Integer livre, Integer limitada, Integer obrigatoria) {
		super(nome, codigoCurso, livre, limitada, obrigatoria);
	}

	public CursoBI() {}

	public Long getId() {
		return id;
	}

	public DisciplinaCursoBI getCursoBIRelacionado() {
		return cursoBIRelacionado;
	}
}
