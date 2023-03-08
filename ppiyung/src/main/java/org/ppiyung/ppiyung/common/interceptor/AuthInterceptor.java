package org.ppiyung.ppiyung.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.test.MemberTestVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

	Logger log = LogManager.getLogger("base");
	
	@Value("${auth.allowOrigin}")
	private String allowOrigin;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		MemberTestVO currentMember = (MemberTestVO)session.getAttribute("currentMember");
		
		log.debug("세션 로그인 정보:" + currentMember);
		
		if(currentMember == null || currentMember.getMemberId() == null) {
			response.setContentType("application/json; charset=UTF-8"); 
			response.setHeader("Access-Control-Allow-Origin", allowOrigin);
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			
			PrintWriter out = response.getWriter();
			out.print("{ \"success\": \"false\", \"msg\": \"로그인이 필요한 서비스입니다.\" }");
			out.close();
			return false;
		}
		return true;
	}
}