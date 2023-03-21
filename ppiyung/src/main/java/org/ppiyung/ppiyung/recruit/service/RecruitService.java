package org.ppiyung.ppiyung.recruit.service;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.common.entity.PagingEntity;
import org.ppiyung.ppiyung.recruit.vo.Apply;
import org.ppiyung.ppiyung.recruit.vo.ApplyExtended;
import org.ppiyung.ppiyung.recruit.vo.BookMark;
import org.ppiyung.ppiyung.recruit.vo.ProposalsList;
import org.ppiyung.ppiyung.recruit.vo.Recruit;
import org.ppiyung.ppiyung.recruit.vo.RecruitBookMark;
import org.ppiyung.ppiyung.recruit.vo.RecruitOption;
import org.ppiyung.ppiyung.recruit.vo.Suggest;

public interface RecruitService {

	public boolean insertRecruitNotice(Recruit recruit);

	public boolean updateRecruitNotice(Recruit recruit);

	public boolean closeRecruitNotice(int recruitId);
	
    public List<Recruit> getRecruitList(RecruitOption option);
	
    @Deprecated
    public List<Recruit> getRecruitListByWorkAreaId(int work_area_id);

    @Deprecated
	public List<Recruit> getRecruitListByKeyword(String keyword);

	public HashMap<String, Object> getRecruitStatusOfCompany(String companyId);

	public List<Recruit> getRecruitListOfCompany(String companyId);

	public boolean applyForJob(Apply apply);
	
	public List<Recruit> getRecruitDetailInfo(String recruitId);

	public boolean addBookmarkRecruit(BookMark bookMark);

	public boolean removeBookmarkRecruit(BookMark bookMark);

	public boolean jobOffer(Suggest suggest);

	public List<HashMap<String, Object>> getMyBookmarkList(String memberId);

	public List<ApplyExtended> getApplyListOfMember(String memberId);

	public List<HashMap<String, Object>> getApplicantsByRecruitNotice(int recruitId);

	public List<Suggest> getJobOfferOfMember(String memberId);

	public List<ProposalsList> getJobOfferOfCompany(String companyId);

	public HashMap<String, Object> getRecruitStatus();

	public int getRecruitListTotal(RecruitOption option);

	public boolean setApplyResult(Apply param);


	
}
