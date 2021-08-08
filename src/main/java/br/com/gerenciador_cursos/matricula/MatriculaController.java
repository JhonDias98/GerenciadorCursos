package br.com.gerenciador_cursos.matricula;

import javax.transaction.Transactional;

import br.com.gerenciador_cursos.aluno.Aluno;
import br.com.gerenciador_cursos.aluno.AlunoRepository;
import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinarRepository;
import br.com.gerenciador_cursos.curso.cursoespecifico.CursoEspecificoRepository;
import br.com.gerenciador_cursos.disciplina.Disciplina;
import br.com.gerenciador_cursos.disciplina.DisciplinaRepository;
import br.com.gerenciador_cursos.matricula.quadrimestre.cursada.Cursada;
import br.com.gerenciador_cursos.matricula.quadrimestre.Quadrimestre;
import br.com.gerenciador_cursos.matricula.quadrimestre.QuadrimestreRepository;
import br.com.gerenciador_cursos.matricula.quadrimestre.cursada.CursadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private QuadrimestreRepository quadrimestreRepository;
	@Autowired
	private BachareladoInterdiciplinarRepository bachareladoRepository;
	@Autowired
	private CursoEspecificoRepository cursoEspecificoRepository;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private CursadaRepository cursadaRepository;

	public MatriculaController(MatriculaRepository matriculaRepository, QuadrimestreRepository quadrimestreRepository,
							   BachareladoInterdiciplinarRepository bachareladoRepository, CursoEspecificoRepository cursoEspecificoRepository,
							   AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository,
							   CursadaRepository cursadaRepository
	) {
		this.matriculaRepository = matriculaRepository;
		this.quadrimestreRepository = quadrimestreRepository;
		this.bachareladoRepository = bachareladoRepository;
		this.cursoEspecificoRepository = cursoEspecificoRepository;
		this.alunoRepository = alunoRepository;
		this.disciplinaRepository = disciplinaRepository;
		this.cursadaRepository = cursadaRepository;
	}

	@PostMapping("/curso/bi")
	@Transactional
	public ResponseEntity<MatriculaResponse> matricularAlunoNoCursdBI(@RequestBody MatriculaRequest request) {
		Matricula novaMatricula = request.toModelBI(alunoRepository, bachareladoRepository);
		matriculaRepository.save(novaMatricula);

		return ResponseEntity.status(HttpStatus.OK).body(new MatriculaResponse(novaMatricula));
	}

	@PostMapping("/curso/ce")
	@Transactional
	public ResponseEntity<MatriculaResponse> matricularAlunoNoCursdCE(@RequestBody MatriculaRequest request) {
		Matricula novaMatricula = request.toModelCE(alunoRepository, cursoEspecificoRepository);
		matriculaRepository.save(novaMatricula);

		return ResponseEntity.status(HttpStatus.OK).body(new MatriculaResponse(novaMatricula));
	}

	@PostMapping("/{idAluno}/quadrimestre")
	@Transactional
	public ResponseEntity<?> criarQuadrimestre(@PathVariable("idAluno") Long idAluno) {
		Aluno aluno = alunoRepository.getById(idAluno);

		Matricula matricula = aluno.getMatricula();
		matricula.criarQuadrimestre(new Quadrimestre());
		matriculaRepository.save(matricula);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{idAluno}/quadrimestre/{id}")
	public ResponseEntity<?> deletarQuadrimestre(@PathVariable("idAluno") Long idAluno, @PathVariable("id") Long idQuadrimestre) {
		Aluno aluno = alunoRepository.getById(idAluno);
		if(aluno.matriculaPertenceAluno(idQuadrimestre)) {
			return ResponseEntity.notFound().build();
		}
		quadrimestreRepository.deleteById(idQuadrimestre);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/concluidas")
	@Transactional
	public ResponseEntity<?> adicionarDisciplina(@RequestBody MatriculaDisciplinaRequest request) {
		Quadrimestre quadrimestre = quadrimestreRepository.getById(request.getQuadrimestreId());
		Disciplina disciplina = disciplinaRepository.getById(request.getDisciplinaId());
		Cursada cursada = new Cursada(disciplina, request.getTipo());

		quadrimestre.adicionarDisciplina(cursada);
		quadrimestreRepository.save(quadrimestre);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("{idAluno}/concluidas/{idCursada}")
	@Transactional
	public ResponseEntity<?> deletarDisciplina(@PathVariable("idAluno") Long idAluno, @PathVariable("idCursada") Long idCursada) {
		Aluno aluno = alunoRepository.getById(idAluno);
		if(aluno.cursadaPertenceAluno(idCursada)) {
			return ResponseEntity.notFound().build();
		}

		cursadaRepository.deleteById(idCursada);
		return ResponseEntity.ok().build();
	}
}
