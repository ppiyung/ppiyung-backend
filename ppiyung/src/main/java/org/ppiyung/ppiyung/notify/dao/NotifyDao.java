package org.ppiyung.ppiyung.notify.dao;

import org.ppiyung.ppiyung.notify.vo.Notify;

public interface NotifyDao {
	

	// 지원 알림 생성
	public void insertApplyNotify(Notify notify) throws Exception;
	
	// 입사제안 알림 생성
	public void insertSuggestNotify(Notify notify) throws Exception;
	
	// 알림 삭제
	public void deleteNotify(int notification_id) throws Exception;


	
	

}
