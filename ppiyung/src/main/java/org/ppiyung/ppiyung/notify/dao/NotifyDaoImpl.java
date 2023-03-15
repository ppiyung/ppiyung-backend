package org.ppiyung.ppiyung.notify.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.notify.vo.Notify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NotifyDaoImpl implements NotifyDao {
	
	private Logger log = LogManager.getLogger("base");
	
	@Autowired
	SqlSession session;
	
	
	// 지원 알림 생성
	@Override
	public void insertApplyNotify(Notify notify) throws Exception {
		int count = session.insert("org.ppiyung.ppiyung.notify.insertApplyNotify", notify);
		if (count != 1) {
			throw new Exception();
		}
	}

	// 입사제안 알림 생성
	@Override
	public void insertSuggestNotify(Notify notify) throws Exception {
		int count = session.insert("org.ppiyung.ppiyung.notify.insertSuggestNotify", notify);
		if (count != 1) {
			throw new Exception();
		}
	}


	// 알림 삭제
	@Override
	public void deleteNotify(int notification_id) throws Exception {
		int count = session.delete("org.ppiyung.ppiyung.notify.deleteNotify", notification_id);
		if (count != 1) {
			throw new Exception();
		}
	}


	
}
