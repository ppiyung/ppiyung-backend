package org.ppiyung.ppiyung.member.dao;

import org.ppiyung.ppiyung.member.vo.Member;

public interface MemberDao {

	public Member login(Member member);

	public void insertMember(Member member) throws Exception;

	public Member selectMemberId(Member member);

	public void updateInfo(Member member) throws Exception;
}
