package br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoce;

import javax.persistence.EntityManager;

import br.com.gerenciador_cursos.curso.cursoce.CursoCE;
import br.com.gerenciador_cursos.curso.relacionamento.TipoCurso;
import br.com.gerenciador_cursos.disciplina.Disciplina;

public class DisciplinaCursoCERequest {
	
	private Long disciplinaId;
	
	private TipoCurso tipoDoCurso;

	public Long getDisciplinaId() {
		return disciplinaId;
	}
	
	public TipoCurso getTipoDoCurso() {
		return tipoDoCurso;
	}
	
	public DisciplinaCursoCE toModel(EntityManager manager, Long cursoCEId) {
		CursoCE cursoCE = manager.find(CursoCE.class, cursoCEId);
		Disciplina disciplina = manager.find(Disciplina.class, disciplinaId);
		
		return new DisciplinaCursoCE(disciplina, tipoDoCurso, cursoCE);
	}
	
}
