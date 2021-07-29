package br.com.gerenciador_cursos.aluno;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private AlunoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<AlunoResponse> cadastrarAluno(@RequestBody @Valid AlunoRequest request) {
		if(repository.existsByRa(request.getRa())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		Aluno novoAluno = request.paraEntidade(manager);
		
		repository.save(novoAluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(new AlunoResponse(novoAluno));
	}
	
}
