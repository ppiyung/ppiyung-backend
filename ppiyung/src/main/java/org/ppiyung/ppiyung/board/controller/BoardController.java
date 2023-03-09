package org.ppiyung.ppiyung.board.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping(value = "/artcle")
	public String CurrentBoard(Model model) {
		
		model.addAttribute("board",bservice.getCurrentlyBoard());
		
		return null;
		
	}
}
