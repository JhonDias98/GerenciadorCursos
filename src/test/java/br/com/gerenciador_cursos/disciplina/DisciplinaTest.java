package br.com.gerenciador_cursos.disciplina;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisciplinaTest {

    private Disciplina disciplina;

    @BeforeEach
    public void setup() {
        disciplina = new Disciplina("Engenharia", 2, 1, 2);
    }

    @Test
    @DisplayName("DEVE criar um objeto disciplina")
    void test1() {
        assertEquals(disciplina.getNome(), "Engenharia");
        assertEquals(disciplina.getTeoria(), 2);
        assertEquals(disciplina.getPratica(), 1);
        assertEquals(disciplina.getIndividual(), 2);
        assertEquals(disciplina.getCreditos(), (disciplina.getTeoria() + disciplina.getPratica()));
    }
}