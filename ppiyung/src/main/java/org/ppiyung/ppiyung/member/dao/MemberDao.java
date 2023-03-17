package org.ppiyung.ppiyung.member.dao;

import java.util.List;

import org.ppiyung.ppiyung.member.vo.Image;
import org.ppiyung.ppiyung.member.vo.Member;
import org.ppiyung.ppiyung.member.vo.MemberExtended;
import org.ppiyung.ppiyung.member.vo.MemberOption;
import org.ppiyung.ppiyung.member.vo.OpenResumeOption;
import org.ppiyung.ppiyung.member.vo.Resume;

public interface MemberDao {

	public Member login(Member member);

	public void insertMember(Member member) throws Exception;
	
	//개별회원
	public Member selectMemberId(Member member);
	
	public MemberExtended selectMemberIdJoinned(Member member);
	
	public void updateInfo(Member member) throws Exception;
	
	//관리자일경우 모든회원
	public List<Member>  getAllMember(MemberOption option);

	public int  getAllMemberCount(MemberOption option);

	public void leaveMember(String memberId) throws Exception;

	public List<MemberExtended> getResumeOpenMember(OpenResumeOption option);

	public void insertMemberImage(Image image) throws Exception;

	public Image selectMemberImage(Image image);

	public void updateMemberImage(Image image) throws Exception;

	public Resume selectMemberResume(Resume resume);

	public void updateMemberResume(Resume resume) throws Exception;

	public void insertMemberResume(Resume resume) throws Exception;
	

}
