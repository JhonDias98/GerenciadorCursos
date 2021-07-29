package br.com.gerenciador_cursos.curso.bachareladointerdiciplinar;

import br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar.DisciplinaBachareladoInterdiciplinar;

public class BachareladoInterdiciplinarAssociacaoResponse {

    private Long id;

    BachareladoInterdiciplinarAssociacaoResponse(DisciplinaBachareladoInterdiciplinar disciplinaBachareladoInterdiciplinar) {
        this.id = disciplinaBachareladoInterdiciplinar.getId();
    }

    public Long getId() {
        return id;
    }
}
