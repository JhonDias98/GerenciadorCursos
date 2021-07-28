package br.com.gerenciador_cursos.aluno;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class AlunoTest {
	
	private Aluno novoAluno;

	@BeforeEach
	public void setup() {
		novoAluno = new Aluno("123", "Renan Zago", new Senha("123456"), new Perfil("ADMIN"));
	}
	
	@Test
	@DisplayName("DEVE criar o objeto aluno")
	void test1() {
		assertEquals(novoAluno.getRa(), "123");
		assertEquals(novoAluno.getNome(), "Renan Zago");
		assertTrue(new BCryptPasswordEncoder().matches("123456", novoAluno.getSenha()));
	}

	@Test
	@DisplayName("DEVE retornar true para os métodos de autenticação")
	void test2() {
		assertTrue(novoAluno.isAccountNonExpired());
		assertTrue(novoAluno.isAccountNonLocked());
		assertTrue(novoAluno.isCredentialsNonExpired());
		assertTrue(novoAluno.isEnabled());
		
	}
	
	@Test
	@DisplayName("DEVE retornar os dados de login")
	void test3() {
		assertEquals(novoAluno.getUsername(), "123");
		assertTrue(new BCryptPasswordEncoder().matches("123456", novoAluno.getPassword()));
	}

}