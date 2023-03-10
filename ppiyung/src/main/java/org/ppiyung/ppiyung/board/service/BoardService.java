package org.ppiyung.ppiyung.board.service;

import org.ppiyung.ppiyung.board.vo.Reply;

public interface BoardService {
	
	// 댓글 생성
	public boolean insertReply(Reply reply);

	// 댓글 삭제
	public boolean deleteReply(Reply reply);

	// 댓글 수정
	public boolean updateReply(Reply reply);

}
