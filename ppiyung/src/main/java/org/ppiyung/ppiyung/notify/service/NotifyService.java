package org.ppiyung.ppiyung.notify.service;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.notify.vo.Notification;

public interface NotifyService {
	
	// 회원별 알림리스트 조회
	public List<HashMap<String, Object>> getNotificationList(String memberId);
	
	// 알림 세부내역 출력
	public List<HashMap<String, Object>> detailNotification(Notification notification);
}
