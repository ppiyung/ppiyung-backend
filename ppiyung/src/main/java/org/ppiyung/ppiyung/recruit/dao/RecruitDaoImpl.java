package org.ppiyung.ppiyung.recruit.dao;

import java.util.List;

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

	@Override
	public void updateRecruitNotice(Recruit param) throws Exception {
		
		int count = session.update("org.ppiyung.ppiyung.recruit.update", param);
		if (count != 1) {
			throw new Exception();
		}
		
	}
    @Override
	public void deleteRecruitNotice(int recruit_id) throws Exception{
		int count = session.update("org.ppiyung.ppiyung.recruit.delete", recruit_id);
		if (count != 1) {
			throw new Exception();
		}
		
	}

    @Override
	public List<Recruit> selectByWorkAreaId(int workAreaId) {
		List<Recruit> list = session.selectList("org.ppiyung.ppiyung.recruit.selectByWorkAreaId", workAreaId);
		
		return list;
	}

	@Override
	public List<Recruit> selectAll() {
        List<Recruit> list = session.selectList("org.ppiyung.ppiyung.recruit.selectAll");
		
		return list;
	}

	@Override
	public List<Recruit> selectByKeyword(String keyword) {
		List<Recruit> list = session.selectList("org.ppiyung.ppiyung.recruit.selectByKeyword", keyword);
			
		return list;
	}
	
}
