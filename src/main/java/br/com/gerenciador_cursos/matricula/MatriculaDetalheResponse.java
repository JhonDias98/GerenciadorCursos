package br.com.gerenciador_cursos.matricula;

import br.com.gerenciador_cursos.aluno.Aluno;
import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinar;
import br.com.gerenciador_cursos.curso.cursoespecifico.CursoEspecifico;

public class MatriculaDetalheResponse {

    private String codigo;
    private String tipo;
    private Integer livre;
    private Integer limitada;
    private Integer obrigatoriaCE;
    private Integer obrigatoriaBi;

    public MatriculaDetalheResponse(Aluno aluno){
        if(aluno.getMatricula().getCursoEspecifico() == null) {
            BachareladoInterdiciplinar bacharelado = aluno.getMatricula().getBachareladoInterdiciplinar();

            this.codigo = bacharelado.getCodigo();
            this.tipo = "Bacharelado Interdiciplinar";
            this.livre = bacharelado.getLivre();
            this.limitada = bacharelado.getLimitada();
            this.obrigatoriaBi = bacharelado.getObrigatoria();
            this.obrigatoriaCE = 0;
        } else {
            CursoEspecifico cursoEspecifico = aluno.getMatricula().getCursoEspecifico();

            this.codigo = cursoEspecifico.getCodigo();
            this.tipo = "Curso Específico";
            this.livre = cursoEspecifico.getLivre();
            this.limitada = cursoEspecifico.getLimitada();
            this.obrigatoriaBi = cursoEspecifico.getBachareladoInterdiciplinar().getObrigatoria();
            this.obrigatoriaCE = cursoEspecifico.getObrigatoria();
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getLivre() {
        return livre;
    }

    public Integer getLimitada() {
        return limitada;
    }

    public Integer getObrigatoriaCE() {
        return obrigatoriaCE;
    }

    public Integer getObrigatoriaBi() {
        return obrigatoriaBi;
    }
}
