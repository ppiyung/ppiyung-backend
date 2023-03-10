package org.ppiyung.ppiyung.board.dao;

import java.util.List;

import org.ppiyung.ppiyung.board.vo.Board;
import org.ppiyung.ppiyung.board.vo.BoardList;

public interface BoardDao {

	List<BoardList> getCurrentBoard();

	void insertBoardpost(Board boardContent) throws Exception;

	void deleteBoardPost(int article_id) throws Exception;

}
