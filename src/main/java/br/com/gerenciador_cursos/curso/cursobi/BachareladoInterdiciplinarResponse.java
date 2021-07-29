package br.com.gerenciador_cursos.curso.cursobi;

public class BachareladoInterdiciplinarResponse {

    private Long id;
    private String nome;

    public BachareladoInterdiciplinarResponse(BachareladoInterdiciplinar bachareladoInterdiciplinar) {
        this.id = bachareladoInterdiciplinar.getId();
        this.nome = bachareladoInterdiciplinar.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
