package br.com.gerenciador_cursos.curso.bachareladointerdiciplinar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoespecifico.DisciplinaCursoEspecificoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar.DisciplinaBachareladoInterdiciplinar;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar.DisciplinaBachareladoInterdiciplinarRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos/bi")
public class BachareladoInterdiciplinarController {
	
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private BachareladoInterdiciplinarRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<BachareladoInterdiciplinarResponse> cadastrarCursoBI(@RequestBody @Valid BachareladoInterdiciplinarRequest request) {
		BachareladoInterdiciplinar novoBachareladoInterdiciplinar = request.toModel();
		manager.persist(novoBachareladoInterdiciplinar);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new BachareladoInterdiciplinarResponse(novoBachareladoInterdiciplinar));
	}
	
	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<BachareladoInterdiciplinarAssociacaoResponse> associarDisciplina(@PathVariable(name = "id") Long cursoId, @RequestBody DisciplinaBachareladoInterdiciplinarRequest request) {
		DisciplinaBachareladoInterdiciplinar associacao = request.toModel(manager, cursoId);
		
		manager.persist(associacao);
		
		return ResponseEntity.status(HttpStatus.OK).body(new BachareladoInterdiciplinarAssociacaoResponse(associacao));
	}

	@GetMapping
	public ResponseEntity<List<BachareladoInterdiciplinarDetalheResponse>> consultarCursos() {
		List<BachareladoInterdiciplinarDetalheResponse> cursos = repository.findAll().stream()
				.map(curso -> new BachareladoInterdiciplinarDetalheResponse(curso)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cursos);
	}

}
