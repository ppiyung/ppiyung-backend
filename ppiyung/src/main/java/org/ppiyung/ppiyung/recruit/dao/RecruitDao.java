package org.ppiyung.ppiyung.recruit.dao;

import java.util.List;

import org.ppiyung.ppiyung.recruit.vo.Recruit;

public interface RecruitDao {
	
	public void insertRecruitNotice(Recruit recruit) throws Exception;
	public void updateRecruitNotice(Recruit recruit) throws Exception;
	public void updateRecruitEndDate(int recruitId) throws Exception;
	public List<Recruit> selectAll();
	public List<Recruit> selectByWorkAreaId(int work_area_id);
	public List<Recruit> selectByKeyword(String keyword);

}
