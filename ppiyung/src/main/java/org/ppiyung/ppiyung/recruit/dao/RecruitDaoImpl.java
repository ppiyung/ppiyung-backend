package org.ppiyung.ppiyung.recruit.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.ppiyung.ppiyung.recruit.vo.Apply;
import org.ppiyung.ppiyung.recruit.vo.BookMark;
import org.ppiyung.ppiyung.recruit.vo.Recruit;
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
	public List<Recruit> selectAll() {
		List<Recruit> list = session.selectList("org.ppiyung.ppiyung.recruit.selectAll");

		return list;
	}

	@Override
	public List<Recruit> selectByKeyword(String keyword) {
		List<Recruit> list = session.selectList("org.ppiyung.ppiyung.recruit.selectByKeyword", keyword);

		return list;
	}

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

}
