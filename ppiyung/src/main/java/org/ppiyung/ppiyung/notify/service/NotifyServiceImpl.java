package org.ppiyung.ppiyung.notify.service;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.notify.dao.NotifyDao;
import org.ppiyung.ppiyung.notify.vo.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyServiceImpl implements NotifyService {
	
	@Autowired
	private NotifyDao dao;
	
	// 회원별 알람 리스트 조회
	@Override
	public List<HashMap<String, Object>>  getNotificationList(String memberId) {
		 List<HashMap<String, Object>> list = dao.getNotificationList(memberId);
		return list;
	}
	
	// 알림 세부내역 출력
	@Override
	public List<HashMap<String, Object>> detailNotification(Notification notification) {
		List<HashMap<String, Object>> notify = dao.getDetailNotify(notification);
		return notify;
	}

}
