package br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar;

import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos/bi")
public class DisciplinaBachareladoInterdiciplinarController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private DisciplinaBachareladoInterdiciplinarRepository disciplinaCursoCERepository;


    @GetMapping("/{id}/disciplinas")
    public ResponseEntity<List<DisciplinaBachareladoInterdiciplinarResponse>> buscarDisciplinasPorCodigoBI(@PathVariable Long id) {

        BachareladoInterdiciplinar cursoEncontrado = manager.find(BachareladoInterdiciplinar.class, id);

        List<DisciplinaBachareladoInterdiciplinarResponse> cursos = disciplinaCursoCERepository.findByBachareladoInterdiciplinar(cursoEncontrado)
                .stream().map(curso -> new DisciplinaBachareladoInterdiciplinarResponse(curso)).collect(Collectors.toList());

        return ResponseEntity.ok().body(cursos);
    }
}
