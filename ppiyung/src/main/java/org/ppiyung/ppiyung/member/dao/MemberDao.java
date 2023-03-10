package org.ppiyung.ppiyung.member.dao;

import org.ppiyung.ppiyung.member.vo.Member;

public interface MemberDao {

	public Member login(Member member);

	public void signin(Member member) throws Exception;

	public Member selectMemberId(Member member);
}
