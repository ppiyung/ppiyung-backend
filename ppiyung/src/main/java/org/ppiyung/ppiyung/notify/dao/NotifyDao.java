package org.ppiyung.ppiyung.notify.dao;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.notify.vo.Notification;
import org.ppiyung.ppiyung.notify.vo.NotificationExtended;

public interface NotifyDao {
	

	// 지원 알림 생성
	public void insertApplyNotify(Notification notify) throws Exception;
	
	// 입사제안 알림 생성
	public void insertSuggestNotify(Notification notify) throws Exception;
	
	// 회원별 알림 리스트 출력
	public List<NotificationExtended> getNotificationList(String memberId);
	
	// 알림 세부내역
	public List<HashMap<String, Object>> getDetailNotify(Notification notification);
	
	// 알림 삭제
	public void deleteNotify(int notification_id) throws Exception;

}
