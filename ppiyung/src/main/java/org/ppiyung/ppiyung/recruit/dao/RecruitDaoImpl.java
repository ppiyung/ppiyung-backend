package org.ppiyung.ppiyung.recruit.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.ppiyung.ppiyung.common.entity.PagingEntity;
import org.ppiyung.ppiyung.recruit.vo.Apply;
import org.ppiyung.ppiyung.recruit.vo.ApplyExtended;
import org.ppiyung.ppiyung.recruit.vo.BookMark;
import org.ppiyung.ppiyung.recruit.vo.ProposalsList;
import org.ppiyung.ppiyung.recruit.vo.Recruit;
import org.ppiyung.ppiyung.recruit.vo.RecruitBookMark;
import org.ppiyung.ppiyung.recruit.vo.RecruitOption;
import org.ppiyung.ppiyung.recruit.vo.Suggest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecruitDaoImpl implements RecruitDao {

	@Autowired
	SqlSession session;

	@Override
	public void insertRecruitNotice(Recruit param) throws Exception {
		int count = session.insert("org.ppiyung.ppiyung.recruit.insert", param);
		if (count != 1) {
			throw new Exception();
		}
	}

	@Override
	public void updateRecruitNotice(Recruit param) throws Exception {
		int count = session.update("org.ppiyung.ppiyung.recruit.update", param);
		if (count != 1) {
			throw new Exception();
		}
	}

	@Override
	public void updateRecruitEndDate(int recruitId) throws Exception {
		int count = session.update("org.ppiyung.ppiyung.recruit.updateEndAt", recruitId);
		if (count != 1) {
			throw new Exception();
		}

	}

	@Override
	public List<Recruit> selectByWorkAreaId(int workAreaId) {
		List<Recruit> list = session.selectList("org.ppiyung.ppiyung.recruit.selectByWorkAreaId", workAreaId);

		return list;
	}

	@Override

	public List<Recruit> selectAll(RecruitOption option) {
        List<Recruit> list = session.selectList("org.ppiyung.ppiyung.recruit.select", option);
		return list;
	}

	@Override
	public int selectAllTotal(RecruitOption option) {
		int count = session.selectOne("org.ppiyung.ppiyung.recruit.countTotal", option);
		return count;
	}

	@Override
	public List<Recruit> selectByKeyword(String keyword) {
		List<Recruit> list = session.selectList("org.ppiyung.ppiyung.recruit.selectByKeyword", keyword);

		return list;
	}

	@Override
	public HashMap<String, Object> selectByCompany(String companyId) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		int closedRecruitNum = session.selectOne("org.ppiyung.ppiyung.recruit.selectClosedRecruit", companyId);
		int recruitingNum = session.selectOne("org.ppiyung.ppiyung.recruit.selectRecruiting", companyId);
		int applicantsNum = session.selectOne("org.ppiyung.ppiyung.recruit.selectAllApplicants", companyId);
		int applicatnsPassedNum = session.selectOne("org.ppiyung.ppiyung.recruit.selectApplicantsPassed", companyId);

		map.put("closedRecruitNum", closedRecruitNum);
		map.put("recruitingNum", recruitingNum);
		map.put("applicantsNum", applicantsNum);
		map.put("applicantsPassed", applicatnsPassedNum);

		return map;
	}

	public HashMap<String, Object> selectStatistic() {
		return session.selectOne("org.ppiyung.ppiyung.recruit.selectStatistic");
	}

	@Override
	public List<Recruit> selectAllByCompany(String companyId) {
		List<Recruit> list = session.selectList("org.ppiyung.ppiyung.recruit.selectAllByCompany", companyId);

		return list;
	}

	@Override
	public void insertApply(Apply apply) throws Exception {
		int count = session.insert("org.ppiyung.ppiyung.apply.insert", apply);
		if (count != 1) {
			throw new Exception();
		}

	}

	@Override
	public List<ApplyExtended> selectByMember(String memberId) {

		List<ApplyExtended> list = session.selectList("org.ppiyung.ppiyung.apply.selectByMember",memberId);
	
		return list;
	}

	@Override
	public Apply selectApplyByApplyId(int applyId) {
		Apply apply = session.selectOne("org.ppiyung.ppiyung.apply.selectByApplyId",applyId);
		
		return apply;
	}
	
	@Override
	public List<HashMap<String, Object>> selectByRecruit(int recruitId) {
		List<HashMap<String, Object>> list = session.selectList("org.ppiyung.ppiyung.apply.selectByRecruit",recruitId);
		
		return list;
	}
	
	@Override
	public void insertSuggest(Suggest suggest) throws Exception {
		int count = session.insert("org.ppiyung.ppiyung.suggest.insert", suggest);
		if (count != 1) {
			throw new Exception();
		}

	}
	// 일반회원-회원별 받은 입사제안 조회 
	@Override
	public List<Suggest> selectSuggestByMember(String memberId) {
		List<Suggest> list = session.selectList("org.ppiyung.ppiyung.suggest.selectSuggestByMember", memberId);		  
		System.out.println("list 03/19" + list);
		return list; 
	}

	@Override
	public List<ProposalsList> selectSuggestByCompany(String companyId) {
		List<ProposalsList> list = session.selectList("org.ppiyung.ppiyung.suggest.selectByCompany", companyId);		  
		return list;
	}

	@Override
	public List<Recruit> selectAllDetailRecruit(String recruitId) {
		List<Recruit> list = session.selectList("org.ppiyung.ppiyung.recruit.selectAllDetailRecruit", recruitId);

		return list;
	}

	@Override
	public void insertBookmark(BookMark bookMark) throws Exception {
		int count = session.insert("org.ppiyung.ppiyung.bookmark.insertBookMark", bookMark);
		if (count != 1) {
			throw new Exception();
		}

	}

	@Override
	public void deleteBookmark(BookMark bookMark) throws Exception {
		int count = session.delete("org.ppiyung.ppiyung.bookmark.deleteBookMark", bookMark);
		if (count != 1) {
			throw new Exception();
		}
	}
	@Override
	public List<HashMap<String, Object>> selectBookmarkList(String memberId) {
		List<HashMap<String,Object>>  list = session.selectList("org.ppiyung.ppiyung.bookmark.selectBookmarkList", memberId);
		return list;
	}

	@Override
	public void updateApply(Apply apply) throws Exception {
		int count = session.update("org.ppiyung.ppiyung.apply.update", apply);
		if (count != 1) {
			throw new Exception();
		}
	}


}
