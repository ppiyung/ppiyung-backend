package org.ppiyung.ppiyung.member.service;

import org.ppiyung.ppiyung.member.vo.Member;

public interface MemberService {

	public Member login(Member param);
	
	public boolean logout();
}
