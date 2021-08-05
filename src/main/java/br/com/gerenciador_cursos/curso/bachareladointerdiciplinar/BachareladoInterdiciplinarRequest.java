package br.com.gerenciador_cursos.curso.bachareladointerdiciplinar;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BachareladoInterdiciplinarRequest {

	@NotBlank
	private String nome;
	@NotBlank
	private String codigo;
	@NotNull
	private Integer livre;
	@NotNull
	private Integer limitada;
	@NotNull
	private Integer obrigatoria;

	public BachareladoInterdiciplinarRequest(String nome, String codigo, Integer livre, Integer limitada, Integer obrigatoria) {
		this.nome = nome;
		this.codigo = codigo;
		this.livre = livre;
		this.limitada = limitada;
		this.obrigatoria = obrigatoria;
	}

	public BachareladoInterdiciplinarRequest() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getLivre() {
		return livre;
	}

	public void setLivre(Integer livre) {
		this.livre = livre;
	}

	public Integer getLimitada() {
		return limitada;
	}

	public void setLimitada(Integer limitada) {
		this.limitada = limitada;
	}

	public Integer getObrigatoria() {
		return obrigatoria;
	}

	public void setObrigatoria(Integer obrigatoria) {
		this.obrigatoria = obrigatoria;
	}

	@Override
	public String toString() {
		return "BachareladoInterdiciplinarRequest{" +
				"nome='" + nome + '\'' +
				", codigo='" + codigo + '\'' +
				", livre=" + livre +
				", limitada=" + limitada +
				", obrigatoria=" + obrigatoria +
				'}';
	}

	public BachareladoInterdiciplinar toModel() {
		return new BachareladoInterdiciplinar(nome, codigo, livre, limitada, obrigatoria);
	}
}
