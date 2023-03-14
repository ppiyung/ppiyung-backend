package org.ppiyung.ppiyung.notify.controller;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.board.service.BoardService;
import org.ppiyung.ppiyung.common.entity.BasicResponseEntity;
import org.ppiyung.ppiyung.notify.service.NotifyService;
import org.ppiyung.ppiyung.notify.vo.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/notify")
public class NotifyController {

	private Logger log = LogManager.getLogger("base");

	@Autowired
	private NotifyService service;
	
	// 회원별 알림리스트 조회
	@GetMapping(value="/{memberId}")
	public ResponseEntity<BasicResponseEntity<Object>> viewNotificationListByMember(@PathVariable("memberId") String memberId,Authentication authentication){
		
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		List<HashMap<String,Object>> notify = null;
		notify = service.getNotificationList(memberId);
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		//권한이 관리자일 때
		boolean hasAutority = userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		// 권한이  관리자 또는  로그인한 회원아이디가 자기의 ID 일치할 경우
		if (userDetails.getUsername().equals(memberId) || hasAutority) {
			log.debug("회원별 알림 리스트 조회 성공");
			respBody = new BasicResponseEntity<Object>(true, "알림 리스트 내역 조회 완료" , notify);
			respCode = HttpServletResponse.SC_OK;
		}else {
			log.debug("회원별 알림 리스트 조회 실패");
			respBody = new BasicResponseEntity<Object>(false, "알림 리스트 내역 조회 실패.", notify);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
		
	}
	
	// 알림 세부내역 조회
	@GetMapping(value="/{memberId}/{notificationId}")
	public ResponseEntity<BasicResponseEntity<Object>> viewNotificationDetails(@PathVariable("memberId") String memberId, @PathVariable("notificationId") int notificationId, Authentication authentication){
		
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		Notification notification = new Notification();
		notification.setMemberId(memberId);
		notification.setNotificationId(notificationId);
        List<HashMap<String,Object>> notify = null;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //권한이 관리자일 때
		boolean hasAutority = userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
		notify = service.detailNotification(notification);
		// 권한이  관리자 또는  로그인한 회원아이디가 자기의 ID 일치할 경우
		if (userDetails.getUsername().equals(memberId) || hasAutority) {
			 log.debug("알림 세부내역 조회 성공");
	         respBody = new BasicResponseEntity<Object>(true, "알림 세부내역 조회 완료", notify);
	         respCode = HttpServletResponse.SC_OK;
		}else {
			 log.debug("알림 세부내역 조회 실패");
             respBody = new BasicResponseEntity<Object>(false, "알림 세부내역 조회 실패", notify);
             respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
      
	}
}
