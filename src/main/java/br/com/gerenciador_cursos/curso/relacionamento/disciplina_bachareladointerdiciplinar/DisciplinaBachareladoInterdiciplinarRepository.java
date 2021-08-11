package br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar;

import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinar;
import br.com.gerenciador_cursos.disciplina.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaBachareladoInterdiciplinarRepository extends JpaRepository<DisciplinaBachareladoInterdiciplinar, Long> {
    List<DisciplinaBachareladoInterdiciplinar> findByBachareladoInterdiciplinar(BachareladoInterdiciplinar cursoBI);

    //Disciplina findByBachareladoInterdiciplina_IdAndDisciplina_nome(Long id, String nome);
}
