package br.com.gerenciador_cursos.curso.cursoespecifico;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.gerenciador_cursos.curso.Curso;
import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinar;

import java.util.Objects;

@Entity
public class CursoEspecifico extends Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private BachareladoInterdiciplinar bachareladoInterdiciplinar;
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
