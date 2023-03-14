package org.ppiyung.ppiyung.board.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Like {
	
	private int articleId;
	private String memberId;
	private Date likeCreatedAt;
}
