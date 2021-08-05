package br.com.gerenciador_cursos.curso.excel;

import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinar;
import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinarController;
import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinarRepository;
import br.com.gerenciador_cursos.curso.cursoespecifico.CursoEspecifico;
import br.com.gerenciador_cursos.curso.cursoespecifico.CursoEspecificoController;
import br.com.gerenciador_cursos.curso.cursoespecifico.CursoEspecificoRepository;
import br.com.gerenciador_cursos.curso.relacionamento.TipoCurso;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar.DisciplinaBachareladoInterdiciplinar;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar.DisciplinaBachareladoInterdiciplinarRepository;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_bachareladointerdiciplinar.DisciplinaBachareladoInterdiciplinarRequest;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoespecifico.DisciplinaCursoEspecifico;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoespecifico.DisciplinaCursoEspecificoRepository;
import br.com.gerenciador_cursos.curso.relacionamento.disciplina_cursoespecifico.DisciplinaCursoEspecificoRequest;
import br.com.gerenciador_cursos.disciplina.Disciplina;
import br.com.gerenciador_cursos.disciplina.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ExcelService {

    @Autowired
    BachareladoInterdiciplinarRepository bachareladoRepository;
    @Autowired
    CursoEspecificoRepository cursoEspecificoRepository;
    @Autowired
    DisciplinaBachareladoInterdiciplinarRepository relacionamentoBachareladoRespository;
    @Autowired
    DisciplinaCursoEspecificoRepository relacionamentoCursoEspecificoRepository;
    @PersistenceContext
    private EntityManager manager;


    private String regexSigla = "(\\w+) - .* \\((\\w+)\\)";

    public void salvarBacharelado(MultipartFile file) {
        try {
            List<BachareladoInterdiciplinar> tutorials = ExcelHelper.excelParaBacharelado(file.getInputStream());
            bachareladoRepository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("Falha ao armazenar dados do excel: " + e.getMessage());
        }
    }

    public void salvarCursoEspecifico(MultipartFile file) {
        try {
            List<CursoEspecifico> tutorials = ExcelHelper.excelParaCursoEspecifico(file.getInputStream(), manager);
            cursoEspecificoRepository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("Falha ao armazenar dados do excel: " + e.getMessage());
        }
    }

    public void relacionarDisciplina(String relacionamentos, Disciplina disciplinaSalva) {
        try {
            for (String relacionamento : relacionamentos.split(";")) {
                Pattern patternSigla = Pattern.compile(regexSigla, Pattern.MULTILINE);
                Matcher matcherSigla = patternSigla.matcher(relacionamento);

                matcherSigla.find();
                String sigla = matcherSigla.group(1);
                if(sigla.equals("BC&T") || sigla.equals("BC&H")) {
                    Optional<BachareladoInterdiciplinar> bacharelado = bachareladoRepository.procurarBacharelado(sigla);
                    if(!bacharelado.isPresent()){
                        break;
                    }
                    TipoCurso tipoCurso = null;
                    if(matcherSigla.group(2).equals("OL")) {
                        tipoCurso = TipoCurso.LIMITADA;
                    }
                    if(matcherSigla.group(2).equals("OBR")) {
                        tipoCurso = TipoCurso.OBRIGATORIA;
                    }
                    if(tipoCurso != null) {
                        DisciplinaBachareladoInterdiciplinarRequest request = new DisciplinaBachareladoInterdiciplinarRequest(disciplinaSalva.getId(), tipoCurso);
                        DisciplinaBachareladoInterdiciplinar relacionamentoBacharelado = request.toModel(manager,bacharelado.get().getId());
                        relacionamentoBachareladoRespository.save(relacionamentoBacharelado);
                    }
                } else {
                    Optional<CursoEspecifico> cursoEspecifico = cursoEspecificoRepository.procurarCurso(sigla);
                    if(!cursoEspecifico.isPresent()){
                        break;
                    }
                    TipoCurso tipoCurso = null;
                    String tipoDisciplina = matcherSigla.group(2);
                    if(tipoDisciplina.equals("OL")) {
                        tipoCurso = TipoCurso.LIMITADA;
                    }
                    if(tipoDisciplina.equals("OBR")) {
                        tipoCurso = TipoCurso.OBRIGATORIA;
                    }
                    if(tipoCurso != null) {
                        DisciplinaCursoEspecificoRequest request = new DisciplinaCursoEspecificoRequest(disciplinaSalva.getId(), tipoCurso);
                        DisciplinaCursoEspecifico relacionamentoCursoEspecifico = request.toModel(manager, cursoEspecifico.get().getId());
                        relacionamentoCursoEspecificoRepository.save(relacionamentoCursoEspecifico);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(disciplinaSalva);
            System.out.println(e.getMessage());
        }
    }
}
