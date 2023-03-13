package org.ppiyung.ppiyung.board.service;

import java.util.List;

import org.ppiyung.ppiyung.board.vo.Board;
import org.ppiyung.ppiyung.board.vo.BoardList;
import org.ppiyung.ppiyung.board.vo.Like;
import org.ppiyung.ppiyung.board.vo.Reply;

public interface BoardService {

	// 게시글 전체 리스트 불러오기 service
	public List<BoardList> getCurrentlyBoard();

	// 게시글 작성 service
	public boolean writeCommunit(Board boardContent);

	// 게시글 삭제 service
	public boolean deleteCommunit(int article_id);
	
	// 게시글 댓글 생성 service
	public boolean insertReply(Reply reply);

	// 게시글 댓글 삭제 service
	public boolean deleteReply(int reply_id);

	// 게시글 댓글 수정 service
	public boolean updateReply(Reply reply);

	// 게시글 좋아요 service
	public boolean insetLike(Like like);

}
