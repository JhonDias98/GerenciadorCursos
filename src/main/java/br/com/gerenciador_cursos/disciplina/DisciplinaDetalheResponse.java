package br.com.gerenciador_cursos.disciplina;

public class DisciplinaDetalheResponse {

    private Long id;
    private String nome;
    private Integer teoria;
    private Integer pratica;
    private Integer individual;
    private Integer creditos;

    public DisciplinaDetalheResponse(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
        this.teoria = disciplina.getTeoria();
        this.pratica = disciplina.getPratica();
        this.individual = disciplina.getIndividual();
        this.creditos = disciplina.getCreditos();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getTeoria() {
        return teoria;
    }

    public Integer getPratica() {
        return pratica;
    }

    public Integer getIndividual() {
        return individual;
    }

    public Integer getCreditos() {
        return creditos;
    }

}
