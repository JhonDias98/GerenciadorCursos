package br.com.gerenciador_cursos.aluno;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@ActiveProfiles("test")
class AlunoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	String requestBodyValida;
	String requestBodyInvalido;

	@BeforeEach
	public void setup() {
		requestBodyValida = "{" +
				"\"ra\":\"123123\"," +
				"\"nome\":\"Renan Zago\"," +
				"\"senha\":\"123456\"," +
				"\"perfilId\":1" +
				"}";

		requestBodyInvalido = "{" +
				"\"ra\":\"123123\"," +
				"\"nome\":\"\"," +
				"\"senha\":\"123456\"," +
				"\"perfilId\":1" +
				"}";
	}

	@Test
	@DisplayName("DEVE cadastrar um novo aluno")
	void test01() throws Exception {
		mockMvc.perform(post("/alunos")
				.content(requestBodyValida)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	@DisplayName("NAO DEVE cadastrar um aluno quando o RA já existe no banco de dados")
	void test02() throws Exception {
		mockMvc.perform(post("/alunos")
				.content(requestBodyValida)
				.contentType(MediaType.APPLICATION_JSON));

		mockMvc.perform(post("/alunos")
				.content(requestBodyValida)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isConflict());
	}

	@Test
	@DisplayName("NAO DEVE cadastrar um novo aluno quando possui dados invalidos")
	void test03() throws Exception {
		mockMvc.perform(post("/alunos")
				.content(requestBodyInvalido)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(result -> assertEquals(MethodArgumentNotValidException.class, result.getResolvedException().getClass()))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
	}
	
}
