package br.com.gerenciador_cursos.config.security.auth.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequest {

	private String ra;
	private String senha;

	public void setRa(String ra) {
		this.ra = ra;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(ra, senha);
	}
	
}
