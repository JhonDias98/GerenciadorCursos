package br.com.gerenciador_cursos.curso.cursoce;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.gerenciador_cursos.curso.Curso;
import br.com.gerenciador_cursos.curso.cursobi.CursoBI;

@Entity
public class CursoCE extends Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private CursoBI cursoBiID;
	public CursoCE(String nome, String codigoCurso, Integer livre, Integer limitada, Integer obrigatoria, CursoBI cursoBiID) {
		super(nome, codigoCurso, livre, limitada, obrigatoria);
		this.cursoBiID = cursoBiID;
	}

	@Deprecated
	public CursoCE() {}

	public Long getId() {
		return id;
	}

	public CursoBI getCursoBiID() {
		return cursoBiID;
	}
	
}
