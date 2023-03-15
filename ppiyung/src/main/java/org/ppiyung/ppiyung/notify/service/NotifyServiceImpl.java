package org.ppiyung.ppiyung.notify.service;

import org.ppiyung.ppiyung.notify.dao.NotifyDao;
import org.ppiyung.ppiyung.notify.vo.Notify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyServiceImpl implements NotifyService {
	
	@Autowired
	private NotifyDao dao;
	
	
	// 지원 알림 생성
	@Override
	public boolean insertApplyNotify(Notify notify) {
		try {
			dao.insertApplyNotify(notify);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	// 입사제안 알림 생성
	@Override
	public boolean insertSuggestNotify(Notify notify) {
		try {
			dao.insertSuggestNotify(notify);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	

	// 알림 삭제
	@Override
	public boolean deleteNotify(int notification_id) {
		try {
			dao.deleteNotify(notification_id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	
	
	

}
