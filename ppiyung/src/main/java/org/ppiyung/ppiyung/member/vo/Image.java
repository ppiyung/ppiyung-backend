package org.ppiyung.ppiyung.member.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
	private String memberId;
	private String imgLocation;
	private String imgFilename;
	private String imgFiletype;
	private Date imgUpdatedAt;
}
