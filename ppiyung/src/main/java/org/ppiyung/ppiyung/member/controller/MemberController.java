package org.ppiyung.ppiyung.member.controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.common.entity.BasicResponseEntity;
import org.ppiyung.ppiyung.common.entity.PagingEntity;
import org.ppiyung.ppiyung.member.service.MemberService;
import org.ppiyung.ppiyung.member.vo.Image;
import org.ppiyung.ppiyung.member.vo.Member;
import org.ppiyung.ppiyung.member.vo.MemberExtended;
import org.ppiyung.ppiyung.member.vo.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/member")
@CrossOrigin(origins = "${auth.allowOrigin}", allowCredentials = "true", exposedHeaders = {"Content-Disposition"})
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
	
	// 아이디 중복 조회
	@GetMapping(value = "/exist/{memberId}")
	public ResponseEntity<BasicResponseEntity<Object>>
	checkExistMemberHandler(@PathVariable("memberId") String memberIdFromUri,
			Authentication authentication) {

		BasicResponseEntity<Object> respBody = null;
		int respCode = HttpServletResponse.SC_OK;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		Member param = new Member();
		param.setMemberId(memberIdFromUri);
		Member result = service.getMemberInfo(param);
		log.debug(result);
		
		if (result == null) {
			respBody = new BasicResponseEntity<Object>(true,
						memberIdFromUri + "(이)라는 아이디는 존재하지 않습니다. 회원가입이 가능합니다.",
						null);
		} else {
			respBody = new BasicResponseEntity<Object>(false,
					memberIdFromUri + "(이)라는 아이디가 이미 존재합니다. 회원가입이 불가능합니다.",
					null);
		}
		
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}

	// 개별회원조회
	@GetMapping(value = "/{memberId}")
	public ResponseEntity<BasicResponseEntity<Object>>
	getMember(@PathVariable("memberId") String memberIdFromUri,
			@RequestParam(value = "isCompany", defaultValue = "false") boolean isCompany,
			Authentication authentication) {

		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		MemberExtended result = null;
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		
		if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			log.debug("개별회원조회 성공");
			Member param = new Member();
			param.setMemberId(memberIdFromUri);
			result = service.getMemberInfoJoinned(param);
			result.setMemberPw("");
			
			respBody = new BasicResponseEntity<Object>(true, "개별회원조회 성공하였습니다.", result);
			respCode = HttpServletResponse.SC_OK;

		} else if (userDetails.getUsername().equals(memberIdFromUri)) {
			Member param = new Member();
			param.setMemberId(memberIdFromUri);
			result = service.getMemberInfoJoinned(param);
			result.setMemberPw("");
			
			respBody = new BasicResponseEntity<Object>(true, "개별회원조회 성공하였습니다.", result);
			respCode = HttpServletResponse.SC_OK;
		} else if (isCompany) {
			Member param = new Member();
			param.setMemberId(memberIdFromUri);
			result = service.getMemberInfoJoinned(param);
			result.setMemberPw("");
			
			if (result.getMemberType() == 'C') {
				respBody = new BasicResponseEntity<Object>(true, "개별회원조회 성공하였습니다.", result);
				respCode = HttpServletResponse.SC_OK;
			} else {
				result = null;
				respBody = new BasicResponseEntity<Object>(false, "개별회원조회 실패하였습니다.", result);
				respCode = HttpServletResponse.SC_BAD_REQUEST;
			}
			
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
				getAllMember(@RequestParam("pagenum") int pageNum, @RequestParam("amount") int amount,Authentication authentication) {
		
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		PagingEntity pagingEntity = new PagingEntity();
		pagingEntity.setpageNum(pageNum);
		pagingEntity.setAmount(amount);
		List<Member> list = service.getAllMember(pagingEntity);
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		boolean hasAutority = userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
		if(list != null && hasAutority) {
			log.debug("관리자-회원전체조회 성공");
			respBody = new BasicResponseEntity<Object>(true, "전체회원조회 성공하였습니다.", list);
			respCode = HttpServletResponse.SC_OK;
		}else {
			log.debug("관리자-회원전체조회 실패");
			respBody = new BasicResponseEntity<Object>(false, "전체회원조회 실패하였습니다.", null);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}

	// 회원수정
	@PutMapping(value = "/{memberId}")
	public ResponseEntity<BasicResponseEntity<Object>> modifyMember(@RequestBody Member reqUpdateInfo,
			@PathVariable("memberId") String memberId,
			Authentication authentication) {

		boolean result = false;
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		boolean hasAuthority = userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		if (userDetails.getUsername().equals(memberId) ||
				hasAuthority) { // 자신의 정보를 수정하는 경우이거나 관리자인 경우
			log.debug("회원정보수정 - 권한 확인됨");
			reqUpdateInfo.setMemberId(memberId);
			result = service.modifyMember(reqUpdateInfo);
		}
		
		if (result == true) {
			log.debug("회원정보수정 성공");
			respBody = new BasicResponseEntity<Object>(true, "회원정보수정에 성공하였습니다.", result);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("회원정보수정 실패");
			respBody = new BasicResponseEntity<Object>(false, "회원정보수정에 실패하였습니다..", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}
	
	
	
	//회원탈퇴 - 일반유저 + 관리자 회원탈퇴 가능
	@DeleteMapping(value = "/{memberId}")
	public ResponseEntity<BasicResponseEntity<Object>> dropMember(
			@PathVariable("memberId") String memberId,
			Authentication authentication) {
		
		boolean result = false;
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();

	
		if (userDetails.getUsername().equals(memberId)) {
			log.debug("회원탈퇴 - 권한 확인됨" + memberId);
			result = service.leaveMember(memberId);
			
		}
			if (result == true) {
				log.debug("회원탈퇴 성공");
				respBody = new BasicResponseEntity<Object>(true, "회원탈퇴에 성공하였습니다.", result);
				respCode = HttpServletResponse.SC_OK;
			} else {
				log.debug("회원탈퇴 실패");
				respBody = new BasicResponseEntity<Object>(false, "회원탈퇴에 실패하였습니다..", result);
				respCode = HttpServletResponse.SC_BAD_REQUEST;
			}
			return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
		
	}
	
	//직무 분야별 이력서 공개한 회원 목록 조회 
	@GetMapping(value="/resume/{workAreaId}")
	public ResponseEntity<BasicResponseEntity<Object>> 
				workAreaOpenMember(@PathVariable("workAreaId") String workAreaId ,Authentication authentication) {
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		List<Member> list = service.getResumeOpenMember(workAreaId);
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		boolean hasAutority = userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_COMPANY"));
		if(list != null && hasAutority) {
			log.debug(" 공개한 회원 목록 조회 성공");
			respBody = new BasicResponseEntity<Object>(true, " 공개한 회원 목록 조회성공하였습니다.", list);
			respCode = HttpServletResponse.SC_OK;
		}else {
			log.debug(" 공개한 회원 목록 조회 실패");
			respBody = new BasicResponseEntity<Object>(false, " 공개한 회원 목록 조회 실패하였습니다.", null);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
		
	}
	
	@PostMapping(value="/img")
	public ResponseEntity<BasicResponseEntity<Object>>
		uploadImageHandler(@RequestParam("file") MultipartFile file, Authentication authentication) {
		

		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		final String memberId = userDetails.getUsername();
		
		Image result = null;
		
		Image param = new Image();
		param.setMemberId(memberId);
		if (service.getImageFileInfo(param) == null) {
			result = service.saveImageFile(file, memberId, false);
		} else {
			result = service.saveImageFile(file, memberId, true);
		}

		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		if (result != null) {
			log.debug("이미지 업로드 성공");
			respBody = new BasicResponseEntity<Object>(true, "이미지 업로드에 성공하였습니다.", result);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("이미지 업로드 실패");
			respBody = new BasicResponseEntity<Object>(false, "이미지 업로드에 실패하였습니다.", null);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}
	
	//이력서 
	@PostMapping(value="/resume")
	public ResponseEntity<BasicResponseEntity<Object>>
		uploadResumeHandler(@RequestParam("file") MultipartFile file, Authentication authentication) {
		

		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		final String memberId = userDetails.getUsername();
		
		Resume result = null;
		
		Resume param = new Resume();
		param.setMemberId(memberId);
		if (service.getResumeFileInfo(param) == null) {
			result = service.saveResumeFile(file, memberId, false);
		} else {
			result = service.saveResumeFile(file, memberId, true);
		}

		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		if (result != null) {
			log.debug("이력서 업로드 성공");
			respBody = new BasicResponseEntity<Object>(true, "이력서 업로드에 성공하였습니다.", result);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("이력서 업로드 실패");
			respBody = new BasicResponseEntity<Object>(false, "이력서 업로드에 실패하였습니다.", null);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}
	
	@GetMapping(value="/{memberId}/resume")
	public void downloadResumeHandler(
			@PathVariable("memberId") String memberId, Authentication authentication,
			HttpServletResponse response) {
		
		Resume resume = null;
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		if (userDetails.getUsername().equals(memberId)
				|| userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
				|| userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_COMPANY"))) {
			Resume param = new Resume();
			param.setMemberId(memberId);
			resume = service.getResumeFileInfo(param);
		}
		
		service.serveResumeFile(resume, response);
	}
}
