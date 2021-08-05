package br.com.gerenciador_cursos.curso.cursoespecifico;

import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CursoEspecificoRequestTest {

    CursoEspecificoRequest request;
    BachareladoInterdiciplinar cursoBI;

    EntityManager manager = mock(EntityManager.class);

    @BeforeEach
    public void setup() {
        request = new CursoEspecificoRequest("Teste BCT", "001", 10, 15, 20, Long.valueOf(1));
        cursoBI = new BachareladoInterdiciplinar("Teste BCT", "001", 10, 15, 20);
    }

    @Test
    @DisplayName("DEVE converter a requisicao para um objeto Curso Especifico")
    void test1() {

        when(manager.find(BachareladoInterdiciplinar.class, request.getCursoBI_id())).thenReturn(cursoBI);

        CursoEspecifico cursoEspecifico = request.toModel(manager);

        assertEquals(request.getNome(), cursoEspecifico.getNome());
        assertEquals(request.getCodigo(), cursoEspecifico.getCodigo());
        assertEquals(request.getLivre(), cursoEspecifico.getLivre());
        assertEquals(request.getLimitada(), cursoEspecifico.getLimitada());
        assertEquals(request.getObrigatoria(), cursoEspecifico.getObrigatoria());
    }
}