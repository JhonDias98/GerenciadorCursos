package br.com.gerenciador_cursos.aluno;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private AlunoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<AlunoResponse> cadastrarAluno(@RequestBody @Valid AlunoCadastroRequest request) {
		if(repository.existsByRa(request.getRa())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		Aluno novoAluno = request.paraEntidade(manager);
		
		repository.save(novoAluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(new AlunoResponse(novoAluno));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AlunoResponse> alterarAluno(@PathVariable("id") Long id, @RequestBody @Valid AlunoAtualizaRequest request) {
		Aluno aluno = repository.getById(id);

		aluno.atualizarDados(request.getRa(), new Senha(request.getSenha()));

		repository.save(aluno);
		return ResponseEntity.status(HttpStatus.OK).body(new AlunoResponse(aluno));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlunoNomeResponse> buscarNomeAluno(@PathVariable("id") Long id) {
		Aluno aluno = repository.getById(id);

		return ResponseEntity.status(HttpStatus.OK).body(new AlunoNomeResponse(aluno));
	}
	
}
