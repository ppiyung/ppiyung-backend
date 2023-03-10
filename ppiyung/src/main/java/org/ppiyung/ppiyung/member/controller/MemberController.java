package org.ppiyung.ppiyung.member.controller;


import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.tools.picocli.CommandLine.Parameters;
import org.ppiyung.ppiyung.common.entity.BasicResponseEntity;
import org.ppiyung.ppiyung.member.service.MemberService;
import org.ppiyung.ppiyung.member.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/member")
public class MemberController {
	
	private Logger log = LogManager.getLogger("base");
	
	@Autowired
	private MemberService service;
	
	//회원가입
	@PostMapping(value="signin")
	public ResponseEntity<BasicResponseEntity<Object>>
			signinHandler(@RequestBody Member reqSigninInfo ){
		
		
		boolean result = service.signin(reqSigninInfo);
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		if(result == true) {
			log.debug("회원가입 성공");
			respBody = new BasicResponseEntity<Object>(true, "회원가입에 성공하였습니다.", result);
			respCode = HttpServletResponse.SC_OK;
		}else {
			log.debug("회원가입 실패");
			respBody = new BasicResponseEntity<Object>(false, "회원가입에 실패하였습니다..", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
	
				return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}
	  //개별회원조회
	 @GetMapping (value = "")
	 public void getMember(HttpSession session){
//		HttpSession session = request.getSession(true);
	
		Member currentMember = (Member)session.getAttribute("currentMember");
		
		 
	 }
	
	//회원수정
	@PutMapping(value="")
	public ResponseEntity<BasicResponseEntity<Object>>
			modifyMember(@RequestBody Member reqUpdateInfo ){
		
		
		boolean result = service.modifyMember(reqUpdateInfo);
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		if(result == true) {
			log.debug("회원정보수정 성공");
			respBody = new BasicResponseEntity<Object>(true, "회원정보수정에 성공하였습니다.", result);
			//셋어트리뷰트
			respCode = HttpServletResponse.SC_OK;
		}else {
			log.debug("회원정보수정 실패");
			respBody = new BasicResponseEntity<Object>(false, "회원정보수정에 실패하였습니다..", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
					return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}
	
	
// 회원정보를 수정하면 세션을 날리고, // 새 세션을 부여해서 그 정보를 넣어햐 함
	// 회원 조회는 항상 새로운 세션을 넣어야 함 
	 
	

}
