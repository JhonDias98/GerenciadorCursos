package br.com.gerenciador_cursos.curso.bachareladointerdiciplinar;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BachareladoInterdiciplinarDetalheResponse {

    private Long id;
    private String nome;
    private String codigo;
    private Integer livre;
    private Integer limitada;
    private Integer obrigatoria;

    public BachareladoInterdiciplinarDetalheResponse(BachareladoInterdiciplinar curso) {
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.codigo = curso.getCodigo();
        this.livre = curso.getLivre();
        this.limitada = curso.getLimitada();
        this.obrigatoria = curso.getObrigatoria();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public Integer getLivre() {
        return livre;
    }

    public Integer getLimitada() {
        return limitada;
    }

    public Integer getObrigatoria() {
        return obrigatoria;
    }
}
