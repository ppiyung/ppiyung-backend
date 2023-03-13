package org.ppiyung.ppiyung.member.service;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.member.vo.Member;

public interface MemberService {

	public HashMap<String, Object> login(Member param);
	
	public String regenToken(String refreshToken);

	public boolean signin(Member param);

	public boolean modifyMember(Member param);

	public Member getMemberInfo(Member member);

	public List<Member> getAllMember();

	public boolean leaveMember(String memberId);
}
