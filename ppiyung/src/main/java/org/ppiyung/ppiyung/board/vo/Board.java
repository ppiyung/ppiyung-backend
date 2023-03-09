package org.ppiyung.ppiyung.board.vo;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private int article_id; // 게시글 아읻
	private String article_like; // 게시글 제목
	private String article_content; // 게시글 내용
	private String member_id; // 회원 아이디
	private Date article_created_at; // 게시글 작성일시
	private String comment_cnt; // 댓글 수
	private int like_cnt;// 좋아요 개수
	
}
