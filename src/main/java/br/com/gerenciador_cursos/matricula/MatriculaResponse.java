package br.com.gerenciador_cursos.matricula;

public class MatriculaResponse {

    private Long id;

    MatriculaResponse(Matricula matricula) {
        this.id = matricula.getId();
    }

    public Long getId() {
        return id;
    }
}
