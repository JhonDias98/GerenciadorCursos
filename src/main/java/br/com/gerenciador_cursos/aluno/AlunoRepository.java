package br.com.gerenciador_cursos.aluno;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	Optional<Aluno> findByRa(String ra);

	boolean existsByRa(String ra);
}
