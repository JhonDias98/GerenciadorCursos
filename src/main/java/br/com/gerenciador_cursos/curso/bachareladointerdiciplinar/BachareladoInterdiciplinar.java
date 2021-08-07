package br.com.gerenciador_cursos.curso.bachareladointerdiciplinar;

import javax.persistence.*;

import br.com.gerenciador_cursos.curso.Curso;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar.DisciplinaBachareladoInterdiciplinar;

import java.util.Objects;

@Entity
public class BachareladoInterdiciplinar extends Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "bachareladoInterdiciplinar")
	private DisciplinaBachareladoInterdiciplinar cursoBIRelacionado;

	public BachareladoInterdiciplinar(String nome, String codigo, Integer livre, Integer limitada, Integer obrigatoria) {
		super(nome, codigo, livre, limitada, obrigatoria);
	}

	public BachareladoInterdiciplinar() {}

	public Long getId() {
		return id;
	}

	public DisciplinaBachareladoInterdiciplinar getCursoBIRelacionado() {
		return cursoBIRelacionado;
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
