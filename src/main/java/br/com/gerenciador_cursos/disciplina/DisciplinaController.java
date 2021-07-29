package br.com.gerenciador_cursos.disciplina;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

	@Autowired
	private DisciplinaRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity<DisciplinaResponse> cadastrarDisciplina(@RequestBody @Valid DisciplinaRequest request) {
		if(repository.existsByNome(request.getNome())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		Disciplina novaDisciplina = request.toModel();
		repository.save(novaDisciplina);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new DisciplinaResponse(novaDisciplina));
	}

	@GetMapping
	public ResponseEntity<List<DisciplinaDetalheResponse>> consultarTodasDisciplinas() {
		List<DisciplinaDetalheResponse> disciplinas = repository.findAll()
				.stream().map(disciplina -> new DisciplinaDetalheResponse(disciplina))
				.collect(Collectors.toList());

		return ResponseEntity.ok(disciplinas);
	}

}
