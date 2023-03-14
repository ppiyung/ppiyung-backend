package org.ppiyung.ppiyung.recruit.dao;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.common.entity.PagingEntity;
import org.ppiyung.ppiyung.recruit.vo.Apply;
import org.ppiyung.ppiyung.recruit.vo.Recruit;
import org.ppiyung.ppiyung.recruit.vo.Suggest;

public interface RecruitDao {
	
	public void insertRecruitNotice(Recruit recruit) throws Exception;
	public void updateRecruitNotice(Recruit recruit) throws Exception;
	public void updateRecruitEndDate(int recruitId) throws Exception;
	public List<Recruit> selectAll(PagingEntity pagingEntity);
	public List<Recruit> selectByWorkAreaId(int work_area_id);
	public List<Recruit> selectByKeyword(String keyword);
	public HashMap<String, Object> selectByCompany(String companyId);
	public List<Recruit> selectAllByCompany(String companyId);
	public void insertApply(Apply apply) throws Exception;
	public void insertSuggest(Suggest suggest)throws Exception;
	public List<Recruit> selectAllDetailRecruit(String recruitId);
	public List<Apply> selectByMember(String memberId);
	public List<HashMap<String, Object>> selectByRecruit(int recruitId);
}
