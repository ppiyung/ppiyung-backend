package org.ppiyung.ppiyung.recruit.controller;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.common.entity.BasicResponseEntity;
import org.ppiyung.ppiyung.recruit.service.RecruitService;
import org.ppiyung.ppiyung.recruit.vo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/recruit")
public class RecruitController {

	private Logger log = LogManager.getLogger("base");
	
	@Autowired
	private RecruitService service; 
	
	@GetMapping(value = "")
	public ResponseEntity<BasicResponseEntity<String>>
		getRecruitHomeHandler() {
		
		BasicResponseEntity<String> respBody = new BasicResponseEntity<String>(true, "테스트용 컨트롤러입니다.", "테스트");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		return new ResponseEntity<BasicResponseEntity<String>>(respBody, headers, HttpServletResponse.SC_OK);
	}
	
	// 기업회원 - 새 공고 게시
	@PostMapping(value="")
	public ResponseEntity<BasicResponseEntity<Object>> insertRecruitNotice(@RequestBody Recruit recruitNoticeContent){ 
  		log.debug(recruitNoticeContent);
		boolean result = service.insertRecruitNotice(recruitNoticeContent);
		
		BasicResponseEntity<Object> respBody = null;
		int respCode=0;
		
		if(result == true) {
			log.debug("공고 업로드 성공");
			respBody = new BasicResponseEntity<Object> (true, "공고 게시 완료", result);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("공고 업로드 실패");
			respBody = new BasicResponseEntity<Object> (false, "공고 게시 실패", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
		
	}
	
	// 기업회원 - 공고 수정
	@PutMapping(value="/{recruit_id}")
	public ResponseEntity<BasicResponseEntity<Object>> updateRecruitNotice(@RequestBody Recruit recruitNoticeContent, @PathVariable("recruit_id") int recruit_id){ 
		log.debug(recruitNoticeContent);
		boolean result = service.updateRecruitNotice(recruitNoticeContent, recruit_id);
		
		BasicResponseEntity<Object> respBody = null;
		int respCode=0;
		
		if(result == true) {
			log.debug("공고 업데이트 성공");
			respBody = new BasicResponseEntity<Object> (true, "공고 수정 완료", result);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("공고 업데이트 실패");
			respBody = new BasicResponseEntity<Object> (false, "공고 수정 실패", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
		

	}
	
	

}

