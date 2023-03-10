package org.ppiyung.ppiyung.member.controller;

import java.nio.charset.Charset;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.common.entity.BasicResponseEntity;
import org.ppiyung.ppiyung.member.service.MemberService;
import org.ppiyung.ppiyung.member.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "${auth.allowOrigin}", allowCredentials = "true")
public class AuthController {
	
	private Logger log = LogManager.getLogger("base");
	
	@Autowired
	private MemberService service;

	@PostMapping(value = "/login")
	public ResponseEntity<BasicResponseEntity<Member>>
		loginHandler(@RequestBody Member reqLoginInfo, HttpSession session) {
		
		Member member = service.login(reqLoginInfo);
		
		BasicResponseEntity<Member> respBody = null;
		int respCode = 0;
		if (member == null || member.getMember_name() == null) {
			log.debug("로그인 실패처리 시작");
			respBody = new BasicResponseEntity<Member>(true, "로그인에 실패하였습니다.", null);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		} else {
			log.debug("로그인 성공처리 시작: " + member);
			respBody = new BasicResponseEntity<Member>(true, "로그인에 성공하였습니다.", member);
			member.setMember_pw("");
			session.setAttribute("currentMember", member);
			respCode = HttpServletResponse.SC_OK;
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		return new ResponseEntity<BasicResponseEntity<Member>>(respBody, headers, respCode);
	}
	
	@PostMapping(value = "/logout")
	public ResponseEntity<HashMap<String, Object>>
		loginHandler(HttpSession session) {
		session.invalidate();
		
		HashMap<String, Object> respBody = new HashMap<String, Object>();
		respBody.put("success", true);
		respBody.put("msg", "로그아웃에 성공하셨습니다.");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		return new ResponseEntity<HashMap<String, Object>>(respBody, headers, HttpServletResponse.SC_OK);
	}
	
	@PostMapping(value = "/verify")
	public ResponseEntity<BasicResponseEntity<Member>>
		verifyHandler(HttpSession session) {
		
		Member member = (Member)session.getAttribute("currentMember");
		
		BasicResponseEntity<Member> respBody = new BasicResponseEntity<Member>();
		
		int respCode = 0;
		if(member == null || member.getMember_id() == null) {
			respCode = HttpServletResponse.SC_FORBIDDEN;
			respBody.setSuccess(false);
			respBody.setMsg("로그인되지 않은 사용자입니다.");
		} else {
			respCode = HttpServletResponse.SC_OK;
			
			member.setMember_pw("");
			respBody.setSuccess(true);
			respBody.setMsg("로그인되어있는 사용자입니다.");
			respBody.setPayload(member);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		return new ResponseEntity<BasicResponseEntity<Member>>(respBody, headers, respCode);
	}
		
}

