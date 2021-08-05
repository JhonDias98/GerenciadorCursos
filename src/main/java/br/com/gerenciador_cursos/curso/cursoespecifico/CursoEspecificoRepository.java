package br.com.gerenciador_cursos.curso.cursoespecifico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CursoEspecificoRepository extends JpaRepository<CursoEspecifico, Long> {
    Optional<CursoEspecifico> findByCodigo(String codigo);

    @Query(value = "SELECT * FROM curso_especifico c WHERE c.codigo = :codigo", nativeQuery = true)
    Optional<CursoEspecifico> procurarCurso(@Param("codigo") String codigo);
}
