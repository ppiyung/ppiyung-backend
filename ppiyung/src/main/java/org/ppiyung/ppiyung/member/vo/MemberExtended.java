package org.ppiyung.ppiyung.member.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberExtended {
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
	private Image profileImage;
	private Resume memberResume;
	private Date lastSuggested;
}
