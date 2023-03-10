package org.ppiyung.ppiyung.board.controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.board.service.BoardService;
import org.ppiyung.ppiyung.board.vo.Board;
import org.ppiyung.ppiyung.common.entity.BasicResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/board")
public class BoardController {

	Logger log = LogManager.getLogger("case3");
	
	@Autowired
	private BoardService bservice;

	// 전체 게시글 가져오기
	@GetMapping(value = "/artcle")
	public ResponseEntity<BasicResponseEntity<List<Board>>> getCurrentHandler(){
		
		BasicResponseEntity<List<Board>> respBody = new BasicResponseEntity<List<Board>>(true, "테스트용 컨트롤러입니다.", bservice.getCurrentlyBoard());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",Charset.forName("UTF-8")));
		
		log.debug(bservice.getCurrentlyBoard());
		
		return new ResponseEntity<BasicResponseEntity<List<Board>>>(respBody, headers, HttpServletResponse.SC_OK);
	}
	
	
	
//	
//	@GetMapping(value = "/artcle")
//	public String CurrentBoard(Model model) {
//		
//		model.addAttribute("board",bservice.getCurrentlyBoard());
//		bservice.getCurrentlyBoard()
//		log.debug(bservice.getCurrentlyBoard());
//		return model;
//		
//	}
}
