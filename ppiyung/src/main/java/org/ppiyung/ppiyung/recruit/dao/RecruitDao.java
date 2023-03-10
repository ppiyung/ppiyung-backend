package org.ppiyung.ppiyung.recruit.dao;

import org.ppiyung.ppiyung.recruit.vo.Recruit;

public interface RecruitDao {
	
	public void insertRecruitNotice(Recruit recruit) throws Exception;
	public void updateRecruitNotice(Recruit recruit, int recuit_id) throws Exception;
	
	

}
