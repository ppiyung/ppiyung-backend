package org.ppiyung.ppiyung.notify.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.notify.vo.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NotifyDaoImpl implements NotifyDao {
	
	private Logger log = LogManager.getLogger("base");
	
	@Autowired
	SqlSession session;
	
	// 회원별 알림 조회
	@Override
	public List<HashMap<String, Object>>  getNotificationList(String param) {
		
		List<HashMap<String, Object>> list = session.selectList("org.ppiyung.ppiyung.notify.selectNotifyList",param);
		return list;
	}		

	@Override
	public List<HashMap<String, Object>> getDetailNotify(Notification param) {
		List<HashMap<String,Object>> notify = session.selectList("org.ppiyung.ppiyung.notify.detailNotify", param);
		return notify;
	}

}
