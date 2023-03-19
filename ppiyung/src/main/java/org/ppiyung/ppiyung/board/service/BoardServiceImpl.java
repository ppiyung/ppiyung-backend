package org.ppiyung.ppiyung.board.service;

import java.util.List;

import org.ppiyung.ppiyung.board.dao.BoardDao;
import org.ppiyung.ppiyung.board.vo.Board;
import org.ppiyung.ppiyung.board.vo.BoardList;
import org.ppiyung.ppiyung.board.vo.Like;
import org.ppiyung.ppiyung.board.vo.Reply;
import org.ppiyung.ppiyung.common.entity.PagingEntity;
import org.ppiyung.ppiyung.recruit.vo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;

	// 커뮤니티 게시글 전체 목록 출력 서비스
	@Override
	public List<BoardList> getListPaging(PagingEntity criteria) {
		return dao.pagingInsertBoard(criteria);
	}
	
	// 커뮤니티 게시글 상세조회
	@Override
	public List<BoardList> getdetailPost(int articleId){
		return dao.detailBoard(articleId);
	}

	// 커뮤니티 게시글 작성
	@Override
	public boolean writeCommunit(Board boardContent) {

		try {
			dao.insertBoardpost(boardContent);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 커뮤니티 게시글 삭제
	@Override
	public boolean deleteCommunit(int article_id) {
		try {
			dao.deleteBoardPost(article_id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// 커뮤니티 게시글 수정
	@Override
	public boolean editCommunit(Board boardContent) {

		try {
			dao.updateBoardPost(boardContent);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
		
	// 커뮤니티 게시글 댓글 생성
	@Override
	public boolean insertReply(Reply reply) {
		try {
			dao.insertReply(reply);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	// 커뮤니티 게시글 댓글 삭제
	@Override
	public boolean deleteReply(int reply_id) {
		try {
			dao.deleteReply(reply_id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 커뮤니티 게시글 댓글 수정
	@Override
	public boolean updateReply(Reply reply) {
		try {
			dao.updateReply(reply);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 커뮤니티 게시글 좋아요 작성
	@Override
	public boolean insetLike(Like like) {
		
		try {
			dao.insertLike(like);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// 커뮤니티 게시글 좋아요 삭제
	@Override
	public boolean deleteCoummunityLike(Like like) {

		try {
			dao.deleteLike(like);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int getArticlesCount() {
		return dao.totalCount();
	}

	//커뮤니티 게시글 회원ID별 게시글 조회
	@Override
	public List<BoardList> getCommunityByMember(String memberId) {
		List<BoardList> list = dao.selectListCommmunityList(memberId);
		return list;
	}

}
