package br.com.gerenciador_cursos.curso.cursoce;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.gerenciador_cursos.curso.Curso;
import br.com.gerenciador_cursos.curso.cursobi.BachareladoInterdiciplinar;

@Entity
public class CursoEspecifico extends Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private BachareladoInterdiciplinar bachareladoInterdiciplinarID;
	public CursoEspecifico(String nome, String codigoCurso, Integer livre, Integer limitada, Integer obrigatoria, BachareladoInterdiciplinar bachareladoInterdiciplinarID) {
		super(nome, codigoCurso, livre, limitada, obrigatoria);
		this.bachareladoInterdiciplinarID = bachareladoInterdiciplinarID;
	}

	@Deprecated
	public CursoEspecifico() {}

	public Long getId() {
		return id;
	}

	public BachareladoInterdiciplinar getCursoBiID() {
		return bachareladoInterdiciplinarID;
	}
	
}
