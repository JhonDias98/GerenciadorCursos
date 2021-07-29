package br.com.gerenciador_cursos.matricula;

import javax.persistence.EntityManager;

import br.com.gerenciador_cursos.aluno.Aluno;
import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinar;
import br.com.gerenciador_cursos.curso.cursoespecifico.CursoEspecifico;

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
		return new Matricula(manager.find(Aluno.class, alunoId), manager.find(BachareladoInterdiciplinar.class, cursoId));
	}

	public Matricula toModelCE(EntityManager manager) {
		return new Matricula(manager.find(Aluno.class, alunoId), manager.find(CursoEspecifico.class, cursoId));
	}
}
