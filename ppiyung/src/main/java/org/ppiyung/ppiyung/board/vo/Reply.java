package org.ppiyung.ppiyung.board.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	
	   private int reply_id;
	   private String reply_content;
	   private int article_id;
	   private String member_id;
	   private Date reply_created_at;
	   


}
