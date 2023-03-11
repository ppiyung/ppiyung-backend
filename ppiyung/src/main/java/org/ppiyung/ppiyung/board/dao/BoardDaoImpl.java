package org.ppiyung.ppiyung.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.board.vo.Board;
import org.ppiyung.ppiyung.board.vo.BoardList;

import org.ppiyung.ppiyung.board.vo.Reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl implements BoardDao {

	
	Logger log = LogManager.getLogger("base");
	
	@Autowired
	private SqlSession session;
	
	
	// 커뮤니티 전체 게시글 목록
	@Override
	public List<BoardList> getCurrentBoard() {
		// TODO Auto-generated method stub
		return session.selectList("org.ppiyung.ppiyung.board.allBoard");
	}
	
	// 커뮤니티 게시글 삽입
	@Override
	public void insertBoardpost(Board param) throws Exception {
		int count = session.insert("org.ppiyung.ppiyung.board.articleInsert",param);
		
		if(count != 1) {
			throw new Exception();
		}
		
	}

	// 커뮤니티 게시글 삭제
	@Override
	public void deleteBoardPost(int param) throws Exception {
		int count = session.delete("org.ppiyung.ppiyung.board.articleDelete", param);
		
		if(count != 1) {
			throw new Exception();
		}
	}
	// 댓글 생성
	@Override
	public void insertReply(Reply param) throws Exception {
		int count = session.insert("org.ppiyung.ppiyung.board.insert", param);
		if (count != 1) {
			throw new Exception();
		}
	}

	// 댓글 삭제
	@Override
	public void deleteReply(int reply_id) throws Exception {
		int count = session.delete("org.ppiyung.ppiyung.board.delete", reply_id);
		if (count != 1) {
			throw new Exception();
		}
	}

	// 댓글 수정
	@Override
	public void updateReply(Reply reply) throws Exception {
		
		log.debug(reply);
		int count = session.update("org.ppiyung.ppiyung.board.update", reply);
		if (count != 1) {
			log.debug("Dao단 오류 확인");
			throw new Exception();
		}
	}

}
