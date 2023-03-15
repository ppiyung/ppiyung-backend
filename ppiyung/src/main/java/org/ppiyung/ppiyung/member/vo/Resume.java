package org.ppiyung.ppiyung.member.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
	private String memberId;
	private String resumeLocation;
	private String resumeFilename;
	private String resumeFiletype;
	private Date resumeUpdatedAt;
	private boolean resumeOpen;
}
