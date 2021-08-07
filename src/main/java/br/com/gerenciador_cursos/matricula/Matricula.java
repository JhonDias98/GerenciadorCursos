package br.com.gerenciador_cursos.matricula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import br.com.gerenciador_cursos.aluno.Aluno;
import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinar;
import br.com.gerenciador_cursos.curso.cursoespecifico.CursoEspecifico;
import br.com.gerenciador_cursos.matricula.quadrimestre.Quadrimestre;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Matricula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Aluno aluno;

	@OneToOne
	private BachareladoInterdiciplinar bachareladoInterdiciplinar;

	@OneToOne
	private CursoEspecifico cursoEspecifico;

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Quadrimestre> quadrimestres = new ArrayList<>();

	@NotNull
	private Integer livre;

	@NotNull
	private Integer limitada;

	@NotNull
	private Integer obrigatoria;

	public Matricula(Aluno aluno, BachareladoInterdiciplinar bachareladoInterdiciplinar) {
		this.aluno = aluno;
		this.bachareladoInterdiciplinar = bachareladoInterdiciplinar;
		this.livre = 0;
		this.limitada = 0;
		this.obrigatoria = 0;
	}

	public Matricula(Aluno aluno, CursoEspecifico cursoEspecifico) {
		this.aluno = aluno;
		this.cursoEspecifico = cursoEspecifico;
		this.livre = 0;
		this.limitada = 0;
		this.obrigatoria = 0;
	}

	@Deprecated
	public Matricula() {}

	public Long getId() {
		return id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public BachareladoInterdiciplinar getBachareladoInterdiciplinar() {
		return bachareladoInterdiciplinar;
	}

	public Integer getLivre() {
		return livre;
	}

	public Integer getLimitada() {
		return limitada;
	}

	public Integer getObrigatoria() {
		return obrigatoria;
	}

	public CursoEspecifico getCursoEspecifico() {
		return cursoEspecifico;
	}

	public List<Quadrimestre> getQuadrimestres() {
		return quadrimestres;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Matricula)) return false;
		Matricula matricula = (Matricula) o;
		return id.equals(matricula.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public void criarQuadrimestre(Quadrimestre quadrimestres) {
		this.quadrimestres.add(quadrimestres);
	}
}
