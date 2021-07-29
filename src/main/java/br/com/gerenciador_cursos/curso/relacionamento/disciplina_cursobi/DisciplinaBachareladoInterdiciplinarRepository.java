package br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursobi;

import br.com.gerenciador_cursos.curso.cursobi.BachareladoInterdiciplinar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaBachareladoInterdiciplinarRepository extends JpaRepository<DisciplinaBachareladoInterdiciplinar, Long> {
    List<DisciplinaBachareladoInterdiciplinar> findByBachareladoInterdiciplinar(BachareladoInterdiciplinar cursoCE);
}
