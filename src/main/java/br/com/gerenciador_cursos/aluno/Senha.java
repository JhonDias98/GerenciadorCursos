package br.com.gerenciador_cursos.aluno;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Senha {

	private String senha;

	public Senha(@NotBlank @Length(min = 6) String senha) {
		this.senha = senha;
	}
	
	public String hash() {
		return new BCryptPasswordEncoder().encode(senha);
	}
}
