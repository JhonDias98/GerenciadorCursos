package br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar;

import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinar;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoespecifico.DisciplinaCursoEspecificoResponse;
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
@RequestMapping("/cursos/bi")
public class DisciplinaBachareladoInterdiciplinarController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private DisciplinaBachareladoInterdiciplinarRepository disciplinaCursoBIRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;


    @GetMapping("/{id}/disciplinas")
    public ResponseEntity<List<DisciplinaBachareladoInterdiciplinarResponse>> buscarDisciplinasPorCodigoBI(@PathVariable Long id) {


        BachareladoInterdiciplinar cursoEncontrado = manager.find(BachareladoInterdiciplinar.class, id);

        List<DisciplinaBachareladoInterdiciplinarResponse> disciplinas = disciplinaRepository.findAll().stream()
                .map(disciplina -> new DisciplinaBachareladoInterdiciplinarResponse(disciplina)).collect(Collectors.toList());
        List<DisciplinaBachareladoInterdiciplinarResponse> disciplinasBI = disciplinaCursoBIRepository.findByBachareladoInterdiciplinar(cursoEncontrado)
                .stream().map(curso -> new DisciplinaBachareladoInterdiciplinarResponse(curso)).collect(Collectors.toList());
        List<DisciplinaBachareladoInterdiciplinarResponse> disciplinasBacharelado = concatenarList(disciplinasBI, disciplinas);
        
        Collections.sort(disciplinasBacharelado);

        return ResponseEntity.ok().body(disciplinasBacharelado);
    }

    public List<DisciplinaBachareladoInterdiciplinarResponse> concatenarList(List<DisciplinaBachareladoInterdiciplinarResponse> primeiraLista,
                                                                  List<DisciplinaBachareladoInterdiciplinarResponse> segundaLista) {
        List<DisciplinaBachareladoInterdiciplinarResponse> novaLista = new ArrayList<>(primeiraLista);

        for(DisciplinaBachareladoInterdiciplinarResponse cursoEspecifico : segundaLista) {
            if(!novaLista.stream().anyMatch(disciplina -> disciplina.getNome().equals(cursoEspecifico.getNome()))) {
                novaLista.add(cursoEspecifico);
            }
        }
        return novaLista;
    }
}
