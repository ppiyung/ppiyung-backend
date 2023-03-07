package org.ppiyung.ppiyung.test;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl implements TestDao {
	@Autowired
	private SqlSession session;
	
	@Override
	public int insert(MemberTestVO member) throws Exception {
		int affectedRows = 0;
		affectedRows = session.insert("org.ppiyung.ppiyung.test.insert", member);	
		
		if (affectedRows != 1) {
			throw new Exception();
		}
		return affectedRows;
	}

	@Override
	public MemberTestVO login(MemberTestVO member) {
		return session.selectOne("org.ppiyung.ppiyung.test.login", member);
	}

	@Override
	public MemberTestVO select(MemberTestVO member) {
		return session.selectOne("org.ppiyung.ppiyung.test.select", member);
	}
}
