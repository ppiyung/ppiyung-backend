package org.ppiyung.ppiyung.common.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.ppiyung.ppiyung.common.util.JwtTokenUtil;
import org.ppiyung.ppiyung.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
 
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private MemberService memberService;
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
        // 헤더에서 토큰 추출
        String token = resolveToken((HttpServletRequest) request);
 
        // 토큰 Validation
        if (token != null && jwtTokenUtil.validateToken(token)) {
        	Authentication authentication = jwtTokenUtil.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
 
    // 헤더 예시:
    // Authorization: Bearer JWT_TOKEN
    // Bearer + 공백문자의 7글자를 제거한 문자열을 반환
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}