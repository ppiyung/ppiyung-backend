package org.ppiyung.ppiyung.recruit.service;

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
		boolean result = false;
		try {
			result = dao.insertRecruitNotice(recruit);
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
		return result;
	}
 
}
