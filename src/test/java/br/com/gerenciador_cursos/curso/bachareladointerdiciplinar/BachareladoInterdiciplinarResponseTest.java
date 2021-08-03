package br.com.gerenciador_cursos.curso.bachareladointerdiciplinar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BachareladoInterdiciplinarResponseTest {

    BachareladoInterdiciplinar cursoBI;

    @BeforeEach
    public void setup() {
        cursoBI = new BachareladoInterdiciplinar("Teste BCT", "001", 10, 15, 20);
    }

    @Test
    @DisplayName("DEVE retornar o nome do curso")
    void test01() {
        BachareladoInterdiciplinarResponse response = new BachareladoInterdiciplinarResponse(cursoBI);

        assertEquals(cursoBI.getNome(), response.getNome());
    }

}