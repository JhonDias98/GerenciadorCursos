package br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoespecifico;

import javax.persistence.EntityManager;

import br.com.gerenciador_cursos.curso.cursoespecifico.CursoEspecifico;
import br.com.gerenciador_cursos.curso.relacionamento.TipoCurso;
import br.com.gerenciador_cursos.disciplina.Disciplina;

public class DisciplinaCursoEspecificoRequest {
	
	private Long disciplinaId;
	
	private TipoCurso tipoDoCurso;

	public DisciplinaCursoEspecificoRequest(Long disciplinaId, TipoCurso tipoDoCurso) {
		this.disciplinaId = disciplinaId;
		this.tipoDoCurso = tipoDoCurso;
	}

	public Long getDisciplinaId() {
		return disciplinaId;
	}
	
	public TipoCurso getTipoDoCurso() {
		return tipoDoCurso;
	}
	
	public DisciplinaCursoEspecifico toModel(EntityManager manager, Long cursoCEId) {
		CursoEspecifico cursoEspecifico = manager.find(CursoEspecifico.class, cursoCEId);
		Disciplina disciplina = manager.find(Disciplina.class, disciplinaId);
		
		return new DisciplinaCursoEspecifico(disciplina, tipoDoCurso, cursoEspecifico);
	}
	
}
