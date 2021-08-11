package br.com.gerenciador_cursos.curso.bachareladointerdiciplinar;

import javax.persistence.*;

import br.com.gerenciador_cursos.curso.Curso;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar.DisciplinaBachareladoInterdiciplinar;

import java.util.List;
import java.util.Objects;

@Entity
public class BachareladoInterdiciplinar extends Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "bachareladoInterdiciplinar")
	private List<DisciplinaBachareladoInterdiciplinar> relacionamentos;

	public BachareladoInterdiciplinar(String nome, String codigo, Integer livre, Integer limitada, Integer obrigatoria) {
		super(nome, codigo, livre, limitada, obrigatoria);
	}

	public BachareladoInterdiciplinar() {}

	public Long getId() {
		return id;
	}

	public List<DisciplinaBachareladoInterdiciplinar> getRelacionamentos() {
		return relacionamentos;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BachareladoInterdiciplinar)) return false;
		BachareladoInterdiciplinar that = (BachareladoInterdiciplinar) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
