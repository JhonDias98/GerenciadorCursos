package br.com.gerenciador_cursos.matricula.quadrimestre;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quadrimestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Cursada> cursadas = new ArrayList<>();

    public Quadrimestre(List<Cursada> cursadas) {
        this.cursadas = cursadas;
    }

    public Quadrimestre() {}

    public Long getId() {
        return id;
    }

    public List<Cursada> getCursadas() {
        return cursadas;
    }

    public void adicionarDisciplina(Cursada cursada) {
        this.cursadas.add(cursada);
    }
}
