package br.com.gerenciador_cursos.curso.bachareladointerdiciplinar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar.DisciplinaBachareladoInterdiciplinar;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar.DisciplinaBachareladoInterdiciplinarRequest;

@RestController
@RequestMapping("/cursos/bi")
public class BachareladoInterdiciplinarController {
	
	@PersistenceContext
	private EntityManager manager;
	
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

}
