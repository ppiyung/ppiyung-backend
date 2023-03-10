package org.ppiyung.ppiyung.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.board.vo.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl implements BoardDao {
	private Logger log = LogManager.getLogger("base");
	@Autowired
	SqlSession session;

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
