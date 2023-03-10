package org.ppiyung.ppiyung.board.controller;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.board.service.BoardService;
import org.ppiyung.ppiyung.board.vo.Reply;
import org.ppiyung.ppiyung.common.entity.BasicResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

		
}

	
	

