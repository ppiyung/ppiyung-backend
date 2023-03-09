package org.ppiyung.ppiyung.member.dao;

import org.ppiyung.ppiyung.member.vo.Member;

public interface MemberDao {

	public Member login(Member member);

	public int signin(Member member);

	public Member selectMemberId(Member member);
}
