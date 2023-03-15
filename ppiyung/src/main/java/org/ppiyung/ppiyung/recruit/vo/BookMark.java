package org.ppiyung.ppiyung.recruit.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookMark {

	private int recruitId;
	private String memberId;
	private Date bookmarkCreateAt;
}
