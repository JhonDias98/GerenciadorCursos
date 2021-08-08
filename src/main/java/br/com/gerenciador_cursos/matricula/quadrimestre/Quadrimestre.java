package br.com.gerenciador_cursos.matricula.quadrimestre;

import br.com.gerenciador_cursos.matricula.Matricula;
import br.com.gerenciador_cursos.matricula.quadrimestre.cursada.Cursada;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quadrimestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quadrimestre", orphanRemoval = true)
    private List<Cursada> cursadas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "matricula_id")
    private Matricula matricula;

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
