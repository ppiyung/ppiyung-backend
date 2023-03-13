package org.ppiyung.ppiyung.board.vo;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardList {
	private int articleId; // 게시글 아이디
	private String memberNickname; //회원 닉네임
	private String articleTitle; // 게시글 제목
	private String articleContent; // 게시글 내용
	private String memberId; // 회원 아이디
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date articleCreatedAt; // 게시글 작성일시
	private String commentCnt; // 댓글 수
	private int likeCnt;// 좋아요 개수
	
}
