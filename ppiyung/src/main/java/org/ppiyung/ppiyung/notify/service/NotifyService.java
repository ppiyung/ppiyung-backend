package org.ppiyung.ppiyung.notify.service;

import org.ppiyung.ppiyung.notify.vo.Notify;

public interface NotifyService {


	// 지원 알림 생성
	boolean insertApplyNotify(Notify notify);
	
	// 입사제안 알림 생성
	boolean insertSuggestNotify(Notify notify);
	
	// 알림 삭제
	boolean deleteNotify(int notification_id);


}
