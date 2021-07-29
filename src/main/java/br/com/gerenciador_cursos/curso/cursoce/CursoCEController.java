package br.com.gerenciador_cursos.curso.cursoce;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoce.DisciplinaCursoCERepository;
import br.com.gerenciador_cursos.disciplina.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoce.DisciplinaCursoCE;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoce.DisciplinaCursoCERequest;

@RestController
@RequestMapping("/cursos/ce")
public class CursoCEController {
	
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private DisciplinaCursoCERepository disciplinaCursoCERepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarCursoBI(@RequestBody @Valid CursoCERequest request) {
		CursoCE novoCursoCE = request.toModel(manager);
		manager.persist(novoCursoCE);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<?> associarDisciplina(@PathVariable(name = "id") Long cursoId, @RequestBody DisciplinaCursoCERequest request) {
		DisciplinaCursoCE associacao = request.toModel(manager, cursoId);
		
		manager.merge(associacao);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
