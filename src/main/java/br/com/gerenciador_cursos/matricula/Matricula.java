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
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
public class Matricula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@NotNull
	private Aluno aluno;

	@ManyToOne
	private BachareladoInterdiciplinar bachareladoInterdiciplinar;

	@ManyToOne
	private CursoEspecifico cursoEspecifico;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Quadrimestre> quadrimestres = new ArrayList<>();

	@NotNull
	private Integer livre;

	@NotNull
	private Integer limitada;

	@NotNull
	private Integer obrigatoriaCE;

	@NotNull
	private Integer obrigatoriaBi;

	public Matricula(Aluno aluno, BachareladoInterdiciplinar bachareladoInterdiciplinar) {
		this.aluno = aluno;
		this.bachareladoInterdiciplinar = bachareladoInterdiciplinar;
		this.livre = 0;
		this.limitada = 0;
		this.obrigatoriaCE = 0;
		this.obrigatoriaBi = 0;
	}

	public Matricula(Aluno aluno, CursoEspecifico cursoEspecifico) {
		this.aluno = aluno;
		this.cursoEspecifico = cursoEspecifico;
		this.livre = 0;
		this.limitada = 0;
		this.obrigatoriaCE = 0;
		this.obrigatoriaBi = 0;
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

	public void setLivre(Integer livre) {
		this.livre = livre;
	}

	public Integer getLimitada() {
		return limitada;
	}

	public void setLimitada(Integer limitada) {
		this.limitada = limitada;
	}

	public Integer getObrigatoriaCE() {
		return obrigatoriaCE;
	}

	public void setObrigatoriaCE(Integer obrigatoriaCE) {
		this.obrigatoriaCE = obrigatoriaCE;
	}

	public Integer getObrigatoriaBi() {
		return obrigatoriaBi;
	}

	public void setObrigatoriaBi(Integer obrigatoriaBi) {
		this.obrigatoriaBi = obrigatoriaBi;
	}

	public CursoEspecifico getCursoEspecifico() {
		return cursoEspecifico;
	}

	public List<Quadrimestre> getQuadrimestres() {
		return quadrimestres;
	}

	public Quadrimestre buscarQuadrimestre(Long id) {
		for(Quadrimestre quadrimestre : quadrimestres) {
			if(quadrimestre.getId() == id) return quadrimestre;
		}
		return null;
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

	public static <T> Collector<T, ?, T> toSingleton() {
		return Collectors.collectingAndThen(
				Collectors.toList(),
				list -> {
					if (list.size() != 1) {
						throw new IllegalStateException();
					}
					return list.get(0);
				}
		);
	}

	@Override
	public String toString() {
		return "Matricula{" +
				"id=" + id +
				", aluno=" + aluno +
				", quadrimestres=" + quadrimestres +
				'}';
	}
}
