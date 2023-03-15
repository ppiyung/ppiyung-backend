package org.ppiyung.ppiyung.board.controller;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.board.service.BoardService;
import org.ppiyung.ppiyung.board.vo.Board;
import org.ppiyung.ppiyung.board.vo.BoardList;
import org.ppiyung.ppiyung.board.vo.Like;
import org.ppiyung.ppiyung.common.entity.PagingEntity;
import org.ppiyung.ppiyung.board.vo.Reply;
import org.ppiyung.ppiyung.common.entity.BasicResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
@RequestMapping(value = "/board")
@CrossOrigin(origins = "${auth.allowOrigin}", allowCredentials = "true")
public class BoardController {

	private Logger log = LogManager.getLogger("base");

	@Autowired
	private BoardService service;

	// 커뮤니티 전체 게시글 가져오기
	@GetMapping("/article")
	public ResponseEntity<BasicResponseEntity<Object>> getCommunityList(@RequestParam("page") int page, @RequestParam("size") int size, Authentication authentication) {
		PagingEntity criteria = new PagingEntity();
		criteria.setpageNum(page);
		criteria.setAmount(size);
		
		List<BoardList> result = service.getListPaging(criteria);
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;

		if (result != null) {
			log.debug("전체 게시글 조회 성공");
			respBody = new BasicResponseEntity<Object>(true, "전체 커뮤니티 게시글 완료",result);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("전체 게시글 조회 실폐");
			respBody = new BasicResponseEntity<Object>(false, "전체 커뮤니티 게시글 실패", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}

	// 커뮤니티 게시글 삽입
	@PostMapping("/article")
	public ResponseEntity<BasicResponseEntity<Object>> writeCommunityPosts(@RequestBody Board boardContent) {

		log.debug(boardContent);
		Date date = java.sql.Timestamp.valueOf(LocalDateTime.now()); // 현재 시스템의 현재 시간 가져오기
		boardContent.setArticleCreatedAt(date);
		
		log.debug(date);
		boolean result = service.writeCommunit(boardContent);
		int respCode = 0;

		BasicResponseEntity<Object> respBody = null;

		if (result == true) {
			log.debug("커뮤니티 게시글 작성");
			respBody = new BasicResponseEntity<Object>(true, "게시글 게시 완료", result);
			respCode = HttpServletResponse.SC_OK;


		} else {
			log.debug("커뮤니티 게시글 작성 실패");
			respBody = new BasicResponseEntity<Object>(false, "게시글 공고 실퍠", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;

		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}
	
	// 커뮤니티 게시글 삭제
	@DeleteMapping(value="/article/{article_id}")
	public ResponseEntity<BasicResponseEntity<Object>> deleteCommunityPost(@PathVariable("article_id") int article_id) {
		log.debug(article_id);
		boolean result = service.deleteCommunit(article_id);

		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		if (result == true) {
			log.debug("커뮤니티 게시글 삭제 완료");
			respBody = new BasicResponseEntity<Object>(true, "게시글 삭제 완료", result);
			respCode = HttpServletResponse.SC_OK;

		} else {
			log.debug("커뮤니티 게시글 작성 실패");
			respBody = new BasicResponseEntity<Object>(false, "게시글 삭제 실퍠", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}

	// 댓글 생성
	@PostMapping(value = "/reply/{reply_id}")
	public ResponseEntity<BasicResponseEntity<Object>> insertReplyHandler(@RequestBody Reply replyContent) {
		log.debug(replyContent);
		boolean result = service.insertReply(replyContent);

		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;

		if (result == true) {
			log.debug("댓글 작성 성공");
			respBody = new BasicResponseEntity<Object>(true, "댓글이 작성되었습니다.", result);
			respCode = HttpServletResponse.SC_OK;

		} else {
			log.debug("댓글 작성 실패");
			respBody = new BasicResponseEntity<Object>(false, "댓글 작성이 실패했습니다.", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}

	// 댓글 삭제
	@DeleteMapping(value = "/reply/{reply_id}")
	public ResponseEntity<BasicResponseEntity<Object>> deleteReplyHandler(@PathVariable("reply_id") int reply_id) {

		log.debug(reply_id);
		boolean result = service.deleteReply(reply_id);

		BasicResponseEntity<Object> respBody = null;

		int respCode = 0;

		if (result == true) {
			log.debug("댓글 삭제 성공");
			respBody = new BasicResponseEntity<Object>(true, "댓글이 삭제되었습니다.", result);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("댓글 삭제 실패");
			respBody = new BasicResponseEntity<Object>(false, "댓글이 삭제가 실패했습니다.", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);

	}

	// 댓글 수정
	@PutMapping(value="/reply/{reply_id}")
	public ResponseEntity<BasicResponseEntity<Object>>
		updateReply(@RequestBody Reply replyContent,@PathVariable("reply_id") int reply_id){
		
		replyContent.setReplyId(reply_id);
		
		boolean result = service.updateReply(replyContent);

		BasicResponseEntity<Object> respBody = null;

		int respCode = 0;

		if (result == true) {
			log.debug("댓글 수정 성공");
			respBody = new BasicResponseEntity<Object>(true, "댓글이 수정되었습니다.", result);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("댓글 수정 실패");
			respBody = new BasicResponseEntity<Object>(false, "댓글 수정이 실패했습니다.", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}

	// 커뮤니티 게시글 수정
	@PutMapping(value = "/article/{article_id}")
	public ResponseEntity<BasicResponseEntity<Object>> editCommunityPosts(@RequestBody Board boardContent,
			@PathVariable("article_id") int article_id) {
	
		Date date = java.sql.Timestamp.valueOf(LocalDateTime.now()); // 현재 시스템의 현재 시간 가져오기
		log.debug(date);
		boardContent.setArticleId(article_id);
		boardContent.setArticleCreatedAt(date);

		boolean result = service.editCommunit(boardContent);

		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;

		if (result == true) {
			log.debug("커뮤니티 게시글 수정 완료");
			respBody = new BasicResponseEntity<Object>(true, "게시글 수정 완료", result);
			respCode = HttpServletResponse.SC_OK;

		} else {
			log.debug("커뮤니티 게시글 수정 실패");
			respBody = new BasicResponseEntity<Object>(false, "게시글 수정 실퍠", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}
	
	// 좋아요 삭제 기능
	@DeleteMapping(value = "/like/{articleId}/{memberId}")
	public ResponseEntity<BasicResponseEntity<Object>> deleteCommunityLike(
	@PathVariable(value ="articleId") int articleId, @PathVariable(value="memberId") String memberId){
		
		Like like = new Like();
		like.setArticleId(articleId);
		like.setMemberId(memberId);
		log.debug(like);
		
		boolean result = service.deleteCoummunityLike(like);
		log.debug(result);
		
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		if (result == true) {
			log.debug("좋아요 삭제 완료");
			respBody = new BasicResponseEntity<Object>(true, "삭제 완료", result);
			respCode = HttpServletResponse.SC_OK;

		} else {
			log.debug("좋아요 삭제 실패");
			respBody = new BasicResponseEntity<Object>(false, "삭제 실퍠", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
		
	}
	
	// 게시글 개별 상세조회
	@GetMapping("/article/{articleId}")
	public ResponseEntity<BasicResponseEntity<Object>> detailsCommunity(@PathVariable("articleId") int articleId ,@RequestParam("page") int page, @RequestParam("size") int size){
		
		List<BoardList> result = service.getdetailPost(articleId);
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;

		if (result != null) {
			log.debug("전체 게시글 조회 성공");
			respBody = new BasicResponseEntity<Object>(true, "전체 커뮤니티 게시글 완료",result);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("전체 게시글 조회 실폐");
			respBody = new BasicResponseEntity<Object>(false, "전체 커뮤니티 게시글 실패", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
		
	}
	
}
