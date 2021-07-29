package br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoespecifico;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.gerenciador_cursos.curso.cursoespecifico.CursoEspecifico;
import br.com.gerenciador_cursos.curso.relacionamento.RelacionamentoCurso;
import br.com.gerenciador_cursos.curso.relacionamento.TipoCurso;
import br.com.gerenciador_cursos.disciplina.Disciplina;

@Entity
@Table(name = "cursoCE_Disciplina")
public class DisciplinaCursoEspecifico extends RelacionamentoCurso {

	@OneToOne
	private CursoEspecifico cursoEspecifico;

	public DisciplinaCursoEspecifico(Disciplina disciplina, TipoCurso tipoDoCurso, CursoEspecifico cursoEspecifico) {
		super(disciplina, tipoDoCurso);
		this.cursoEspecifico = cursoEspecifico;
	}
	
	@Deprecated
	public DisciplinaCursoEspecifico() {}

	public CursoEspecifico getCursoCE() {
		return cursoEspecifico;
	}
	
}
