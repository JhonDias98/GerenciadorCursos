package br.com.gerenciador_cursos.curso.bachareladointerdiciplinar;

import javax.persistence.*;

import br.com.gerenciador_cursos.curso.Curso;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar.DisciplinaBachareladoInterdiciplinar;

@Entity
public class BachareladoInterdiciplinar extends Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "bachareladoInterdiciplinar")
	private DisciplinaBachareladoInterdiciplinar cursoBIRelacionado;

	public BachareladoInterdiciplinar(String nome, String codigoCurso, Integer livre, Integer limitada, Integer obrigatoria) {
		super(nome, codigoCurso, livre, limitada, obrigatoria);
	}

	public BachareladoInterdiciplinar() {}

	public Long getId() {
		return id;
	}

	public DisciplinaBachareladoInterdiciplinar getCursoBIRelacionado() {
		return cursoBIRelacionado;
	}
}
