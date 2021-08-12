package br.com.gerenciador_cursos.curso.cursoespecifico;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoespecifico.DisciplinaCursoEspecifico;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoespecifico.DisciplinaCursoEspecificoRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos/ce")
public class CursoEspecificoController {
	
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private CursoEspecificoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<CursoEspecificoResponse> cadastrarCursoBI(@RequestBody @Valid CursoEspecificoRequest request) {
		CursoEspecifico novoCursoEspecifico = request.toModel(manager);
		manager.persist(novoCursoEspecifico);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new CursoEspecificoResponse(novoCursoEspecifico));
	}
	
	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<CursoEspecificoAssociacaoResponse> associarDisciplina(@PathVariable(name = "id") Long cursoId, @RequestBody DisciplinaCursoEspecificoRequest request) {
		DisciplinaCursoEspecifico associacao = request.toModel(manager, cursoId);

		manager.persist(associacao);

		return ResponseEntity.status(HttpStatus.OK).body(new CursoEspecificoAssociacaoResponse(associacao));
	}

	@GetMapping
	public ResponseEntity<List<CursoEspecificoDetalheResponse>> consultarCursos() {
		List<CursoEspecificoDetalheResponse> cursos = repository.findAll().stream()
				.map(curso -> new CursoEspecificoDetalheResponse(curso)).collect(Collectors.toList());

		return ResponseEntity.ok().body(cursos);
	}
}
