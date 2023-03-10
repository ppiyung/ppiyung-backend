package org.ppiyung.ppiyung.board.controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.board.service.BoardService;
import org.ppiyung.ppiyung.board.vo.Board;
import org.ppiyung.ppiyung.board.vo.BoardList;
import org.ppiyung.ppiyung.common.entity.BasicResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/board/article")
public class BoardController {

	Logger log = LogManager.getLogger("case3");

	@Autowired
	private BoardService bservice;

	// 커뮤니티 전체 게시글 가져오기
	@GetMapping("")
	public ResponseEntity<BasicResponseEntity<List<BoardList>>> getCurrentHandler() {

		BasicResponseEntity<List<BoardList>> respBody = new BasicResponseEntity<List<BoardList>>(true, "테스트용 컨트롤러입니다.",
				bservice.getCurrentlyBoard());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		log.debug(bservice.getCurrentlyBoard());

		return new ResponseEntity<BasicResponseEntity<List<BoardList>>>(respBody, headers, HttpServletResponse.SC_OK);
	}

	// 커뮤니티 게시글 삽입
	@PostMapping("")
	public ResponseEntity<BasicResponseEntity<Object>> writeCommunityPosts(@RequestBody Board boardContent) {

		log.debug(boardContent);
		boolean result = bservice.writeCommunit(boardContent);
		int respCode = 0;

		BasicResponseEntity<Object> respBody = null;

		if (result == true) {
			log.debug("커뮤니티 게시글 작성");
			respBody = new BasicResponseEntity<Object>(true, "게시글 게시 완료", result);
			respCode = HttpServletResponse.SC_OK;

		} else {
			log.debug("커뮤니티 게시글 작성 실패");
			respBody = new BasicResponseEntity<Object>(true, "게시글 공고 실퍠", result);
			respCode = HttpServletResponse.SC_BAD_REQUEST;

		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return new ResponseEntity<BasicResponseEntity<Object>>(respBody, headers, respCode);
	}


}
