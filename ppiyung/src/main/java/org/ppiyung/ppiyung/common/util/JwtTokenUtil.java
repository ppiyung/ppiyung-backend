package org.ppiyung.ppiyung.common.util;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.common.entity.BasicResponseEntity;
import org.ppiyung.ppiyung.member.vo.SecurityUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtTokenUtil {
	
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;
	private final Key key;
	
	private Logger log = LogManager.getLogger("base");
    
//	// JWT 생성을 위해 사용하는 Key 객체를 생성
//    public JwtTokenUtil(@Value("${auth.jwtSecret}") String secretKey) {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//    }
//    
	// JWT 생성을 위해 사용하는 Key 객체를 생성
    public JwtTokenUtil(String secretKey) {
    	System.out.println("secretKey: " + secretKey);
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
    
    // JWT 생성
    public HashMap<String, String> generateToken(Authentication authentication) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
 
        long now = (new Date()).getTime();
        
        Date accessTokenExpiresIn = new Date(now + JWT_TOKEN_VALIDITY);
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("memberType", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
 
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + JWT_TOKEN_VALIDITY))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        
        HashMap<String, String> payload = new HashMap<String, String>();
        payload.put("authType", "Bearer");
        payload.put("accessToken", accessToken);
        payload.put("refreshToken", refreshToken);
        
        return payload;
    }
    
    // JWT로부터 계정 정보 얻기
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);
 
        if (claims.get("memberType") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
 
        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("memberType").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        
        return new UsernamePasswordAuthenticationToken(new SecurityUserDetails(claims.getSubject(), authorities),
        		"", authorities);
    }
    
    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }
    
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
