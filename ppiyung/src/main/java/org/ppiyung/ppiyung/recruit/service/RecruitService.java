package org.ppiyung.ppiyung.recruit.service;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.recruit.vo.Apply;
import org.ppiyung.ppiyung.recruit.vo.Recruit;

public interface RecruitService {

	public boolean insertRecruitNotice(Recruit recruit);

	public boolean updateRecruitNotice(Recruit recruit);

	public boolean closeRecruitNotice(int recruitId);
	
    public List<Recruit> getRecruitList();
	
    public List<Recruit> getRecruitListByWorkAreaId(int work_area_id);

	public List<Recruit> getRecruitListByKeyword(String keyword);

	public HashMap<String, Object> getRecruitStatusOfCompany(String companyId);

	public List<Recruit> getRecruitListOfCompany(String companyId);

	public boolean applyForJob(Apply apply);


	
	
	
}
