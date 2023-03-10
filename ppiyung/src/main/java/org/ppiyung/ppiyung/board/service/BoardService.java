package org.ppiyung.ppiyung.board.service;

import java.util.List;

import org.ppiyung.ppiyung.board.vo.Board;
import org.ppiyung.ppiyung.board.vo.BoardList;

public interface BoardService {

	public List<BoardList> getCurrentlyBoard();

	public boolean writeCommunit(Board boardContent);

}
