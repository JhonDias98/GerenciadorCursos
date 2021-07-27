package br.com.gerenciador_cursos.disciplina;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    boolean existsByNome(String nome);

    List<Disciplina> findByCursoBIRelacionadoCursoBIId(Long idCurso);

    List<Disciplina> findByCursoCERelacionadoCursoCEId(Long idCurso);
}
