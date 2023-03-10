package org.ppiyung.ppiyung.member.controller;

import java.nio.charset.Charset;
import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

	private Logger log = LogManager.getLogger("base");

	@Autowired
	private MemberService service;

	// 회원가입
	@PostMapping(value = "signin")
	public ResponseEntity<BasicResponseEntity<Object>> signinHandler(@RequestBody Member reqSigninInfo) {

		boolean result = service.signin(reqSigninInfo);
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		if (result == true) {
			log.debug("회원가입 성공");
			respBody = new BasicResponseEntity<Object>(true, "회원가입에 성공하였습니다.", result);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("회원가입 실패");
			respBody = new BasicResponseEntity<Object>(false, "회원가입에 실패하였습니다..", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}

		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}

	// 개별회원조회
	@GetMapping(value = "/{memberId}")
	public ResponseEntity<BasicResponseEntity<Object>> getMember(@PathVariable("memberId") String memberIdFromUri,
			HttpSession session) {

		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		Member result = null;
		Member currentMember = (Member) session.getAttribute("currentMember");
		if (currentMember.getMember_type() == 'A') {
			log.debug("개별회원조회 성공");
			Member param = new Member();
			param.setMember_id(memberIdFromUri);
			result = service.getMemberInfo(param);
			respBody = new BasicResponseEntity<Object>(true, "개별회원조회 성공하였습니다.", result);
			respCode = HttpServletResponse.SC_OK;

		} else if (currentMember.getMember_id().equals(memberIdFromUri)) {
			result = service.getMemberInfo(currentMember);
			respBody = new BasicResponseEntity<Object>(true, "개별회원조회 성공하였습니다.", result);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("개별회원조회 실패");
			respBody = new BasicResponseEntity<Object>(false, "개별회원조회 실패하였습니다.", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);

	}

	// 관리자 회원 전체조회
	@GetMapping(value = "")
	public ResponseEntity<BasicResponseEntity<Object>> 
				getAllMember(HttpSession session) {
		
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		Member currentMember = (Member) session.getAttribute("currentMember");
		
		List<Member> list = service.getAllMember();
		
		
		if(list != null && currentMember.getMember_type() == 'A') {
			log.debug("관리자-회원전체조회 성공");
			respBody = new BasicResponseEntity<Object>(true, "개별회원조회 성공하였습니다.", list);
			respCode = HttpServletResponse.SC_OK;
		}else {
			log.debug("관리자-회원전체조회 실패");
			respBody = new BasicResponseEntity<Object>(false, "개별회원조회 실패하였습니다.",list);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}

	// 회원수정
	@PutMapping(value = "")
	public ResponseEntity<BasicResponseEntity<Object>> modifyMember(@RequestBody Member reqUpdateInfo,
			HttpSession session) {

		boolean result = service.modifyMember(reqUpdateInfo);
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		if (result == true) {
			log.debug("회원정보수정 성공");
			respBody = new BasicResponseEntity<Object>(true, "회원정보수정에 성공하였습니다.", result);
			respCode = HttpServletResponse.SC_OK;
			Member currentMember = (Member) session.getAttribute("currentMember");
			Member member = service.getMemberInfo(currentMember);
			session.setAttribute("currentMember", member);

		} else {
			log.debug("회원정보수정 실패");
			respBody = new BasicResponseEntity<Object>(false, "회원정보수정에 실패하였습니다..", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}

}
