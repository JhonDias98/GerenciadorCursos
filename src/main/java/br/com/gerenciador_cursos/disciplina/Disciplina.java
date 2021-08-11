package br.com.gerenciador_cursos.disciplina;

import br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar.DisciplinaBachareladoInterdiciplinar;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoespecifico.DisciplinaCursoEspecifico;
import br.com.gerenciador_cursos.matricula.quadrimestre.Quadrimestre;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	private Integer teoria;
	
	@NotNull
	private Integer pratica;
	
	@NotNull
	private Integer individual;
	
	@NotNull
	private Integer creditos;

	@OneToMany(mappedBy = "disciplina")
	private List<DisciplinaBachareladoInterdiciplinar> cursoBIRelacionado;

	@OneToMany(mappedBy = "disciplina")
	private List<DisciplinaCursoEspecifico> cursoCERelacionado;
	
	public Disciplina(String nome, Integer teoria, Integer pratica, Integer individual) {
		this.nome = nome;
		this.teoria = teoria;
		this.pratica = pratica;
		this.individual = individual;
		this.creditos = pratica + teoria;
	}

	@Deprecated
	public Disciplina() {}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public Integer getTeoria() {
		return teoria;
	}
	
	public Integer getPratica() {
		return pratica;
	}
	
	public Integer getIndividual() {
		return individual;
	}
	
	public Integer getCreditos() {
		return creditos;
	}

	public List<DisciplinaBachareladoInterdiciplinar> getCursoBIRelacionado() {
		return cursoBIRelacionado;
	}

	public List<DisciplinaCursoEspecifico> getCursoCERelacionado() {
		return cursoCERelacionado;
	}

	@Override
	public String toString() {
		return "Disciplina{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", teoria=" + teoria +
				", pratica=" + pratica +
				", individual=" + individual +
				", creditos=" + creditos +
				", cursoBIRelacionado=" + cursoBIRelacionado +
				", cursoCERelacionado=" + cursoCERelacionado +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Disciplina)) return false;
		Disciplina that = (Disciplina) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
