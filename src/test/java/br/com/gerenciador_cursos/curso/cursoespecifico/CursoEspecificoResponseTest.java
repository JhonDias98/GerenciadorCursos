package br.com.gerenciador_cursos.curso.cursoespecifico;

import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CursoEspecificoResponseTest {

    CursoEspecifico cursoEspecifico;
    BachareladoInterdiciplinar cursoBI;

    @BeforeEach
    public void setup() {
        cursoBI = new BachareladoInterdiciplinar("Teste BCT", "001", 10, 15, 20);
        cursoEspecifico = new CursoEspecifico("Engenharia", "001E", 10, 15, 20, cursoBI);
    }

    @Test
    @DisplayName("DEVE retornar o nome do curso")
    void test01() {
        CursoEspecificoResponse response = new CursoEspecificoResponse(cursoEspecifico);

        assertEquals(cursoEspecifico.getNome(), response.getNome());
    }

}