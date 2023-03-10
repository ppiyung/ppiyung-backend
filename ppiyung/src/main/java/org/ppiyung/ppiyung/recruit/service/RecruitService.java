package org.ppiyung.ppiyung.recruit.service;

import org.ppiyung.ppiyung.recruit.vo.Recruit;

public interface RecruitService {

	public boolean insertRecruitNotice(Recruit recruit);

	public boolean updateRecruitNotice(Recruit recruit, int recruit_id);

	public boolean deleteRecruitNotice(int recruit_id);
	
	
}
