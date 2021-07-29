package br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoce;

import br.com.gerenciador_cursos.curso.cursoce.CursoCE;
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
@RequestMapping("/cursos/ce")
public class DisciplinaCursoCEController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private DisciplinaCursoCERepository disciplinaCursoCERepository;


    @GetMapping("/{id}/disciplinas")
    public ResponseEntity<List<DisciplinaCursoCEResponse>> buscarDisciplinasPorCodigoBI(@PathVariable Long id) {

        CursoCE cursoEncontrado = manager.find(CursoCE.class, id);

        List<DisciplinaCursoCEResponse> cursos = disciplinaCursoCERepository.findByCursoCE(cursoEncontrado)
                .stream().map(curso -> new DisciplinaCursoCEResponse(curso)).collect(Collectors.toList());


        return ResponseEntity.ok().body(cursos);
    }
}
