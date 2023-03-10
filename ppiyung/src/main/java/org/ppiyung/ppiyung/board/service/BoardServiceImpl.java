package org.ppiyung.ppiyung.board.service;

import java.util.List;

import org.ppiyung.ppiyung.board.dao.BoardDao;
import org.ppiyung.ppiyung.board.vo.Board;
import org.ppiyung.ppiyung.board.vo.BoardList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;

	// 커뮤니티 게시글 전체 목록 출력 서비스
	@Override
	public List<BoardList> getCurrentlyBoard() {

		return dao.getCurrentBoard();

	}

	// 커뮤니티 게시글 작성
	@Override
	public boolean writeCommunit(Board boardContent) {

		try {
			dao.insertBoardpost(boardContent);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
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

}
