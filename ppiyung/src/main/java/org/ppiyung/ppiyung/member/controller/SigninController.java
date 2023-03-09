package org.ppiyung.ppiyung.member.controller;


import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.common.entity.BasicResponseEntity;
import org.ppiyung.ppiyung.member.service.MemberService;
import org.ppiyung.ppiyung.member.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/member")
public class SigninController {
	
	private Logger log = LogManager.getLogger("base");
	
	@Autowired
	private MemberService service;
	
	@PostMapping(value="/signin")
	public ResponseEntity<BasicResponseEntity<Integer>>
			signinHandler(@RequestBody Member reqSigninInfo ){
		log.debug(reqSigninInfo);
		
		int result = service.signin(reqSigninInfo);
		BasicResponseEntity<Integer> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		if(result == 1) {
			
			respBody = new BasicResponseEntity<Integer>(true, "회원가입에 성공하였습니다.", result);
			respCode = HttpServletResponse.SC_OK;
		}
	
				return new ResponseEntity<BasicResponseEntity<Integer>>(respBody, headers, respCode);
	}
	
	

}
