package br.com.gerenciador_cursos.curso.cursobi;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.gerenciador_cursos.disciplina.DisciplinaRepository;
import br.com.gerenciador_cursos.disciplina.DisciplinaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursobi.DisciplinaCursoBI;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursobi.DisciplinaCursoBIRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos/bi")
public class CursoBIController {
	
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarCursoBI(@RequestBody @Valid CursoBIRequest request) {
		CursoBI novoCursoBI = request.toModel();
		manager.persist(novoCursoBI);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<?> associarDisciplina(@PathVariable(name = "id") Long cursoId, @RequestBody DisciplinaCursoBIRequest request) {
		DisciplinaCursoBI associacao = request.toModel(manager, cursoId);
		
		manager.merge(associacao);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/{id}/disciplinas")
	public ResponseEntity<List<DisciplinaResponse>> buscarDisciplinasPorCodigoBI(@PathVariable Long id) {
		List<DisciplinaResponse> disciplinasEncontradas = disciplinaRepository
				.findByCursoBIRelacionadoCursoBIId(id)
				.stream().map(disciplina -> new DisciplinaResponse(disciplina))
				.collect(Collectors.toList());

		return ResponseEntity.ok(disciplinasEncontradas);
	}

}
