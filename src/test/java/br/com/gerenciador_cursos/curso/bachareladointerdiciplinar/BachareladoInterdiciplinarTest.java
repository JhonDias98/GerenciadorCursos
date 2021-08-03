package br.com.gerenciador_cursos.curso.bachareladointerdiciplinar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BachareladoInterdiciplinarTest {

    BachareladoInterdiciplinar cursoBI;

    @BeforeEach
    public void setup() {
        cursoBI = new BachareladoInterdiciplinar("Teste BCT", "001", 10, 15, 20);
    }

    @Test
    @DisplayName("DEVE retornar os dados do Bacharelado Interdiciplinar")
    void test01() {
        assertEquals("Teste BCT", cursoBI.getNome());
        assertEquals("001", cursoBI.getCodigoCurso());
        assertEquals(10, cursoBI.getLivre());
        assertEquals(15, cursoBI.getLimitada());
        assertEquals(20, cursoBI.getObrigatoria());
    }
}