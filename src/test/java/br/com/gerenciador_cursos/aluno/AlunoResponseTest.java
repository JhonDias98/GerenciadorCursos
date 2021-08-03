package br.com.gerenciador_cursos.aluno;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoResponseTest {

    private Aluno novoAluno;

    @BeforeEach
    public void setup() {
        novoAluno = new Aluno("123", "Renan Zago", new Senha("123456"), new Perfil("ADMIN"));
    }

    @Test
    @DisplayName("DEVE retornar os dados do aluno")
    void test01() {
        AlunoResponse alunoResponse = new AlunoResponse(novoAluno);

        assertEquals(novoAluno.getRa(), alunoResponse.getRa());
    }
}