package org.ppiyung.ppiyung.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberTestVO {
	private String memberId;
	private String memberName;
	private String memberPw;
}
