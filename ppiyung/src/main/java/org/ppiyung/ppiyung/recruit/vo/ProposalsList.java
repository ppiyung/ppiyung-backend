package org.ppiyung.ppiyung.recruit.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProposalsList {
	private int suggestId;
	private Date suggestCreatedAt;
	private String companyId;
	private String memberId;
	private String companyName;
	private String memberName;
	private String memberPhone;
	private String memberAddr;
	private String workAreaName;
}
