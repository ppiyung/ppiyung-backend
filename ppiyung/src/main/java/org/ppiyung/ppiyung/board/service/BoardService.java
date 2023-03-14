package org.ppiyung.ppiyung.board.service;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.board.vo.Board;
import org.ppiyung.ppiyung.board.vo.BoardList;
import org.ppiyung.ppiyung.board.vo.Like;
import org.ppiyung.ppiyung.common.entity.PagingEntity;
import org.ppiyung.ppiyung.board.vo.Reply;

public interface BoardService {

	// 페이징 적용하는 게시물 출력
	public List<BoardList> getListPaging(PagingEntity criteria);
	
	// 게시글 작성 service
	public boolean writeCommunit(Board boardContent);

	// 게시글 삭제 service
	public boolean deleteCommunit(int article_id);
	
	// 댓글 생성
	public boolean insertReply(Reply reply);

	// 댓글 삭제
	public boolean deleteReply(int reply_id);

	// 댓글 수정
	public boolean updateReply(Reply reply);

	// 게시글 수정
	public boolean editCommunit(Board boardContent);
	// 커뮤니티 좋아요 삭제
	boolean deleteCoummunityLike(Like like);

	// 게시판 상세조회 페이지
	public List<Board> getdetailPost(int articleId);
	
}
