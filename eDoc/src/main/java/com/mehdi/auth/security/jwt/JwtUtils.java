package com.mehdi.auth.security.jwt;

import java.util.Date;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mehdi.auth.security.services.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	public static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${bezkoder.app.jwtSecret}")
	private String jwtSecret;

	@Value("${bezkoder.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String token,UserDetails userDetails) {
		final String username = getUserNameFromJwtToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	//check if the token has expired
	private Boolean isTokenExpired(String token) {
	final Date expiration = getExpirationDateFromToken(token);
	return expiration.before(new Date());
	}
	
	//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
	return getClaimFromToken(token, Claims::getExpiration);
	}
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
	final Claims claims = getAllClaimsFromToken(token);
	return claimsResolver.apply(claims);
	}
	    //for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
	return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	}

}

