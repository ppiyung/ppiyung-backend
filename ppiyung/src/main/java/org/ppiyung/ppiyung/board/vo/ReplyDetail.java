package org.ppiyung.ppiyung.board.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDetail {
	
	   private int replyId;
	   private String replyContent;
	   private int articleId;
	   private String memberId;
	   private String memberNickname;
	   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	   private Date replyCreatedAt;
	   


}
