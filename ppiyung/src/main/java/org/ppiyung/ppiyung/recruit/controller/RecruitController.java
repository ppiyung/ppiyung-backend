package org.ppiyung.ppiyung.recruit.controller;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.common.entity.BasicResponseEntity;
import org.ppiyung.ppiyung.member.vo.Member;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/recruit")
public class RecruitController {

	private Logger log = LogManager.getLogger("base");
	
	@GetMapping(value = "/")
	public ResponseEntity<BasicResponseEntity<String>>
		getRecruitHomeHandler() {
		
		BasicResponseEntity<String> respBody = new BasicResponseEntity<String>(true, "테스트용 컨트롤러입니다.", "테스트");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		return new ResponseEntity<BasicResponseEntity<String>>(respBody, headers, HttpServletResponse.SC_OK);
	}
	
}
