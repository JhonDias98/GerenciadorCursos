package br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursobi;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.gerenciador_cursos.curso.cursobi.CursoBI;
import br.com.gerenciador_cursos.curso.relacionamento.RelacionamentoCurso;
import br.com.gerenciador_cursos.curso.relacionamento.TipoCurso;
import br.com.gerenciador_cursos.disciplina.Disciplina;

@Entity
@Table(name = "cursoBI_Disciplina")
public class DisciplinaCursoBI extends RelacionamentoCurso {
	
	@OneToOne
	private CursoBI cursoBI;

	public DisciplinaCursoBI(Disciplina disciplina, TipoCurso tipoDoCurso, CursoBI cursoBI) {
		super(disciplina, tipoDoCurso);
		this.cursoBI = cursoBI;
	}
	
	@Deprecated
	public DisciplinaCursoBI() {}

	public CursoBI getCursoBI() {
		return cursoBI;
	}
	
}
