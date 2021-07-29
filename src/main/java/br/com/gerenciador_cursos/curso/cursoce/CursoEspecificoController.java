package br.com.gerenciador_cursos.curso.cursoce;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoce.DisciplinaCursoEspecifico;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoce.DisciplinaCursoEspecificoRequest;

@RestController
@RequestMapping("/cursos/ce")
public class CursoEspecificoController {
	
	@PersistenceContext
	private EntityManager manager;
	
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

		manager.merge(associacao);

		return ResponseEntity.status(HttpStatus.OK).body(new CursoEspecificoAssociacaoResponse(associacao));
	}
}
