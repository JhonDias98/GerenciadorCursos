package br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursobi;

import javax.persistence.EntityManager;

import br.com.gerenciador_cursos.curso.cursobi.BachareladoInterdiciplinar;
import br.com.gerenciador_cursos.curso.relacionamento.TipoCurso;
import br.com.gerenciador_cursos.disciplina.Disciplina;

public class DisciplinaBachareladoInterdiciplinarRequest {

	private Long disciplinaId;
	
	private TipoCurso tipoDoCurso;

	public DisciplinaBachareladoInterdiciplinarRequest(Long disciplinaId, TipoCurso tipoDoCurso) {
		this.disciplinaId = disciplinaId;
		this.tipoDoCurso = tipoDoCurso;
	}

	public Long getDisciplinaId() {
		return disciplinaId;
	}
	
	public TipoCurso getTipoDoCurso() {
		return tipoDoCurso;
	}

	public DisciplinaBachareladoInterdiciplinar toModel(EntityManager manager, Long cursoBIId) {
		BachareladoInterdiciplinar bachareladoInterdiciplinar = manager.find(BachareladoInterdiciplinar.class, cursoBIId);
		Disciplina disciplina = manager.find(Disciplina.class, disciplinaId);
		
		return new DisciplinaBachareladoInterdiciplinar(disciplina, tipoDoCurso, bachareladoInterdiciplinar);
	}
	
}
