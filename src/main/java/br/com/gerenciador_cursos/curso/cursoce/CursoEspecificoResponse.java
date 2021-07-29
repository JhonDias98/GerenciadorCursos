package br.com.gerenciador_cursos.curso.cursoce;

public class CursoEspecificoResponse {

    private Long id;
    private String nome;

    public CursoEspecificoResponse(CursoEspecifico cursoEspecifico) {
        this.id = cursoEspecifico.getId();
        this.nome = cursoEspecifico.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
