package org.ppiyung.ppiyung.common.util;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.member.vo.Member;
import org.ppiyung.ppiyung.member.vo.SecurityUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtTokenUtil {
	
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000; // 5시간
//	public static final long JWT_TOKEN_VALIDITY = 60 * 1000; // 1분
	public static final long REFRESH_TOKEN_VALIDITY = 24 * 60 * 60 * 14 * 1000; // 14일
	
	private final Key accesskey;
	private final Key refreshkey;
	
	private Logger log = LogManager.getLogger("base");
    
//	// JWT 생성을 위해 사용하는 Key 객체를 생성
//    public JwtTokenUtil(@Value("${auth.jwtSecret}") String secretKey) {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//    }
//    
	// JWT 생성을 위해 사용하는 Key 객체를 생성
    public JwtTokenUtil(String accessSecret, String refreshSecret) {
        byte[] keyBytes = Decoders.BASE64.decode(accessSecret);
        this.accesskey = Keys.hmacShaKeyFor(keyBytes);
        
        keyBytes = Decoders.BASE64.decode(refreshSecret);
        this.refreshkey = Keys.hmacShaKeyFor(keyBytes);
    }
    
    // JWT 생성
    public HashMap<String, Object> generateToken(Authentication authentication) {
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
                .signWith(accesskey, SignatureAlgorithm.HS256)
                .compact();
 
        Date refreshTokenExpiresIn = new Date(now + REFRESH_TOKEN_VALIDITY);
        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("memberType", authorities)
                .setExpiration(refreshTokenExpiresIn)
                .signWith(refreshkey, SignatureAlgorithm.HS256)
                .compact();
        
        HashMap<String, Object> payload = new HashMap<String, Object>();
        payload.put("authType", "Bearer");
        payload.put("accessToken", accessToken);
        payload.put("refreshToken", refreshToken);
        payload.put("accessTokenExpiresIn", accessTokenExpiresIn);
        payload.put("refreshTokenExpiresIn", refreshTokenExpiresIn);
        
        Member member = ((SecurityUserDetails)authentication.getPrincipal()).getMember();
        member.setMemberPw("");
        payload.put("memberInfo", member);
        
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
    
    // 토큰 리프레시
    public HashMap<String, Object> reGenerateTokenFromRefreshToken(String refreshToken) {
    	// 리프레시 토큰 검증
    	if (!validateToken(refreshToken, true)) {
    		return null;
    	}
    	
    	// 리프레시 토큰으로부터 계정 정보 가져오기
    	Claims claims = parseClaims(refreshToken, true);
    	String userId = claims.getSubject();
        String authorities = claims.get("memberType").toString();
 
        long now = (new Date()).getTime();
        
        Date accessTokenExpiresIn = new Date(now + JWT_TOKEN_VALIDITY);
        String accessToken = Jwts.builder()
                .setSubject(userId)
                .claim("memberType", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(accesskey, SignatureAlgorithm.HS256)
                .compact();
        
        HashMap<String, Object> payload = new HashMap<String, Object>();
        payload.put("accessToken", accessToken);
        payload.put("accessTokenExpiresIn", accessTokenExpiresIn);
        
        return payload;
    }
    
    // 토큰 검증
    public boolean validateToken(String token) {
    	return validateToken(token, false);
    }
    
    public boolean validateToken(String token, boolean isRefresh) {
        try {
        	if (!isRefresh) {
                Jwts.parserBuilder().setSigningKey(accesskey).build().parseClaimsJws(token);
                return true;	
        	} else {
                Jwts.parserBuilder().setSigningKey(refreshkey).build().parseClaimsJws(token);
                return true;
        	}
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
    
    // 클레임 정보 가져오기
    private Claims parseClaims(String accessToken) {
    	return parseClaims(accessToken, false);
    }
    
    private Claims parseClaims(String accessToken, boolean isRefresh) {
        try {
        	if(!isRefresh) {
                return Jwts.parserBuilder().setSigningKey(accesskey).build().parseClaimsJws(accessToken).getBody();
        	} else {
                return Jwts.parserBuilder().setSigningKey(refreshkey).build().parseClaimsJws(accessToken).getBody();
        	}
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
