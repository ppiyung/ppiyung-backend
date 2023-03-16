package org.ppiyung.ppiyung.recruit.controller;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.common.entity.BasicResponseEntity;
import org.ppiyung.ppiyung.common.entity.PagingEntity;
import org.ppiyung.ppiyung.recruit.service.RecruitService;
import org.ppiyung.ppiyung.recruit.vo.Apply;
import org.ppiyung.ppiyung.recruit.vo.BookMark;
import org.ppiyung.ppiyung.recruit.vo.Recruit;
import org.ppiyung.ppiyung.recruit.vo.RecruitOption;
import org.ppiyung.ppiyung.recruit.vo.Suggest;
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

@RestController
@RequestMapping(value = "/recruit")
@CrossOrigin(origins = "${auth.allowOrigin}", allowCredentials = "true")
public class RecruitController {

	private Logger log = LogManager.getLogger("base");
	
	@Autowired
	private RecruitService service; 
	
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
	public ResponseEntity<BasicResponseEntity<Object>> 
		
	    updateRecruitNotice(@RequestBody Recruit recruitNoticeContent, @PathVariable("recruit_id") int recruit_id){ 
		
		log.debug(recruitNoticeContent);
		
		recruitNoticeContent.setRecruitId(recruit_id);
		boolean result = service.updateRecruitNotice(recruitNoticeContent);
		
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
	
	// 기업회원- 공고 즉시 마감
	@PutMapping(value="/emergency/{recruit_id}")
	public ResponseEntity<BasicResponseEntity<Object>> 
		closeRecruitNotice(@PathVariable("recruit_id") int recruitId){ 
		
		log.debug(recruitId);
		boolean result = service.closeRecruitNotice( recruitId);
		
		BasicResponseEntity<Object> respBody = null;
		int respCode=0;
		
		if(result == true) {
			log.debug("공고 즉시 마감 성공");
			respBody = new BasicResponseEntity<Object> (true, "공고 즉시 마감 완료", result);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("공고 즉시 마감 실패");
			respBody = new BasicResponseEntity<Object> (false, "공고 즉시 마감 실패", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	
	}
	
	// 전체 채용 공고 조회 
	@GetMapping(value="")
	public ResponseEntity<BasicResponseEntity<Object>> getRecruitList(@RequestParam("page") int pageNum,
			@RequestParam("size") int amount,
			@RequestParam(value = "workArea", defaultValue = "0") int workAreaId,
			@RequestParam(value = "company", defaultValue = "") String companyId,
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			@RequestParam(value = "closed", defaultValue = "false") boolean closed){ 
  		
		RecruitOption option = new RecruitOption(pageNum, amount);
		option.setRecruitId(0);
		option.setIncludeClosed(closed);
		if (workAreaId != 0) {
			option.setWorkAreaId(workAreaId);
		}
		if (!companyId.equals("")) {
			option.setCompanyId(companyId);
		}
		if (!keyword.equals("")) {
			option.setKeyword(keyword);
		}
		
		int total = service.getRecruitListTotal(option);
		List<Recruit> result = service.getRecruitList(option);	
        log.debug("!!" + result);
		BasicResponseEntity<Object> respBody = null;
		int respCode=0;
		
		if(result != null) {
			log.debug("전체 공고 조회 성공");
			
			Map<String, Object> payload = new HashMap<String, Object>();
			payload.put("total", total);
			payload.put("list", result);
			
			respBody = new BasicResponseEntity<Object> (true, "전체 공고 조회 완료", payload);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("전체 공고 조회 실패");
			respBody = new BasicResponseEntity<Object> (false, "전체 조회 실패", null);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
		
	}
	
	// 기업별 채용 공고 현황 조회 
    @GetMapping(value="/statistics/{company_id}")
    public ResponseEntity<BasicResponseEntity<Object>> 
    getRecruitStatusOfCompany(@PathVariable("company_id") String companyId, 
    		Authentication authentication) {
        
    	
        BasicResponseEntity<Object> respBody = null;
        int respCode=0;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		HashMap<String, Object> result = null;
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		
		if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			
			result = service.getRecruitStatusOfCompany(companyId);
		    log.debug(result);
		    
			if(result != null) {   
				log.debug("조회 성공");
				respBody = new BasicResponseEntity<Object> (true, "조회 완료", result);
				respCode = HttpServletResponse.SC_OK;
		    } else {
		    	log.debug("조회 실패");
				respBody = new BasicResponseEntity<Object> (false, "조회 실패", result);
				respCode = HttpServletResponse.SC_BAD_REQUEST;
		    }
		} else {
			log.debug("조회 실패");
			respBody = new BasicResponseEntity<Object> (false, "조회 실패", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
    	return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);

    }
    
 // 전체 채용 공고 현황 조회 
    @GetMapping(value="/statistics")
    public ResponseEntity<BasicResponseEntity<Object>> getRecruitStatus(Authentication authentication) {
        
    	
        BasicResponseEntity<Object> respBody = null;
        int respCode=0;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		HashMap<String, Object> result = null;
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		
		if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			
			result = service.getRecruitStatus();
		    log.debug(result);
		    
			if(result != null) {   
			log.debug("조회 성공");
			respBody = new BasicResponseEntity<Object> (true, "조회 완료", result);
			respCode = HttpServletResponse.SC_OK;
		    } else {
		    	log.debug("조회 실패");
				respBody = new BasicResponseEntity<Object> (false, "조회 실패", result);
				respCode = HttpServletResponse.SC_BAD_REQUEST;
		    }
		} else {
			log.debug("조회 실패");
			respBody = new BasicResponseEntity<Object> (false, "조회 실패", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
    	
    	return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);

    }
    
    // 기업별 채용 공고 조회
    @GetMapping(value="/companyinfo/{company_id}")
    public ResponseEntity<BasicResponseEntity<Object>> 
    getRecruitListCompany(@PathVariable("company_id") String companyId) {
       
        List<Recruit> result = service.getRecruitListOfCompany(companyId);
		log.debug(companyId);
		
		log.debug(result);
		BasicResponseEntity<Object> respBody = null;
		int respCode=0;
		
		if(result != null) {
			log.debug("기업별 공고 조회 성공");
			respBody = new BasicResponseEntity<Object> (true, "기업별 공고 조회 완료", result);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("기업별 공고 조회 실패");
			respBody = new BasicResponseEntity<Object> (false, "기업별 조회 실패", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);


    }
    
    // 일반회원 - 지원하기
    @PostMapping(value="/apply/{recruit_id}")
    public ResponseEntity<BasicResponseEntity<Object>> 
	
    appplyForJob(@PathVariable("recruit_id") int recruitId, Authentication authentication){
    	 
    	    UserDetails userDetails = (UserDetails)authentication.getPrincipal();
    	    String memberId = userDetails.getUsername();
    	 
    	    Apply apply = new Apply();
    	    apply.setMemberId(memberId);
    	    apply.setRecruitId(recruitId);
    	    
    	    log.debug(apply);
    	    
    	    boolean result = service.applyForJob(apply);
    		
    		BasicResponseEntity<Object> respBody = null;
    		int respCode=0;
    		
    		if(result == true) {
    			log.debug("지원하기 성공");
    			respBody = new BasicResponseEntity<Object> (true, "지원하기 완료", result);
    			respCode = HttpServletResponse.SC_OK;
    		} else {
    			log.debug("지원하기 실패");
    			respBody = new BasicResponseEntity<Object> (false, "지원하기 실패", result);
    			respCode = HttpServletResponse.SC_BAD_REQUEST;
    		}
    		
    		
    		HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(new MediaType("application", "json",
    				Charset.forName("UTF-8")));
    		
    		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
    		
    }
    
    // 일반회원-회원별 지원 현황 리스트 조회
    @GetMapping(value="/apply/member/{member_id}")
    public ResponseEntity<BasicResponseEntity<Object>> 
    getApplyListOfMember(@PathVariable("member_id") String memberId, 
    		Authentication authentication) {
        
    	
        BasicResponseEntity<Object> respBody = null;
        int respCode=0;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));
		
		List<Apply> result = null;
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		
		if (userDetails.getUsername().equals(memberId)) {
			
			result = service.getApplyListOfMember(memberId);
		    log.debug(result);
		    
			if(result != null) {   
			log.debug("회원별 지원 현황 조회 성공");
			respBody = new BasicResponseEntity<Object> (true, "공고 조회 완료", result);
			respCode = HttpServletResponse.SC_OK;
		    } else {
		    	log.debug("공고 조회 실패");
				respBody = new BasicResponseEntity<Object> (false, "공고 조회 실패", result);
				respCode = HttpServletResponse.SC_BAD_REQUEST;
		    }
		} else {
			log.debug("공고 조회 실패");
			respBody = new BasicResponseEntity<Object> (false, "공고 조회 실패", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
    	
    	return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);

    }
    
    // 기업회원 - 채용공고별 지원자 리스트 조회
    @GetMapping(value="/apply/company")
    public ResponseEntity<BasicResponseEntity<Object>> 
    getApplicantsByRecruitNotice(@RequestParam("recruitid") int recruitId, @RequestParam("companyid") String companyId, 
    		Authentication authentication) {
        
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		List<HashMap<String, Object>> result = null;
		log.debug("!!" +result);
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		
		if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_COMPANY")) 
				&& userDetails.getUsername().equals(companyId)) {
			
			result = service.getApplicantsByRecruitNotice(recruitId);
			
			respBody = new BasicResponseEntity<Object>(true, "개별회원조회 성공하였습니다.", result);
			respCode = HttpServletResponse.SC_OK;

		} else {
			log.debug("개별회원조회 실패");
			respBody = new BasicResponseEntity<Object>(false, "개별회원조회 실패하였습니다.", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);

	}
    
    
    // 기업회원 - 입사 제안 보내기
    @PostMapping(value="/suggest/{member_id}")
    public ResponseEntity<BasicResponseEntity<Object>> 
	
    jobOffer(@PathVariable("member_id") String memberId, Authentication authentication){
    	 
    	    UserDetails userDetails = (UserDetails)authentication.getPrincipal();
    	    String companyId = userDetails.getUsername();
    	 
    	    Suggest suggest = new Suggest();
    	    suggest.setCompanyId(companyId);
    	    suggest.setMemberId(memberId);
    	    
    	    log.debug(suggest);
    	    
    	    boolean result = service.jobOffer(suggest);
    		
    		BasicResponseEntity<Object> respBody = null;
    		int respCode=0;
    		
    		if(result == true) {
    			log.debug("입사제안하기 성공");
    			respBody = new BasicResponseEntity<Object> (true, "입사제안하기 완료", result);
    			respCode = HttpServletResponse.SC_OK;
    		} else {
    			log.debug("입사제안하기 실패");
    			respBody = new BasicResponseEntity<Object> (false, "입사제안하기 실패", result);
    			respCode = HttpServletResponse.SC_BAD_REQUEST;
    		}
    		
    		
    		HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(new MediaType("application", "json",
    				Charset.forName("UTF-8")));
    		
    		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
    		
    }
    
    // 일반회원 -회원별 받은 입사 제안 조회
    @GetMapping(value="/suggest/member/{member_id}")
    public ResponseEntity<BasicResponseEntity<Object>> 
    getJobOfferOfMember(@PathVariable("member_id") String memberId,
    		@RequestParam("page") int pageNum,
			@RequestParam("size") int amount,
    		Authentication authentication) {
        
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		List<Suggest> result = null;
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		
		if (userDetails.getUsername().equals(memberId)) {
			
			result = service.getJobOfferOfMember(memberId);
			
			respBody = new BasicResponseEntity<Object>(true, "개별회원 입사제안 조회 성공하였습니다.", result);
			respCode = HttpServletResponse.SC_OK;

		} else {
			
			respBody = new BasicResponseEntity<Object>(false, "개별회원입사제안 조회 실패하였습니다.", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);

	}
    //기업회원 - 기업별 보낸 입사제안 목록 조회
    @GetMapping(value="/suggest/company/{company_id}")
    public ResponseEntity<BasicResponseEntity<Object>> 
    getJobOfferOfCompany(@PathVariable("company_id") String companyId, 
    		Authentication authentication) {
        
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		List<Suggest> result = null;
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		
		if (userDetails.getUsername().equals(companyId)) {
			
			result = service.getJobOfferOfCompany(companyId);
			
			respBody = new BasicResponseEntity<Object>(true, "개별회원 입사제안 조회 성공하였습니다.", result);
			respCode = HttpServletResponse.SC_OK;

		} else {
			
			respBody = new BasicResponseEntity<Object>(false, "개별회원입사제안 조회 실패하였습니다.", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);

	}
    
    //채용공고 상세조회
	 @GetMapping(value="/{recruit_id}")
	 public ResponseEntity<BasicResponseEntity<Object>> 
	 		getRecruitDetailInfo(@PathVariable("recruit_id") String recruitId) {
		 
		   List<Recruit> result = service.getRecruitDetailInfo(recruitId);
			log.debug(recruitId);
			
			log.debug(result);
			BasicResponseEntity<Object> respBody = null;
			int respCode=0;
			
			if(result != null) {
				log.debug("채용공고 상세 조회 성공");
				respBody = new BasicResponseEntity<Object> (true, "채용공고 상세 조회 성공", result);
				respCode = HttpServletResponse.SC_OK;
			} else {
				log.debug("채용공고 상세 조회 실패");
				respBody = new BasicResponseEntity<Object> (false, "채용공고 상세 조회 성공실패", result);
				respCode = HttpServletResponse.SC_BAD_REQUEST;
			}
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json",
					Charset.forName("UTF-8")));
			
			return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
		 
	 }
	 
	   // 관심 채용 정보 추가
	   @PostMapping(value = "/bookmark/{recruit_id}")
	   public ResponseEntity<BasicResponseEntity<Object>> addBookmarkRecruit(@PathVariable("recruit_id") int recruitId,
	         Authentication authentication) {

	      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	      String memberId = userDetails.getUsername();

	      BookMark bookMark = new BookMark();
	      bookMark.setRecruitId(recruitId);
	      bookMark.setMemberId(memberId);

	      boolean result = service.addBookmarkRecruit(bookMark);

	      BasicResponseEntity<Object> respBody = null;
	      int respCode = 0;

	      if (result == true) {
	         log.debug("북마크 성공");
	         respBody = new BasicResponseEntity<Object>(true, "북마크 완료", result);
	         respCode = HttpServletResponse.SC_OK;
	      } else {
	         log.debug("북마크 실패");
	         respBody = new BasicResponseEntity<Object>(false, "북마크 실패", result);
	         respCode = HttpServletResponse.SC_BAD_REQUEST;
	      }

	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

	      return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);

	   }

	   // 관심 채용 정보 삭제
	   @DeleteMapping(value = "/bookmark/{recruit_id}")
	   public ResponseEntity<BasicResponseEntity<Object>> removeBookmarkRecruit(@PathVariable("recruit_id") int recruitId,
	         Authentication authentication) {

	      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	      String memberId = userDetails.getUsername();

	      BookMark bookMark = new BookMark();
	      bookMark.setRecruitId(recruitId);
	      bookMark.setMemberId(memberId);

	      boolean result = service.removeBookmarkRecruit(bookMark);

	      BasicResponseEntity<Object> respBody = null;
	      int respCode = 0;

	      if (result == true) {
	         log.debug("북마크 취소 성공");
	         respBody = new BasicResponseEntity<Object>(true, "북마크 취소 완료", result);
	         respCode = HttpServletResponse.SC_OK;
	      } else {
	         log.debug("북마크 취소 실패");
	         respBody = new BasicResponseEntity<Object>(false, "북마크 취소 실패", result);
	         respCode = HttpServletResponse.SC_BAD_REQUEST;
	      }

	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

	      return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	   }
	   
	// 회원별 관심 채용 정보 리스트 조회
	   @GetMapping(value = "/bookmark/{member_id}")
	   public ResponseEntity<BasicResponseEntity<Object>> getMyBookmarkList(@PathVariable("member_id") String memberId,
	         Authentication authentication) {

	      BasicResponseEntity<Object> respBody = null;
	      int respCode = 0;
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

	      List<HashMap<String,Object>> list = null;
	      UserDetails userDetails = (UserDetails) authentication.getPrincipal();

	      if (userDetails.getUsername().equals(memberId)) {
	         list = service.getMyBookmarkList(memberId);
	         log.debug(list);
	         if (list != null) {
	            log.debug("회원별 관심 채용 정보 리스트 조회 성공");
	            respBody = new BasicResponseEntity<Object>(true, "회원별 관심 채용 정보 리스트 조회 완료", list);
	            respCode = HttpServletResponse.SC_OK;
	         } else {
	            log.debug("공고 조회 실패");
	            respBody = new BasicResponseEntity<Object>(false, "회원별 관심 채용 정보 리스트 조회 실패", list);
	            respCode = HttpServletResponse.SC_BAD_REQUEST;
	         }
	      } else {
	         log.debug("공고 조회 실패");
	         respBody = new BasicResponseEntity<Object>(false, "회원별 관심 채용 정보 리스트 조회 실패", list);
	         respCode = HttpServletResponse.SC_BAD_REQUEST;
	      }

	      return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);

	   }
	   
	
}