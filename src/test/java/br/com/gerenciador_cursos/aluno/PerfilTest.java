package br.com.gerenciador_cursos.aluno;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PerfilTest {

    @Test
    @DisplayName("DEVE retornar os dados do perfil")
    void test01() {
        Perfil perfil = new Perfil("ADMIN");
        assertEquals("ADMIN", perfil.getNome());
        assertEquals("ADMIN", perfil.getAuthority());
    }
}