package org.ppiyung.ppiyung.member.service;

import java.util.List;

import org.ppiyung.ppiyung.member.vo.Member;

public interface MemberService {

	public Member login(Member param);
	
	public boolean logout();

	public boolean signin(Member param);

	public boolean modifyMember(Member param);

	public Member getMemberInfo(Member member);

	public List<Member> getAllMember();
}
