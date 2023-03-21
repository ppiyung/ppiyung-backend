package org.ppiyung.ppiyung.recruit.vo;

import java.util.Date;

import org.ppiyung.ppiyung.member.vo.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyExtended {

	private int applyId;
	private boolean applyResult;
	private String memberId;
	private String companyId;
	private int recruitId;
	private Date applyCreatedAt;
	private Recruit applyRecruit;
	private Member applyMember;
	
}
