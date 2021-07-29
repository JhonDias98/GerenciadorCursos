package br.com.gerenciador_cursos.aluno;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AlunoRequestTest {
    private AlunoRequest alunoRequest;
    private Perfil perfil;
    private EntityManager manager = mock(EntityManager.class);

    @BeforeEach
    public void setup() {
        alunoRequest = new AlunoRequest("123", "Renan Zago", "123456", Long.valueOf(1));
        perfil = new Perfil("ADMIM");
    }

    @Test
    @DisplayName("DEVE criar o objeto de requisicao")
    void test1() {
        assertEquals(alunoRequest.getRa(), "123");
        assertEquals(alunoRequest.getNome(), "Renan Zago");
        assertEquals(alunoRequest.getSenha(), "123456");
        assertEquals(alunoRequest.getPerfilId(), Long.valueOf(1));
    }

    @Test
    @DisplayName("DEVE transformar a requisicao em um objeto aluno")
    void test2() {
        Mockito.when(manager.find(Perfil.class, alunoRequest.getPerfilId())).thenReturn(perfil);

        Aluno novoAluno = alunoRequest.paraEntidade(manager);

        assertEquals(novoAluno.getRa(), alunoRequest.getRa());
        assertEquals(novoAluno.getNome(), alunoRequest.getNome());
        assertTrue(new BCryptPasswordEncoder().matches(alunoRequest.getSenha(), novoAluno.getSenha()));
    }

}