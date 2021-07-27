package br.com.gerenciador_cursos.aluno;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    private Aluno alunoExistente;

    @BeforeEach
    public void setup() {
        alunoExistente = new Aluno("123", "Renan", new Senha("123456"), new Perfil("ADMIN"));
        alunoRepository.save(alunoExistente);
    }

    @Test
    @DisplayName("DEVE retorna TRUE caso o RA exista no banco de dados")
    public void teste01() throws Exception {
        Boolean raEncontrado = alunoRepository.existsByRa(alunoExistente.getRa());

        assertTrue(raEncontrado);
    }

    @Test
    @DisplayName("DEVE retorna o aluno caso exista no banco de dados")
    public void teste02() throws Exception {
        Aluno alunoEncontrado = alunoRepository.findByRa(alunoExistente.getRa()).get();

        assertTrue(alunoEncontrado.getId() != null);
        assertEquals(alunoExistente.getRa(), alunoEncontrado.getRa());
        assertEquals(alunoExistente.getNome(), alunoEncontrado.getNome());
    }

}