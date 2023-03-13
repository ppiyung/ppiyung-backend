package org.ppiyung.ppiyung.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.board.vo.Board;
import org.ppiyung.ppiyung.board.vo.BoardList;
import org.ppiyung.ppiyung.board.vo.Like;
import org.ppiyung.ppiyung.board.vo.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	private Logger log = LogManager.getLogger("base");
	
	@Autowired
	SqlSession session;
	
		
	// 커뮤니티 전체 게시글 목록
	@Override
	public List<BoardList> getCurrentBoard() {
		
		return session.selectList("org.ppiyung.ppiyung.board.allBoard");
	}
	
	// 커뮤니티 게시글 삽입
	@Override
	public void insertBoardpost(Board param) throws Exception {
		int count = session.insert("org.ppiyung.ppiyung.board.insertBoard",param);
		
		if(count != 1) {
			throw new Exception();
		}
		
	}
	
	
	// 커뮤니티 게시글 삭제
	@Override
	public void deleteBoardPost(int param) throws Exception {
		int count = session.delete("org.ppiyung.ppiyung.board.deleteBoard", param);
		
		if(count != 1) {
			throw new Exception();
		}
		
	}

	// 게시글 댓글 생성
	@Override
	public void insertReply(Reply param) throws Exception {
		int count = session.insert("org.ppiyung.ppiyung.board.insertReply", param);
		if (count != 1) {
			throw new Exception();
		}
	}

	// 게시글 댓글 삭제
	@Override
	public void deleteReply(int reply_id) throws Exception {
		int count = session.delete("org.ppiyung.ppiyung.board.deleteReply", reply_id);
		if (count != 1) {
			throw new Exception();
		}
	}

	// 게시글 댓글 수정
	@Override
	public void updateReply(Reply reply) throws Exception {
		
		log.debug(reply);
		int count = session.update("org.ppiyung.ppiyung.board.updateReply", reply);
		if (count != 1) {
			log.debug("Dao단 오류 확인");
			throw new Exception();
		}
	}
	
	// 게시글 좋아요 작성
	@Override
	public void insertLike(Like like) throws Exception {
		
		log.debug(like);
		int count = session.insert("org.ppiyung.ppiyung.board.insertLike", like);
		if (count != 1) {
			log.debug("Dao단 오류 확인");
			throw new Exception();
		}
		
	}
	
	


}
