package org.ppiyung.ppiyung.recruit.dao;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.recruit.vo.Apply;
import org.ppiyung.ppiyung.recruit.vo.BookMark;
import org.ppiyung.ppiyung.recruit.vo.Recruit;

public interface RecruitDao {
	
	public void insertRecruitNotice(Recruit recruit) throws Exception;
	public void updateRecruitNotice(Recruit recruit) throws Exception;
	public void updateRecruitEndDate(int recruitId) throws Exception;
	public List<Recruit> selectAll();
	public List<Recruit> selectByWorkAreaId(int work_area_id);
	public List<Recruit> selectByKeyword(String keyword);
	public HashMap<String, Object> selectByCompany(String companyId);
	public List<Recruit> selectAllByCompany(String companyId);
	public void insertApply(Apply apply) throws Exception;
	public List<Recruit> selectAllDetailRecruit(String recruitId);
	public void insertBookmark(BookMark bookMark) throws Exception;
	public void deleteBookmark(BookMark bookMark) throws Exception;
}
