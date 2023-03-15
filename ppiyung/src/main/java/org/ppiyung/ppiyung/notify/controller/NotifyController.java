package org.ppiyung.ppiyung.notify.controller;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.common.entity.BasicResponseEntity;
import org.ppiyung.ppiyung.notify.service.NotifyService;
import org.ppiyung.ppiyung.notify.vo.Notify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/notify")
public class NotifyController {
	
	private Logger log = LogManager.getLogger("base");
	
	@Autowired
	private NotifyService service;
	
	// apply 지원 알림 생성
	@PostMapping(value = "/apply/{apply_id}")
	public ResponseEntity<BasicResponseEntity<Object>> 
			applyNotify(@PathVariable("apply_id") int applyId, Authentication authentication) {
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String memberId = userDetails.getUsername();
		
		
        Notify notify = new Notify();
    	notify.setApplyId(applyId);
    	notify.setMemberId(memberId);
    	

        boolean result = service.insertApplyNotify(notify);
        
        BasicResponseEntity<Object> respBody = null;
        
        int respCode = 0;

        if (result == true) {
            log.debug("지원 알림 등록 성공");
            respBody = new BasicResponseEntity<Object>(true, "지원 알림 생성 완료", result);
            respCode = HttpServletResponse.SC_OK;
        } else {
            log.debug("지원 알림 등록 실패");
            respBody = new BasicResponseEntity<Object>(false, "지원 알림 생성 실패", result);
            respCode = HttpServletResponse.SC_BAD_REQUEST;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
    }
		
	@PostMapping(value = "/suggest/{suggest_id}")
	public ResponseEntity<BasicResponseEntity<Object>>
			insertSuggestNotify(@PathVariable("suggest_id")int suggestId, Authentication authentication){
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String memberId = userDetails.getUsername();
		
		Notify notify = new Notify();
		notify.setMemberId(memberId);
		notify.setSuggestId(suggestId);
		
		boolean result = service.insertSuggestNotify(notify);
		
		BasicResponseEntity<Object> respBody = null;
		
		int respCode = 0;
		
		if(result == true) {
			log.debug("입사제안 알림 등록 성공");
			respBody = new BasicResponseEntity<Object>(true, "입사제안 알림 등록 성공", result);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("입사제안 알림 등록 실패");
			respBody = new BasicResponseEntity<Object>(false, "입사제안 알림 등록 실패", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}
	
		
	
	// 알림 삭제
	@DeleteMapping(value = "/{notification_id}")
	public ResponseEntity<BasicResponseEntity<Object>>
			deleteNotifyHandler(@PathVariable("notification_id") int notification_id){
		
		log.debug(notification_id);
		
		boolean result = service.deleteNotify(notification_id);
		
		BasicResponseEntity<Object> respBody = null;
		
		int respCode = 0;
		
		if(result == true) {
			log.debug("알림 삭제 성공");
			respBody = new BasicResponseEntity<Object>(true, "알림 삭제에 성공했습니다.", result);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("알림 삭제 실패");
			respBody = new BasicResponseEntity<Object>(false, "알림 삭제에 실패했습니다.", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
		
	}

}
