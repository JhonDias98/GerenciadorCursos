package br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoce;

import br.com.gerenciador_cursos.curso.cursoce.CursoCE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaCursoCERepository extends JpaRepository<DisciplinaCursoCE, Long> {

    List<DisciplinaCursoCE> findByCursoCE(CursoCE cursoCE);
}
