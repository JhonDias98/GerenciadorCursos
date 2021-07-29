package br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoespecifico;

import br.com.gerenciador_cursos.curso.cursoespecifico.CursoEspecifico;
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
public class DisciplinaCursoEspecificoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private DisciplinaCursoEspecificoRepository disciplinaCursoEspecificoRepository;


    @GetMapping("/{id}/disciplinas")
    public ResponseEntity<List<DisciplinaCursoEspecificoResponse>> buscarDisciplinasPorCodigoBI(@PathVariable Long id) {

        CursoEspecifico cursoEncontrado = manager.find(CursoEspecifico.class, id);

        List<DisciplinaCursoEspecificoResponse> cursos = disciplinaCursoEspecificoRepository.findByCursoEspecifico(cursoEncontrado)
                .stream().map(curso -> new DisciplinaCursoEspecificoResponse(curso)).collect(Collectors.toList());

        return ResponseEntity.ok().body(cursos);
    }
}
