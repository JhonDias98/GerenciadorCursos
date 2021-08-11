package br.com.gerenciador_cursos.matricula;

import br.com.gerenciador_cursos.curso.relacionamento.TipoCurso;

public class MatriculaDisciplinaRequest {

    private Long quadrimestreId;
    private Long disciplinaId;

    public MatriculaDisciplinaRequest(Long quadrimestreId, TipoCurso tipo, Long discpiplinaId) {
        this.quadrimestreId = quadrimestreId;
        this.disciplinaId = discpiplinaId;
    }

    public Long getQuadrimestreId() {
        return quadrimestreId;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

}
