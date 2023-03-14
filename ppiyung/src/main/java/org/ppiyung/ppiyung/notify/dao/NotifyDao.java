package org.ppiyung.ppiyung.notify.dao;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.notify.vo.Notification;


public interface NotifyDao {
	// 회원별 알림 리스트 출력
	public List<HashMap<String, Object>> getNotificationList(String memberId);
	// 알림 세부내역
	public List<HashMap<String, Object>> getDetailNotify(Notification notification);
	
}
