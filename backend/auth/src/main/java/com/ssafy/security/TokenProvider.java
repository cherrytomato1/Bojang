package com.ssafy.security;

import com.ssafy.config.AppConfig;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {

	private final AppConfig appConfig;

	public String createToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + appConfig.getTokenExpirationMsec());

		return Jwts.builder()
			       //userId + OAuth2Id로 토큰 발급
			       //id = UserId, subject = AuthId
			       .setSubject(userPrincipal.getId())
			       .setId(userPrincipal.getUser().getId())
			       .setIssuedAt(new Date())
			       .setExpiration(expiryDate)
			       .signWith(SignatureAlgorithm.HS512, appConfig.getTokenSecret())
			       .compact();
	}

	/*
		OAuth2 인증 provider ID 반환
	 */
	public String getOAuthIdFromToken(String token) {
		Claims claims = Jwts.parser()
			                .setSigningKey(appConfig.getTokenSecret())
			                .parseClaimsJws(token)
			                .getBody();

		return claims.getSubject();
	}

	/*
		userId 반환
	 */
	public String getUserIdFromToken(String token) {
		Claims claims = Jwts.parser()
			                .setSigningKey(appConfig.getTokenSecret())
			                .parseClaimsJws(token)
			                .getBody();

		return claims.getId();
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(appConfig.getTokenSecret()).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			log.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
		}
		return false;
	}

}
