package br.com.gerenciador_cursos.matricula;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.gerenciador_cursos.aluno.Aluno;
import br.com.gerenciador_cursos.curso.Curso;
import br.com.gerenciador_cursos.curso.cursobi.CursoBI;
import br.com.gerenciador_cursos.curso.cursoce.CursoCE;

@Entity
public class Matricula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "fk_aluno_id")
	private Aluno aluno;

	@OneToOne
	private CursoBI cursoBI;

	@OneToOne
	private CursoCE cursoCE;
	
	public Matricula(Aluno aluno, CursoBI cursoBI) {
		this.aluno = aluno;
		this.cursoBI = cursoBI;
	}

	public Matricula(Aluno aluno, CursoCE cursoCE) {
		this.aluno = aluno;
		this.cursoCE = cursoCE;
	}

	@Deprecated
	public Matricula() {}

	public Long getId() {
		return id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public CursoBI getCursoBI() {
		return cursoBI;
	}

	public CursoCE getCursoCE() {
		return cursoCE;
	}
}
