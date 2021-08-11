package br.com.gerenciador_cursos.matricula;

import br.com.gerenciador_cursos.aluno.Aluno;
import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinar;
import br.com.gerenciador_cursos.curso.cursoespecifico.CursoEspecifico;

public class MatriculaProgressoResponse {


    private Integer livre;
    private Integer limitada;
    private Integer obrigatoriaCE;
    private Integer obrigatoriaBi;

    public MatriculaProgressoResponse(Aluno aluno){
        Matricula matricula = aluno.getMatricula();

        this.livre = matricula.getLivre();
        this.limitada = matricula.getLimitada();
        this.obrigatoriaBi = matricula.getObrigatoriaBi();
        this.obrigatoriaCE = matricula.getObrigatoriaCE();
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
