package org.ppiyung.ppiyung.test;

public interface TestService {
	public boolean registerNewAccount(MemberTestVO newMember);
	public MemberTestVO login(MemberTestVO member);
}
