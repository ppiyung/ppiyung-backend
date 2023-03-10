package org.ppiyung.ppiyung.board.dao;

import org.ppiyung.ppiyung.board.vo.Reply;

public interface BoardDao  {
	
	// 댓글 생성
	public void insertReply(Reply reply) throws Exception;

	// 댓글 삭제
	public void deleteReply(Reply reply) throws Exception;

	// 댓글 수정
	public void updateReply(Reply reply) throws Exception;
	
	
	

}
