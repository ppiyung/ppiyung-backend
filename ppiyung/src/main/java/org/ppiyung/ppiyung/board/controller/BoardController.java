package org.ppiyung.ppiyung.board.controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.board.service.BoardService;
import org.ppiyung.ppiyung.board.vo.Board;
import org.ppiyung.ppiyung.board.vo.BoardList;
import org.ppiyung.ppiyung.board.vo.Reply;
import org.ppiyung.ppiyung.common.entity.BasicResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/board/reply")
public class BoardController {
	
	private Logger log = LogManager.getLogger("base");

	@Autowired
	private BoardService service;

	// 댓글 생성
	@PostMapping(value = "")
	public ResponseEntity<BasicResponseEntity<Object>>
			insertReplyHandler(@RequestBody Reply replyContent){

			boolean result = service.insertReply(replyContent);
		
			BasicResponseEntity<Object> respBody = null;
			int respCode = 0;
			
			if(result == true) {
				log.debug("댓글 작성 성공");
				respBody = new BasicResponseEntity<Object>(true, "댓글이 작성되었습니다.", result);
				respCode = HttpServletResponse.SC_OK;
				
			}else {
				log.debug("댓글 작성 실패");
				respBody = new BasicResponseEntity<Object>(false, "댓글 작성이 실패했습니다.", result);
				respCode = HttpServletResponse.SC_BAD_REQUEST;
			}
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", 
									Charset.forName("UTF-8")));
			
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}
	
	// 댓글 삭제
	@DeleteMapping(value="/{reply_id}")
	public ResponseEntity<BasicResponseEntity<Object>>
		deleteReplyHandler(@PathVariable("reply_id") int reply_id){
		
		log.debug(reply_id);
		boolean result = service.deleteReply(reply_id);
		
		BasicResponseEntity<Object> respBody = null;
		
		int respCode = 0;
		
		if (result == true) {
			log.debug("댓글 삭제 성공");
			respBody = new BasicResponseEntity<Object>(true, "댓글이 삭제되었습니다.", result);
			respCode = HttpServletResponse.SC_OK;
		}else {
			log.debug("댓글 삭제 실패");
			respBody = new BasicResponseEntity<Object>(false, "댓글이 삭제가 실패했습니다.", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers,respCode);
		
		
	}
	
	// 댓글 수정
	@PutMapping(value="/{reply_id}")
	public ResponseEntity<BasicResponseEntity<Object>>
		updateReply(@RequestBody Reply replyContent,@PathVariable("reply_id") int reply_id){
		
		replyContent.setReply_id(reply_id);
		
		boolean result = service.updateReply(replyContent);
		
		BasicResponseEntity<Object> respBody = null;
		
		int respCode = 0;
		
		if (result == true) {
			log.debug("댓글 수정 성공");
			respBody = new BasicResponseEntity<Object>(true, "댓글이 수정되었습니다.", result);
			respCode = HttpServletResponse.SC_OK;			
		}else {
			log.debug("댓글 수정 실패");
			respBody = new BasicResponseEntity<Object>(false, "댓글 수정이 실패했습니다.", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}

		
		
	// 커뮤니티 전체 게시글 가져오기
	@GetMapping("")
	public ResponseEntity<BasicResponseEntity<Object>> getCommunityList() {
		
		List<BoardList> result = service.getCurrentlyBoard();
		BasicResponseEntity<Object> respBody = null;
		int respCode = 0;
		
		if(result != null) {
			log.debug("전체 게시글 조회 성공");
			respBody = new BasicResponseEntity<Object>(true,"전체 커뮤니티 게시글 완료",result);
			respCode = HttpServletResponse.SC_OK;
		} else {
			log.debug("전체 게시글 조회 실폐");
			respBody = new BasicResponseEntity<Object>(false,"전체 커뮤니티 게시글 실패",result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				Charset.forName("UTF-8")));

		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}


	// 커뮤니티 게시글 삽입
	@PostMapping("")
	public ResponseEntity<BasicResponseEntity<Object>> writeCommunityPosts(@RequestBody Board boardContent) {

		log.debug(boardContent);
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
	@DeleteMapping(value="/{article_id}")
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
	
	
}
