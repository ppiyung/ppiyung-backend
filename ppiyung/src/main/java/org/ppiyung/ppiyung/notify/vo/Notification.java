package org.ppiyung.ppiyung.notify.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
	
	private int notificationId;
	private String memberId;
	private int suggestId;
	private int applyId;
	private Date notificationCreatedAt;


}
