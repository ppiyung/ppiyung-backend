package org.ppiyung.ppiyung.member.dao;

import java.util.List;

import org.ppiyung.ppiyung.member.vo.Member;
import org.ppiyung.ppiyung.member.vo.Notification;

public interface MemberDao {

	public Member login(Member member);

	public void insertMember(Member member) throws Exception;
	
	//개별회원
	public Member selectMemberId(Member member);
	
	
	public void updateInfo(Member member) throws Exception;
	
	//관리자일경우 모든회원
	public List<Member>  getAllMember();

	public void leaveMember(String memberId) throws Exception;

	public List<Member> getResumeOpenMember(String workAreaId);

	public List<Notification> getNotificationList(String memberId);
	
}
