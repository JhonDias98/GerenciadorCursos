package br.com.gerenciador_cursos.aluno;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AlunoAtualizaRequest {

	@NotBlank
	private String ra;

	@NotBlank
	private String senha;


	public AlunoAtualizaRequest(@NotBlank String ra, @NotBlank String nome, @NotBlank String senha, @NotNull Long perfilId) {
		this.ra = ra;
		this.senha = senha;
	}

	public String getRa() {
		return ra;
	}

	public String getSenha() {
		return senha;
	}
	
}
