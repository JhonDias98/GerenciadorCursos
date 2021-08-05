package br.com.gerenciador_cursos.curso.excel;

import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinar;
import br.com.gerenciador_cursos.curso.bachareladointerdiciplinar.BachareladoInterdiciplinarRequest;
import br.com.gerenciador_cursos.curso.cursoespecifico.CursoEspecifico;
import br.com.gerenciador_cursos.curso.cursoespecifico.CursoEspecificoRequest;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "codigo", "nome", "obrigatoria", "limitada", "livre" };
    static String BACHARELADO = "Bacharelado";
    static String ESPECIFICO = "Especifico";

    public static List<BachareladoInterdiciplinar> excelParaBacharelado(InputStream input) {
        try {
            Workbook workbook = new XSSFWorkbook(input);

            Sheet sheet = workbook.getSheet(BACHARELADO);
            Iterator<Row> rows = sheet.iterator();

            List<BachareladoInterdiciplinar> bachareladoAcumulador = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();

                BachareladoInterdiciplinarRequest bacharelado = new BachareladoInterdiciplinarRequest();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    if (currentCell.toString().length() == 0) {
                        workbook.close();
                        return bachareladoAcumulador;
                    }

                    switch (cellIdx) {
                        case 0:
                            bacharelado.setCodigo(currentCell.getStringCellValue().trim());
                            break;
                        case 1:
                            bacharelado.setNome(currentCell.getStringCellValue().trim());
                            break;
                        case 2:
                            bacharelado.setObrigatoria((int) currentCell.getNumericCellValue());
                            break;
                        case 3:
                            bacharelado.setLimitada((int) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            bacharelado.setLivre((int) currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                bachareladoAcumulador.add(bacharelado.toModel());
            }
            workbook.close();
            return bachareladoAcumulador;
        } catch (IOException e) {
            throw new RuntimeException("Falha ao analisar arquivo do Excel: " + e.getMessage());
        }
    }

    public static List<CursoEspecifico> excelParaCursoEspecifico(InputStream input, EntityManager manager) {
        try {
            Workbook workbook = new XSSFWorkbook(input);

            Sheet sheet = workbook.getSheet(ESPECIFICO);
            Iterator<Row> rows = sheet.iterator();

            List<CursoEspecifico> cursoEspecificos = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();

                CursoEspecificoRequest especifico = new CursoEspecificoRequest();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    if (currentCell.toString().length() == 0) {
                        workbook.close();
                        return cursoEspecificos;
                    }
                    switch (cellIdx) {
                        case 0:
                            especifico.setCodigo(currentCell.getStringCellValue().trim());
                            break;
                        case 1:
                            especifico.setNome(currentCell.getStringCellValue().trim());
                            break;
                        case 2:
                            especifico.setCursoBI_id(new Double(currentCell.getNumericCellValue()).longValue());
                            break;
                        case 3:
                            especifico.setObrigatoria((int) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            especifico.setLimitada((int) currentCell.getNumericCellValue());
                            break;
                        case 5:
                            especifico.setLivre((int) currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                cursoEspecificos.add(especifico.toModel(manager));
            }
            workbook.close();
            return cursoEspecificos;
        } catch (IOException e) {
            throw new RuntimeException("Falha ao analisar arquivo do Excel: " + e.getMessage());
        }
    }

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }
}
