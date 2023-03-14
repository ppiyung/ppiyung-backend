package org.ppiyung.ppiyung.recruit.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apply {

	private int applyId;
	private boolean applyResult;
	private String memberId;
	private int recruitId;
	private Date applyCreatedAt;
	
}
