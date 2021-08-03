package br.com.gerenciador_cursos.curso.bachareladointerdiciplinar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BachareladoInterdiciplinarRequestTest {

    BachareladoInterdiciplinarRequest request;

    @BeforeEach
    public void setup() {
        request = new BachareladoInterdiciplinarRequest("Teste BCT", "001", 10, 15, 20);
    }

    @Test
    @DisplayName("DEVE converter a requisicao para um objeto Bacharelado Interdiciplinar")
    void test1() {
        BachareladoInterdiciplinar bachareladoInterdiciplinar = request.toModel();

        assertEquals(request.getNome(), bachareladoInterdiciplinar.getNome());
        assertEquals(request.getCodigoCurso(), bachareladoInterdiciplinar.getCodigoCurso());
        assertEquals(request.getLivre(), bachareladoInterdiciplinar.getLivre());
        assertEquals(request.getLimitada(), bachareladoInterdiciplinar.getLimitada());
        assertEquals(request.getObrigatoria(), bachareladoInterdiciplinar.getObrigatoria());

    }

}