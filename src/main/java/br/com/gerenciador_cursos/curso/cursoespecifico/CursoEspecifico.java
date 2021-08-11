package br.com.gerenciador_cursos.curso.cursoespecifico;

import javax.persistence.*;

import br.com.gerenciador_cursos.curso.Curso;
import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinar;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoespecifico.DisciplinaCursoEspecifico;

import java.util.List;
import java.util.Objects;

@Entity
public class CursoEspecifico extends Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private BachareladoInterdiciplinar bachareladoInterdiciplinar;

	@OneToMany(mappedBy = "cursoEspecifico")
	private List<DisciplinaCursoEspecifico> relacionamentos;

	public CursoEspecifico(String nome, String codigo, Integer livre, Integer limitada, Integer obrigatoria, BachareladoInterdiciplinar bachareladoInterdiciplinar) {
		super(nome, codigo, livre, limitada, obrigatoria);
		this.bachareladoInterdiciplinar = bachareladoInterdiciplinar;
	}

	@Deprecated
	public CursoEspecifico() {}

	public Long getId() {
		return id;
	}

	public BachareladoInterdiciplinar getBachareladoInterdiciplinar() {
		return bachareladoInterdiciplinar;
	}

	public List<DisciplinaCursoEspecifico> getRelacionamentos() {
		return relacionamentos;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CursoEspecifico)) return false;
		CursoEspecifico that = (CursoEspecifico) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
