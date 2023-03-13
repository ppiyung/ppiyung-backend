package org.ppiyung.ppiyung.member.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private String memberId;
	private String memberPw;
	private String memberName;
	private Date memberBirth;
	private char memberGender;
	private String memberPhone;
	private String memberAddr;
	private double memberCoordX;
	private double memberCoordY;
	private String memberNickname;
	private String memberEmail;
	private char memberType;
	private String memberRegNum;
	private String memberInfo;
	private boolean memberActive;
	private Date memberCreatedAt;
	private int workAreaId;
	private boolean memberVerified;
	
	public String getRole() {
		if (memberType == 'N') {
			return "ROLE_NORMAL";
		} else if (memberType == 'C') {
			return "ROLE_COMPANY";
		} else if (memberType == 'A') {
			return "ROLE_ADMIN";
		} else {
			return null;
		}
	}
}
