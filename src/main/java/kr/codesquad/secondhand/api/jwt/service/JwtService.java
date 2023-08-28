package kr.codesquad.secondhand.api.jwt.service;

import static org.apache.catalina.util.ConcurrentDateFormat.*;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.codesquad.secondhand.api.jwt.domain.Jwt;
import kr.codesquad.secondhand.api.jwt.domain.JwtProperties;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
	private final JwtProperties jwtProperties;

	public Jwt createJwt(Map<String, Object> claims) {
		String accessToken = createToken(claims, getExpiration(jwtProperties.getAccessTokenExpirationTime()));
		String refreshToken = createToken(new HashMap<>(), getExpiration(jwtProperties.getRefreshTokenExpirationTime()));

		return Jwt.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	public String createToken(Map<String,Object> claims, Date expiration){
		return Jwts.builder()
			.setClaims(claims)
			.setExpiration(expiration)
			.signWith(SignatureAlgorithm.HS256,
				Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes()))
			.compact();
	}

	public String reissueAccessToken(Map<String, Object> claims) {
		return createToken(claims, getExpiration(jwtProperties.getAccessTokenExpirationTime()));
	}

	public Date getExpiration(Long expirationTime) {
		return new Date(System.currentTimeMillis() + expirationTime);
	}

	public Long getUserId(String token) {
		Claims claims = getClaims(token);
		return claims.get("userId", Long.class);
	}

	public Claims getClaims(String token) {
		return Jwts.parser()
			.setSigningKey(Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes()))
			.parseClaimsJws(token)
			.getBody();
	}
}
