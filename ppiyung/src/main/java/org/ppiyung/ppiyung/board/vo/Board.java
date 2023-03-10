package org.ppiyung.ppiyung.board.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

	private int article_id;
	private String article_title; // 게시글 제목
	private String article_content; // 게시글 내용
	private String member_id;
	private Date article_created_at;
	
}
