package br.com.gerenciador_cursos.curso.relacionamento;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import br.com.gerenciador_cursos.disciplina.Disciplina;

@MappedSuperclass
public abstract class RelacionamentoCurso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Disciplina disciplina;
	
	@Enumerated(EnumType.STRING)
	private TipoCurso tipoDoCurso;
	
	public RelacionamentoCurso(Disciplina disciplina, TipoCurso tipoDoCurso) {
		super();
		this.disciplina = disciplina;
		this.tipoDoCurso = tipoDoCurso;
	}

	public RelacionamentoCurso() {}

	public Long getId() {
		return id;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public TipoCurso getTipoDoCurso() {
		return tipoDoCurso;
	}
}
