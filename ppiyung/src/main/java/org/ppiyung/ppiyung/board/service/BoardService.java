package org.ppiyung.ppiyung.board.service;

import java.util.List;

import org.ppiyung.ppiyung.board.vo.Board;
import org.ppiyung.ppiyung.board.vo.BoardList;
import org.ppiyung.ppiyung.board.vo.Like;
import org.ppiyung.ppiyung.board.vo.Reply;
import org.ppiyung.ppiyung.common.entity.PagingEntity;

public interface BoardService {

	// 페이징 적용하는 게시물 출력
	public List<BoardList> getListPaging(PagingEntity criteria);
	
	// 게시판 상세조회 페이지
	public List<BoardList> getdetailPost(int articleId);
	
	// 게시글 작성 service
	public boolean writeCommunit(Board boardContent);

	// 게시글 삭제 service
	public boolean deleteCommunit(int article_id);
	
	// 게시글 수정 service
	public boolean editCommunit(Board boardContent);
	
	// 게시글 댓글 생성 service
	public boolean insertReply(Reply reply);

	// 게시글 댓글 삭제 service
	public boolean deleteReply(int reply_id);

	// 게시글 댓글 수정 service
	public boolean updateReply(Reply reply);

	// 게시글 좋아요 service
	public boolean insetLike(Like like);
	
	// 게시글 좋아요 삭제 service
	boolean deleteCoummunityLike(Like like);
	
	// 게시글 개수 세기
	public int getArticlesCount();

	//회원ID별 게시글 조회 
	public List<BoardList> getCommunityByMember(String memberId);
	
}
