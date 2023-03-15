package org.ppiyung.ppiyung.notify.service;

<<<<<<< HEAD
import org.ppiyung.ppiyung.notify.vo.Notify;

public interface NotifyService {


	// 지원 알림 생성
	boolean insertApplyNotify(Notify notify);
	
	// 입사제안 알림 생성
	boolean insertSuggestNotify(Notify notify);
	
	// 알림 삭제
	boolean deleteNotify(int notification_id);


=======
import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.notify.vo.Notification;

public interface NotifyService {
	
	// 회원별 알림리스트 조회
	public List<HashMap<String, Object>> getNotificationList(String memberId);
	
	// 알림 세부내역 출력
	public List<HashMap<String, Object>> detailNotification(Notification notification);
>>>>>>> becceea8e79af99341788791d8db1a6eb1624ff4
}
