package org.ppiyung.ppiyung.board.service;

import org.ppiyung.ppiyung.board.dao.BoardDao;
import org.ppiyung.ppiyung.board.vo.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{

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
	public boolean deleteReply(Reply reply) {
		try {
			dao.deleteReply(reply);
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
			e.printStackTrace();
			return false;
		}
	}
	
	
}
