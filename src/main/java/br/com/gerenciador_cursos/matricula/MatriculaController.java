package br.com.gerenciador_cursos.matricula;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping("/curso/bi")
	@Transactional
	public ResponseEntity<MatriculaResponse> matricularAlunoNoCursdBI(@RequestBody MatriculaRequest request) {
		Matricula novaMatricula = request.toModelBI(manager);

		manager.persist(novaMatricula);

		return ResponseEntity.status(HttpStatus.OK).body(new MatriculaResponse(novaMatricula));
	}

	@PostMapping("/curso/ce")
	@Transactional
	public ResponseEntity<MatriculaResponse> matricularAlunoNoCursdCE(@RequestBody MatriculaRequest request) {
		Matricula novaMatricula = request.toModelCE(manager);

		manager.persist(novaMatricula);

		return ResponseEntity.status(HttpStatus.OK).body(new MatriculaResponse(novaMatricula));
	}
}
