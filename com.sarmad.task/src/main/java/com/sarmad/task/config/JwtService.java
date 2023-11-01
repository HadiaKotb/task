package com.sarmad.task.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mongodb.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET_KEY="a190a8699032874cc00a7085944e4ced5e10be8ef16b0900283762144f29ac9f";


	
	
	
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(),userDetails);
	}
	
	public String generateToken(Map<String,Object>extraClaims,UserDetails userDetails) {
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration((new Date(System.currentTimeMillis()+1000*60*24)))
				.signWith(getSigninKey(),SignatureAlgorithm.HS256)
				.compact();
		
	}
	
	public boolean isTokenValid(String token,UserDetails userDetails) {
		final String username= extractLoginIdFromToken(token);
		return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
	}
	
	public boolean isTokenExpired(String token) {
		
		
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getExpiration);
	}

	public String extractLoginIdFromToken(String jwtToken) {
		
		return extractClaim(jwtToken, Claims::getSubject);
	}
	
	public<T> T extractClaim(String token,Function<Claims, T> claimResolver) {
		final Claims claims=extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	private Claims extractAllClaims(String jwt) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSigninKey())
				.build()
				.parseClaimsJws(jwt)
				.getBody();
	}

	private Key getSigninKey() {
		// TODO Auto-generated method stub
		byte[]keyBytes=Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	

}
