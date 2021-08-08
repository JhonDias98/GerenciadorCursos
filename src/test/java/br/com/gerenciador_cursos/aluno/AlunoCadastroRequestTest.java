package br.com.gerenciador_cursos.aluno;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AlunoCadastroRequestTest {
    private AlunoCadastroRequest alunoCadastroRequest;
    private Perfil perfil;
    private EntityManager manager = mock(EntityManager.class);

    @BeforeEach
    public void setup() {
        alunoCadastroRequest = new AlunoCadastroRequest("123", "Renan Zago", "123456", Long.valueOf(1));
        perfil = new Perfil("ADMIM");
    }

    @Test
    @DisplayName("DEVE criar o objeto de requisicao")
    void test1() {
        assertEquals(alunoCadastroRequest.getRa(), "123");
        assertEquals(alunoCadastroRequest.getNome(), "Renan Zago");
        assertEquals(alunoCadastroRequest.getSenha(), "123456");
        assertEquals(alunoCadastroRequest.getPerfilId(), Long.valueOf(1));
    }

    @Test
    @DisplayName("DEVE transformar a requisicao em um objeto aluno")
    void test2() {
        Mockito.when(manager.find(Perfil.class, alunoCadastroRequest.getPerfilId())).thenReturn(perfil);

        Aluno novoAluno = alunoCadastroRequest.paraEntidade(manager);

        assertEquals(novoAluno.getRa(), alunoCadastroRequest.getRa());
        assertEquals(novoAluno.getNome(), alunoCadastroRequest.getNome());
        assertTrue(new BCryptPasswordEncoder().matches(alunoCadastroRequest.getSenha(), novoAluno.getSenha()));
    }

}