package org.ppiyung.ppiyung.board.dao;


import java.util.List;

import org.ppiyung.ppiyung.board.vo.Board;
import org.ppiyung.ppiyung.board.vo.BoardList;
import org.ppiyung.ppiyung.board.vo.Reply;

public interface BoardDao {

	List<BoardList> getCurrentBoard();

	void insertBoardpost(Board boardContent) throws Exception;

	void deleteBoardPost(int article_id) throws Exception;

	
	// 댓글 생성
	public void insertReply(Reply reply) throws Exception;

	// 댓글 삭제
	public void deleteReply(int reply_id) throws Exception;

	// 댓글 수정
	public void updateReply(Reply reply) throws Exception;
	
	

}
