package br.com.gerenciador_cursos.curso.excel;

import br.com.gerenciador_cursos.disciplina.Disciplina;
import br.com.gerenciador_cursos.disciplina.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ArquivoExcelController {

    @Autowired
    ExcelService service;

    @Autowired
    DisciplinaExcel disciplinaExcel;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @PostMapping("/upload/curso/bacharelado")
    public ResponseEntity<?> uploadArquivoBacharelado(@RequestParam("arquivo") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                service.salvarBacharelado(file);
                message = "Arquivo carregado com sucesso: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
                message = "Não foi possível fazer upload do arquivo: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }

        message = "Faça upload de um arquivo excel!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @PostMapping("/upload/curso/especifico")
    public ResponseEntity<?> uploadArquivoEspecifico(@RequestParam("arquivo") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                service.salvarCursoEspecifico(file);
                message = "Arquivo carregado com sucesso: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
                message = "Não foi possível fazer upload do arquivo: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Faça upload de um arquivo excel!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @PostMapping("/upload/disciplina")
    public ResponseEntity<?> uploadArquivoDisciplina(@RequestParam("arquivo") MultipartFile file) {
        try {
            disciplinaExcel.excelParaDisciplna(file.getInputStream(), disciplinaRepository);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao armazenar dados do excel: " + e.getMessage());
        }
    }
}
