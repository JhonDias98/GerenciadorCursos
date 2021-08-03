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

        assertEquals(disciplinaRequest.getNome(), novaDisciplina.getNome());
        assertEquals(disciplinaRequest.getTeoria(), novaDisciplina.getTeoria());
        assertEquals(disciplinaRequest.getPratica(), novaDisciplina.getPratica());
        assertEquals(disciplinaRequest.getIndividual(), novaDisciplina.getIndividual());
        assertEquals((disciplinaRequest.getTeoria() + disciplinaRequest.getPratica()), novaDisciplina.getCreditos());
    }

}