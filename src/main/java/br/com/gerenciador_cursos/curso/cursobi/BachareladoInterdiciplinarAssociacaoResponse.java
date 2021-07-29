package br.com.gerenciador_cursos.curso.cursobi;

import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursobi.DisciplinaBachareladoInterdiciplinar;

public class BachareladoInterdiciplinarAssociacaoResponse {

    private Long id;

    BachareladoInterdiciplinarAssociacaoResponse(DisciplinaBachareladoInterdiciplinar disciplinaBachareladoInterdiciplinar) {
        this.id = disciplinaBachareladoInterdiciplinar.getId();
    }

    public Long getId() {
        return id;
    }
}
