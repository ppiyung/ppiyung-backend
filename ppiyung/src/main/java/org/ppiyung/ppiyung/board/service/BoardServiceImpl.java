package org.ppiyung.ppiyung.board.service;

import java.util.List;

import org.ppiyung.ppiyung.board.dao.BoardDao;
import org.ppiyung.ppiyung.board.vo.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;
	
	@Override
	public List<Board> getCurrentlyBoard() {
		
		return dao.getCurrentBoard();
		
	}

}
