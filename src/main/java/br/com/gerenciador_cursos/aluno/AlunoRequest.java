package br.com.gerenciador_cursos.aluno;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AlunoRequest {
	
	@NotBlank
	private String ra;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String senha;
	
	@NotNull
	private Long perfilId;

	public AlunoRequest(@NotBlank String ra, @NotBlank String nome, @NotBlank String senha, @NotNull Long perfilId) {
		this.ra = ra;
		this.nome = nome;
		this.senha = senha;
		this.perfilId = perfilId;
	}

	public String getRa() {
		return ra;
	}

	public String getNome() {
		return nome;
	}
	public String getSenha() {
		return senha;
	}
	
	public Long getPerfilId() {
		return perfilId;
	}

	public Aluno paraEntidade(EntityManager manager) {
		Perfil perfil = manager.find(Perfil.class, perfilId);
		return new Aluno(ra, nome, new Senha(senha), perfil);
	}
	
}
