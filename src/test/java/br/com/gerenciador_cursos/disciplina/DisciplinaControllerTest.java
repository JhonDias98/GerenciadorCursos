package br.com.gerenciador_cursos.disciplina;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
//@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class DisciplinaControllerTest {

//    @WebMvcTest(DisciplinaController.class)
//    @RunWith(SpringRunner.class)

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EntityManager manager;

    String requestBody;

    @BeforeEach
    public void setup() {
        requestBody = "{" +
                "\"nome\":\"Computação II\"," +
                "\"teoria\":2," +
                "\"pratica\":1," +
                "\"individual\":1," +
                "\"codigo\":\"APS11\"," +
                "\"interdisciplinar\":\"BCT\"" +
                "}";
    }

    @Test
    @DisplayName("DEVE cadastrar uma nova disciplina")
    void test01() throws Exception {
        mockMvc.perform(post("/disciplinas")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("NAO DEVE cadastrar uma disciplina quando o nome já existe no banco de dados")
    void test02() throws Exception {
        mockMvc.perform(post("/disciplinas")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(post("/disciplinas")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

}