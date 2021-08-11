package br.com.gerenciador_cursos.matricula.quadrimestre;

import br.com.gerenciador_cursos.disciplina.Disciplina;
import br.com.gerenciador_cursos.matricula.Matricula;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Quadrimestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Disciplina> disciplinas = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Matricula matricula;

    public Quadrimestre(List<Disciplina> disciplinas, Matricula matricula) {
        this.disciplinas = disciplinas;
        this.matricula = matricula;
    }

    public Quadrimestre() {}

    public Long getId() {
        return id;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    @Override
    public String toString() {
        return "Quadrimestre{" +
                "id=" + id +
                ", disciplinas=" + disciplinas +
                ", matricula=" + matricula +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quadrimestre)) return false;
        Quadrimestre that = (Quadrimestre) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
