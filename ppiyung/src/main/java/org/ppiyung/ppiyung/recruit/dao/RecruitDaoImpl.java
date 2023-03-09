package org.ppiyung.ppiyung.recruit.dao;

import org.apache.ibatis.session.SqlSession;
import org.ppiyung.ppiyung.recruit.vo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecruitDaoImpl implements RecruitDao {

	@Autowired
	SqlSession session;
	
	@Override
	public void insertRecruitNotice(Recruit param) throws Exception {
		int count = session.insert("org.ppiyung.ppiyung.recruit.insert", param);
		if (count != 1) {
			throw new Exception();
		}
	}
	
}
