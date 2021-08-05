package br.com.gerenciador_cursos.curso.cursoespecifico;

import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CursoEspecificoTest {

    CursoEspecifico cursoEspecifico;
    BachareladoInterdiciplinar cursoBI;

    @BeforeEach
    public void setup() {
        cursoBI = new BachareladoInterdiciplinar("Teste BCT", "001", 10, 15, 20);
        cursoEspecifico = new CursoEspecifico("Engenharia", "001E", 10, 15, 20, cursoBI);
    }

    @Test
    @DisplayName("DEVE retornar os dados do Curso Especifico")
    void test01() {
        assertEquals("Engenharia", cursoEspecifico.getNome());
        assertEquals("001E", cursoEspecifico.getCodigo());
        assertEquals(10, cursoEspecifico.getLivre());
        assertEquals(15, cursoEspecifico.getLimitada());
        assertEquals(20, cursoEspecifico.getObrigatoria());
        assertEquals(cursoBI, cursoEspecifico.getBachareladoInterdiciplinar());
    }
}