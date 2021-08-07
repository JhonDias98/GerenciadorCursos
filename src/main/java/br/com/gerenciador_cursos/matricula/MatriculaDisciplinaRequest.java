package br.com.gerenciador_cursos.matricula;

import br.com.gerenciador_cursos.curso.relacionamento.TipoCurso;

public class MatriculaDisciplinaRequest {

    private Long quadrimestreId;
    private TipoCurso tipo;
    private Long disciplinaId;

    public MatriculaDisciplinaRequest(Long quadrimestreId, TipoCurso tipo, Long discpiplinaId) {
        this.quadrimestreId = quadrimestreId;
        this.tipo = tipo;
        this.disciplinaId = discpiplinaId;
    }

    public Long getQuadrimestreId() {
        return quadrimestreId;
    }

    public TipoCurso getTipo() {
        return tipo;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }


}
