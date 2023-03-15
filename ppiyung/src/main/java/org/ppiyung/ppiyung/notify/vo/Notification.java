package org.ppiyung.ppiyung.notify.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notification {
	
	private int notificationId; // 알림 ID
	private String memberId; // 회원 ID
	private String suggestId; // 지원이력 ID 
	private String notificationCreatedAt; // 등록일시
}
