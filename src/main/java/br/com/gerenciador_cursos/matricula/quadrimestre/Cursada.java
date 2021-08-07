package br.com.gerenciador_cursos.matricula.quadrimestre;

import br.com.gerenciador_cursos.curso.relacionamento.TipoCurso;
import br.com.gerenciador_cursos.disciplina.Disciplina;

import javax.persistence.*;

@Entity
public class Cursada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Disciplina disciplina;

    @Enumerated(value = EnumType.STRING)
    private TipoCurso tipo;

    public Cursada(Disciplina disciplina, TipoCurso tipo) {
        this.disciplina = disciplina;
        this.tipo = tipo;
    }

    public Cursada() {
    }

    public Long getId() {
        return id;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public TipoCurso getTipo() {
        return tipo;
    }


}
