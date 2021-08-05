package br.com.gerenciador_cursos.curso.bachareladointerdiciplinar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BachareladoInterdiciplinarRepository extends JpaRepository<BachareladoInterdiciplinar, Long> {

    Optional<BachareladoInterdiciplinar> findByCodigo(String codigo);

    @Query(value = "SELECT * FROM bacharelado_interdiciplinar b WHERE b.codigo = :codigo", nativeQuery = true)
    Optional<BachareladoInterdiciplinar> procurarBacharelado(@Param("codigo") String codigo);
}
