package br.com.gerenciador_cursos.curso.cursoce;

import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoce.DisciplinaCursoEspecifico;

public class CursoEspecificoAssociacaoResponse {

    private Long id;

    CursoEspecificoAssociacaoResponse(DisciplinaCursoEspecifico disciplinaCursoEspecifico) {
        this.id = disciplinaCursoEspecifico.getId();
    }

    public Long getId() {
        return id;
    }
}
