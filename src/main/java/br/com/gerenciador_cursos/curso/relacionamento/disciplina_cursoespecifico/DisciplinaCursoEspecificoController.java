package br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoespecifico;

import br.com.gerenciador_cursos.curso.cursoespecifico.CursoEspecifico;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar.DisciplinaBachareladoInterdiciplinarResponse;
import br.com.gerenciador_cursos.disciplina.Disciplina;
import br.com.gerenciador_cursos.disciplina.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos/ce")
public class DisciplinaCursoEspecificoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private DisciplinaCursoEspecificoRepository disciplinaCursoEspecificoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;


    @GetMapping("/{id}/disciplinas")
    public ResponseEntity<List<DisciplinaCursoEspecificoResponse>> buscarDisciplinasPorCodigoBI(@PathVariable Long id) {

        CursoEspecifico cursoEncontrado = manager.find(CursoEspecifico.class, id);

        List<DisciplinaCursoEspecificoResponse> disciplinas = disciplinaRepository.findAll().stream()
                .map(disciplina -> new DisciplinaCursoEspecificoResponse(disciplina)).collect(Collectors.toList());
        List<DisciplinaCursoEspecificoResponse> disciplinasBI = cursoEncontrado.getBachareladoInterdiciplinar()
                .getRelacionamentos().stream().map(curso -> new DisciplinaCursoEspecificoResponse(curso)).collect(Collectors.toList());
        List<DisciplinaCursoEspecificoResponse> disciplinasCE = cursoEncontrado.getRelacionamentos()
                .stream().map(curso -> new DisciplinaCursoEspecificoResponse(curso)).collect(Collectors.toList());

        List<DisciplinaCursoEspecificoResponse> disciplinasCursoEspecifico = concatenarList(disciplinasCE, disciplinasBI, disciplinas);

        Collections.sort(disciplinasCursoEspecifico);

        return ResponseEntity.ok().body(disciplinasCursoEspecifico);
    }

    public List<DisciplinaCursoEspecificoResponse> concatenarList(List<DisciplinaCursoEspecificoResponse> primeiraLista,
                                                                   List<DisciplinaCursoEspecificoResponse> segundaLista,
                                                                   List<DisciplinaCursoEspecificoResponse> terceiraLista) {
        List<DisciplinaCursoEspecificoResponse> novaLista = new ArrayList<>(primeiraLista);

        for(DisciplinaCursoEspecificoResponse cursoEspecifico : segundaLista) {
            if(!novaLista.stream().anyMatch(disciplina -> disciplina.getNome().equals(cursoEspecifico.getNome()))) {
                novaLista.add(cursoEspecifico);
            }
        }

        for(DisciplinaCursoEspecificoResponse cursoEspecifico : terceiraLista) {
            if(!novaLista.stream().anyMatch(disciplina -> disciplina.getNome().equals(cursoEspecifico.getNome()))) {
                novaLista.add(cursoEspecifico);
            }
        }
        return novaLista;
    }

}
