package org.ppiyung.ppiyung.recruit.service;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.common.entity.PagingEntity;
import org.ppiyung.ppiyung.recruit.dao.RecruitDaoImpl;
import org.ppiyung.ppiyung.recruit.vo.Apply;
import org.ppiyung.ppiyung.recruit.vo.BookMark;
import org.ppiyung.ppiyung.recruit.vo.Recruit;
import org.ppiyung.ppiyung.recruit.vo.RecruitBookMark;
import org.ppiyung.ppiyung.recruit.vo.Suggest;
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
	public List<Recruit> getRecruitList(PagingEntity pagingEntity) {
		List<Recruit> list = dao.selectAll(pagingEntity);
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
    
    @Override
    public boolean applyForJob(Apply apply) {
    	try {
			dao.insertApply(apply);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    
    @Override
    public List<Apply> getApplyListOfMember(String memberId) {
    	List<Apply> list = dao.selectByMember(memberId);
    	return list;
    }
    
    @Override
    public List<HashMap<String, Object>> getApplicantsByRecruitNotice(int recruitId) {
    	List<HashMap<String, Object>> list = dao.selectByRecruit(recruitId);
    	return list;
    }
    
    @Override
    public boolean jobOffer(Suggest suggest) {
    	try {
			dao.insertSuggest(suggest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    
    @Override
    public List<Suggest> getJobOfferOfMember(String memberId) {
    	List<Suggest> list = dao.selectSuggestByMember(memberId);
    	
    	return list;
    }
    
    @Override
    public List<Suggest> getJobOfferOfCompany(String companyId) {
        List<Suggest> list = dao.selectSuggestByCompany(companyId);
    	
    	return list;    
    }
    
	@Override
	public List<Recruit> getRecruitDetailInfo(String recruitId) {
		List<Recruit> list = dao.selectAllDetailRecruit(recruitId);
		return list;
	}

	@Override
	public boolean addBookmarkRecruit(BookMark bookMark) {
		try {
			dao.insertBookmark(bookMark);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removeBookmarkRecruit(BookMark bookMark) {
		try {
			dao.deleteBookmark(bookMark);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<HashMap<String, Object>> getMyBookmarkList(String memberId) {
		List<HashMap<String,Object>>  list = dao.selectBookmarkList(memberId);
			return list;
	}
}
