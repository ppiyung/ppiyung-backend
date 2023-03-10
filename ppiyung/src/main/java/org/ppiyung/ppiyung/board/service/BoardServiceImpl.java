package org.ppiyung.ppiyung.board.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.board.dao.BoardDao;
import org.ppiyung.ppiyung.board.vo.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{

	private Logger log = LogManager.getLogger("base");
	@Autowired
	private BoardDao dao;
	
	// 댓글 생성
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
	
	// 댓글 삭제
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
	
	// 댓글 수정
	@Override
	public boolean updateReply(Reply reply) {
		try {
			dao.updateReply(reply);
			return true;
		} catch (Exception e) {
			log.debug("서비스단 오류 확인");
			e.printStackTrace();
			return false;
		}
	}
	
	
}
