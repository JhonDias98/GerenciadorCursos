package br.com.gerenciador_cursos.curso.bachareladointerdiciplinar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@ActiveProfiles("test")
class BachareladoInterdiciplinarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    String requestCursoBI;
    String requestAssociacao;

    @BeforeEach
    public void setup() {
        requestCursoBI =
                "{" +
                    "\"nome\":\"Teste BCT\"," +
                    "\"codigoCurso\":\"001\"," +
                    "\"livre\":10," +
                    "\"limitada\":15," +
                    "\"obrigatoria\":20" +
                "}";

        requestAssociacao =
                "{" +
                    "\"disciplinaId\":1," +
                    "\"tipoDoCurso\":\"OBRIGATORIA\"" +
                "}";
    }

    @Test
    @DisplayName("DEVE cadastrar um novo Bacharelado Interdiciplinar")
    void test01() throws Exception {
        mockMvc.perform(post("/cursos/bi")
                .content(requestCursoBI)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("DEVE associar uma disciplina ao curso")
    void test02() throws Exception {
        mockMvc.perform(post("/cursos/bi/1")
                .content(requestAssociacao)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}