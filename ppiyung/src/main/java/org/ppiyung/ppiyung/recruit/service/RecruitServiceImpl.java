package org.ppiyung.ppiyung.recruit.service;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.notify.dao.NotifyDao;
import org.ppiyung.ppiyung.notify.vo.Notification;
import org.ppiyung.ppiyung.recruit.dao.RecruitDaoImpl;
import org.ppiyung.ppiyung.recruit.vo.Apply;
import org.ppiyung.ppiyung.recruit.vo.ApplyExtended;
import org.ppiyung.ppiyung.recruit.vo.BookMark;
import org.ppiyung.ppiyung.recruit.vo.ProposalsList;
import org.ppiyung.ppiyung.recruit.vo.Recruit;
import org.ppiyung.ppiyung.recruit.vo.RecruitOption;
import org.ppiyung.ppiyung.recruit.vo.Suggest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class RecruitServiceImpl implements RecruitService{

	@Autowired
	private RecruitDaoImpl dao;
	
	@Autowired
	private NotifyDao notifyDao;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
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
	public List<Recruit> getRecruitList(RecruitOption option) {
		List<Recruit> list = dao.selectAll(option);
		return list;
	}

	@Override
	public int getRecruitListTotal(RecruitOption option) {
		int count = dao.selectAllTotal(option);
		return count;
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
	public HashMap<String, Object> getRecruitStatus() {
    	HashMap<String, Object> map = dao.selectStatistic();
		return map;
	}

	@Override
    public List<Recruit> getRecruitListOfCompany(String companyId) {
    	List<Recruit> list = dao.selectAllByCompany(companyId);
		return list;
    }
    
    @Override
    public boolean applyForJob(Apply apply) {
        TransactionStatus txStatus =
                transactionManager.getTransaction(new DefaultTransactionDefinition());
        
    	try {
			dao.insertApply(apply);
			
			Recruit recruit = dao.selectAllDetailRecruit(String.valueOf(apply.getRecruitId())).get(0);
			
    		Notification noti = new Notification(0, recruit.getCompanyId(), 0, apply.getApplyId(), null);
			notifyDao.insertApplyNotify(noti);
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			e.printStackTrace();
			return false;
		}
    	
    	transactionManager.commit(txStatus);
		return true;
    }
    
    @Override
    public List<ApplyExtended> getApplyListOfMember(String memberId) {
    	List<ApplyExtended> list = dao.selectByMember(memberId);
    	return list;
    }
    
    @Override
    public List<HashMap<String, Object>> getApplicantsByRecruitNotice(int recruitId) {
    	List<HashMap<String, Object>> list = dao.selectByRecruit(recruitId);
    	return list;
    }
    
    @Override
    public boolean jobOffer(Suggest suggest) {
        TransactionStatus txStatus =
                transactionManager.getTransaction(new DefaultTransactionDefinition());
        
    	try {
			dao.insertSuggest(suggest);
    		Notification noti = new Notification(0, suggest.getMemberId(), 0, suggest.getSuggestId(), null);
			notifyDao.insertApplyNotify(noti);
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			e.printStackTrace();
			return false;
		}
    	
    	transactionManager.commit(txStatus);
		return true;
    }
    
    @Override
    public List<Suggest> getJobOfferOfMember(String memberId) {
    	List<Suggest> list = dao.selectSuggestByMember(memberId);
    	
    	return list;
    }
    
    @Override
    public List<ProposalsList> getJobOfferOfCompany(String companyId) {
        List<ProposalsList> list = dao.selectSuggestByCompany(companyId);
    	
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

	@Override
	public boolean setApplyResult(Apply apply) {
		try {
			dao.updateApply(apply);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
