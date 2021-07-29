package br.com.gerenciador_cursos.disciplina;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisciplinaRequestTest {

    private DisciplinaRequest disciplinaRequest;

    @BeforeEach
    public void setup() {
        disciplinaRequest = new DisciplinaRequest("Engenharia", 2, 1, 2);
    }

    @Test
    @DisplayName("DEVE converter a requisicao para um objeto disciplina")
    void test1() {
        Disciplina novaDisciplina = disciplinaRequest.toModel();

        assertEquals(novaDisciplina.getNome(), disciplinaRequest.getNome());
        assertEquals(novaDisciplina.getTeoria(), disciplinaRequest.getTeoria());
        assertEquals(novaDisciplina.getPratica(), disciplinaRequest.getPratica());
        assertEquals(novaDisciplina.getIndividual(), disciplinaRequest.getIndividual());
        assertEquals(novaDisciplina.getCreditos(), (disciplinaRequest.getTeoria() + disciplinaRequest.getPratica()));
    }

}