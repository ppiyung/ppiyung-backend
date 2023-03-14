package org.ppiyung.ppiyung.recruit.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitBookMark {
 
	private int recruitId;
	private String companyId;
	private String recruitTitle;
	private String recruitDetail;
	private int workAreaId;
	private Date recruitStartAt ; 
	private Date recruitEndAt;
	private String memberId;
	private Date bookmarkCreateAt;
}
