package org.ppiyung.ppiyung.common.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Value("${auth.allowOrigin:http://localhost:9090}")
	private String allowOrigin;
	
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		response.setContentType("application/json; charset=UTF-8"); 
		response.setHeader("Access-Control-Allow-Origin", allowOrigin);
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		
		PrintWriter out = response.getWriter();
		out.print("{ \"success\": \"false\", \"msg\": \"로그인이 필요한 서비스입니다.\" }");
		out.close();
    }
}