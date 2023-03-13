package org.ppiyung.ppiyung.recruit.service;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.recruit.dao.RecruitDaoImpl;
import org.ppiyung.ppiyung.recruit.vo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruitServiceImpl implements RecruitService{

	@Autowired
	private RecruitDaoImpl dao;
	
	@Override
	public boolean insertRecruitNotice(Recruit recruit) {
		try {
			dao.insertRecruitNotice(recruit);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateRecruitNotice(Recruit recruit) {
		try {
			dao.updateRecruitNotice(recruit);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
		
	@Override
	public boolean closeRecruitNotice(int recruitId) {
		try {
			dao.updateRecruitEndDate(recruitId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Recruit> getRecruitList() {
		List<Recruit> list = dao.selectAll();
		return list;
	}
	
	@Override
	public  List<Recruit> getRecruitListByWorkAreaId(int work_area_id) {
		List<Recruit> list = dao.selectByWorkAreaId(work_area_id);
		return list;
	}
	
    @Override
    public List<Recruit> getRecruitListByKeyword(String keyword) {
    	List<Recruit> list = dao.selectByKeyword(keyword);
		return list;
    }
    
    @Override
	public HashMap<String, Object> getRecruitStatusOfCompany(String companyId) {
	   HashMap<String, Object> map = dao.selectByCompany(companyId);
	return map;
}
    @Override
    public List<Recruit> getRecruitListOfCompany(String companyId) {
    	List<Recruit> list = dao.selectAllByCompany(companyId);
		return list;
    }
}
