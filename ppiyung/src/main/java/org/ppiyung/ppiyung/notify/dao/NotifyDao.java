package org.ppiyung.ppiyung.notify.dao;

<<<<<<< HEAD
import org.ppiyung.ppiyung.notify.vo.Notify;

public interface NotifyDao {
	

	// 지원 알림 생성
	public void insertApplyNotify(Notify notify) throws Exception;
	
	// 입사제안 알림 생성
	public void insertSuggestNotify(Notify notify) throws Exception;
	
	// 알림 삭제
	public void deleteNotify(int notification_id) throws Exception;


	
	

=======
import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.notify.vo.Notification;


public interface NotifyDao {
	// 회원별 알림 리스트 출력
	public List<HashMap<String, Object>> getNotificationList(String memberId);
	// 알림 세부내역
	public List<HashMap<String, Object>> getDetailNotify(Notification notification);
	
>>>>>>> becceea8e79af99341788791d8db1a6eb1624ff4
}
