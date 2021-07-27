package br.com.gerenciador_cursos.matricula;

import javax.persistence.EntityManager;

import br.com.gerenciador_cursos.aluno.Aluno;
import br.com.gerenciador_cursos.curso.Curso;
import br.com.gerenciador_cursos.curso.cursobi.CursoBI;
import br.com.gerenciador_cursos.curso.cursoce.CursoCE;

public class MatriculaRequest {

	private Long alunoId;
	
	private Long cursoId;
	
	public MatriculaRequest(Long alunoId, Long cursoId) {
		this.alunoId = alunoId;
		this.cursoId = cursoId;
	}

	public Long getAlunoId() {
		return alunoId;
	}

	public Long getCursoId() {
		return cursoId;
	}
	
	public Matricula toModelBI(EntityManager manager) {
		return new Matricula(manager.find(Aluno.class, alunoId), manager.find(CursoBI.class, cursoId));
	}

	public Matricula toModelCE(EntityManager manager) {
		return new Matricula(manager.find(Aluno.class, alunoId), manager.find(CursoCE.class, cursoId));
	}
}
