package org.ppiyung.ppiyung.notify.service;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.notify.vo.Notification;
import org.ppiyung.ppiyung.notify.vo.NotificationExtended;

public interface NotifyService {

	// 지원 알림 생성
	boolean insertApplyNotify(Notification notify);
	
	// 입사제안 알림 생성
	boolean insertSuggestNotify(Notification notify);
	
	// 회원별 알림리스트 조회
	public List<NotificationExtended> getNotificationList(String memberId);
	
	// 알림 세부내역 출력
	public List<HashMap<String, Object>> detailNotification(Notification notification);
	
	// 알림 삭제
	boolean deleteNotify(int notification_id);

}
