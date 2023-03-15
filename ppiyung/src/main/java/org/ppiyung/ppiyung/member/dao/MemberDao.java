package org.ppiyung.ppiyung.member.dao;

import java.util.List;

import org.ppiyung.ppiyung.common.entity.PagingEntity;
import org.ppiyung.ppiyung.member.vo.Image;
import org.ppiyung.ppiyung.member.vo.Member;
import org.ppiyung.ppiyung.member.vo.MemberExtended;

public interface MemberDao {

	public Member login(Member member);

	public void insertMember(Member member) throws Exception;
	
	//개별회원
	public Member selectMemberId(Member member);
	
	public MemberExtended selectMemberIdJoinned(Member member);
	
	public void updateInfo(Member member) throws Exception;
	
	//관리자일경우 모든회원
	public List<Member>  getAllMember(PagingEntity pagingEntity);

	public void leaveMember(String memberId) throws Exception;

	public List<Member> getResumeOpenMember(String workAreaId);

	public void insertMemberImage(Image image) throws Exception;

	public Image getMemberImage(Image image);

	public void updateMemberImage(Image image) throws Exception;
	

}
