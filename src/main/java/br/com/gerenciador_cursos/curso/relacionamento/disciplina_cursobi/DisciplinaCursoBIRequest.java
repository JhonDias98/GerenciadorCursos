package br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursobi;

import javax.persistence.EntityManager;

import br.com.gerenciador_cursos.curso.cursobi.CursoBI;
import br.com.gerenciador_cursos.curso.relacionamento.TipoCurso;
import br.com.gerenciador_cursos.disciplina.Disciplina;

public class DisciplinaCursoBIRequest {

	private Long disciplinaId;
	
	private TipoCurso tipoDoCurso;

	public Long getDisciplinaId() {
		return disciplinaId;
	}
	
	public TipoCurso getTipoDoCurso() {
		return tipoDoCurso;
	}

	public DisciplinaCursoBI toModel(EntityManager manager, Long cursoBIId) {
		CursoBI cursoBI = manager.find(CursoBI.class, cursoBIId);
		Disciplina disciplina = manager.find(Disciplina.class, disciplinaId);
		
		return new DisciplinaCursoBI(disciplina, tipoDoCurso, cursoBI);
	}
	
}
