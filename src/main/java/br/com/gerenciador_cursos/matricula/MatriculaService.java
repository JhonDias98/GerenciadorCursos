package br.com.gerenciador_cursos.matricula;

import br.com.gerenciador_cursos.aluno.Aluno;
import br.com.gerenciador_cursos.aluno.AlunoRepository;
import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinar;
import br.com.gerenciador_cursos.curso.cursoespecifico.CursoEspecifico;
import br.com.gerenciador_cursos.curso.relacionamento.TipoCurso;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar.DisciplinaBachareladoInterdiciplinarRepository;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar.DisciplinaBachareladoInterdiciplinarResponse;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoespecifico.DisciplinaCursoEspecificoRepository;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoespecifico.DisciplinaCursoEspecificoResponse;
import br.com.gerenciador_cursos.disciplina.Disciplina;
import br.com.gerenciador_cursos.disciplina.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatriculaService {

    @Autowired
    private DisciplinaBachareladoInterdiciplinarRepository disciplinaCursoBIRepository;
    @Autowired
    private DisciplinaCursoEspecificoRepository disciplinaCursoEspecificoRepository;
    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public void somarCredito(Matricula matricula, Disciplina disciplina) {

        if(matricula.getCursoEspecifico() == null) {
            try {
                List<DisciplinaBachareladoInterdiciplinarResponse> disciplinasEncontradas = disciplinaCursoBIRepository
                        .findByBachareladoInterdiciplinar(matricula.getBachareladoInterdiciplinar())
                        .stream().map(curso -> new DisciplinaBachareladoInterdiciplinarResponse(curso)).collect(Collectors.toList());

                DisciplinaBachareladoInterdiciplinarResponse disciplinaFinal = disciplinasEncontradas
                        .stream().filter(curso -> curso.getNome().equals(disciplina.getNome())).findAny().get();

                if(disciplinaFinal.getTipo().equals("limitada")) matricula.setLimitada(disciplinaFinal.getCreditos());

                if(disciplinaFinal.getTipo().equals("obrigatoria")) matricula.setObrigatoriaCE(disciplinaFinal.getCreditos());
            } catch (Exception e) {
                matricula.setLivre(disciplina.getCreditos());
            }
        } else {
            try {
                List<DisciplinaCursoEspecificoResponse> disciplinasEncontradas = disciplinaCursoEspecificoRepository
                        .findByCursoEspecifico(matricula.getCursoEspecifico())
                        .stream().map(curso -> new DisciplinaCursoEspecificoResponse(curso)).collect(Collectors.toList());

                DisciplinaCursoEspecificoResponse disciplinaFinal = disciplinasEncontradas
                        .stream().filter(curso -> curso.getNome().equals(disciplina.getNome())).findAny().get();

                if(disciplinaFinal.getTipo().equals("limitada")) matricula.setLimitada(disciplinaFinal.getCreditos());

                if(disciplinaFinal.getTipo().equals("obrigatoria")) matricula.setObrigatoriaCE(disciplinaFinal.getCreditos());

                if(disciplinaFinal.getTipo().equals("obrigatoria_bi")) matricula.setObrigatoriaBi(disciplinaFinal.getCreditos());
            } catch (Exception e) {
                System.out.println(disciplina.getCreditos());
                matricula.setLivre(disciplina.getCreditos());
            }
        }
        matriculaRepository.save(matricula);
    }

    public List<DisciplinaCursoEspecificoResponse> consultarDisciplinasCE(Aluno aluno) {
        List<DisciplinaCursoEspecificoResponse> disciplinas = disciplinaRepository.findAll().stream()
                .map(disciplina -> new DisciplinaCursoEspecificoResponse(disciplina)).collect(Collectors.toList());
        List<DisciplinaCursoEspecificoResponse> disciplinasBI = aluno.getMatricula().getCursoEspecifico().getBachareladoInterdiciplinar()
                .getRelacionamentos().stream().map(curso -> new DisciplinaCursoEspecificoResponse(curso)).collect(Collectors.toList());
        List<DisciplinaCursoEspecificoResponse> disciplinasCE = aluno.getMatricula().getCursoEspecifico().getRelacionamentos()
                .stream().map(curso -> new DisciplinaCursoEspecificoResponse(curso)).collect(Collectors.toList());

        List<DisciplinaCursoEspecificoResponse> disciplinasCursoEspecifico = concatenarTresList(disciplinasBI, disciplinasCE, disciplinas);

        Collections.sort(disciplinasCursoEspecifico);
        return disciplinasCursoEspecifico;
    }



    public List<DisciplinaCursoEspecificoResponse> concatenarTresList(List<DisciplinaCursoEspecificoResponse> primeiraLista,
                                                                  List<DisciplinaCursoEspecificoResponse> segundaLista,
                                                                  List<DisciplinaCursoEspecificoResponse> terceiraLista) {
        List<DisciplinaCursoEspecificoResponse> novaLista = new ArrayList<>(primeiraLista);
        novaLista.addAll(segundaLista);

        for(DisciplinaCursoEspecificoResponse cursoEspecifico : terceiraLista) {
            if(!novaLista.stream().anyMatch(disciplina -> disciplina.getNome().equals(cursoEspecifico.getNome()))) {
                novaLista.add(cursoEspecifico);
            }
        }
        return novaLista;
    }
}
