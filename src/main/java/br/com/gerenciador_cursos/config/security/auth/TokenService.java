package br.com.gerenciador_cursos.config.security.auth;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.gerenciador_cursos.aluno.Aluno;
import br.com.gerenciador_cursos.aluno.AlunoRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${ufabc.jwt.expiration}")
	private String expiration;
	@Value("${ufabc.jwt.secret}")
	private String secret;
	@Autowired
    private AlunoRepository alunoRepository;
	
	public String gerarToken(Authentication authentication) {
		Aluno logado = (Aluno) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API UFABC")
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Aluno getAluno(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		Optional<Aluno> aluno = alunoRepository.findById(Long.parseLong(claims.getSubject()));
        return aluno.get();
	}
}