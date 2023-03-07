package org.ppiyung.ppiyung.test;

public interface TestDao {
	public MemberTestVO login(MemberTestVO member);
	
	public MemberTestVO select(MemberTestVO member);
	
	public int insert(MemberTestVO member) throws Exception;
}
