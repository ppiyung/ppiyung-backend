package org.ppiyung.ppiyung.board.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
=======
import lombok.ToString;
>>>>>>> becceea8e79af99341788791d8db1a6eb1624ff4

@Data
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< HEAD
public class Like {
	
	   private int articleId;
	   private String memberId;
	   private Date likeCreatedAt;
	   
=======
@ToString
public class Like {
	
	private int articleId;
	private String memberId;
	private Date likeCreatedAt;
>>>>>>> becceea8e79af99341788791d8db1a6eb1624ff4
}
