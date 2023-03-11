package org.ppiyung.ppiyung.member.controller;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

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
	public ResponseEntity<BasicResponseEntity<Object>>
		loginHandler(@RequestBody Member reqLoginInfo) {
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		
		final HashMap<String, String> payload = service.login(reqLoginInfo);
		
		if (payload == null) {
			respBody = new BasicResponseEntity<Object>(true, "로그인에 실패하였습니다.", null);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		} else {
			respBody = new BasicResponseEntity<Object>(true, "로그인에 성공하였습니다.", payload);
			respCode = HttpServletResponse.SC_OK;
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}
	
	@PostMapping(value = "/refresh")
	public ResponseEntity<BasicResponseEntity<Map<String, String>>>
		verifyHandler(@RequestBody HashMap<String, String> reqPayload) {
		
		String newToken = service.regenToken(reqPayload.get("refreshToken"));
		
		BasicResponseEntity<Map<String, String>> respBody = null;
		int respCode = 0;
		if (newToken == null) {
			respBody = new BasicResponseEntity<Map<String, String>>(true, "토큰을 재발급하는데 실패했습니다.", null);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		} else {
			Map<String, String> respPayload = new HashMap<String, String>();
			respPayload.put("accessToken", newToken);
			
			respBody = new BasicResponseEntity<Map<String, String>>(true,
						"토큰을 재발급하는데 성공했습니다.",
						respPayload);
			respCode = HttpServletResponse.SC_OK;
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		return new ResponseEntity<BasicResponseEntity<Map<String, String>>>(respBody, headers, respCode);
	}
		
}

