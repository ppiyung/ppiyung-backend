package org.ppiyung.ppiyung.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.ppiyung.ppiyung.member.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	SqlSession session;
	
	@Override
	public Member login(Member param) {
		return session.selectOne("org.ppiyung.ppiyung.member.login", param);
	}

	
	@Override
	public Member selectMemberId(Member param) {
		
		return session.selectOne("org.ppiyung.ppiyung.member.select", param);
	}
	
	@Override
	public void insertMember(Member param) throws Exception {
		int count = session.insert("org.ppiyung.ppiyung.member.signin", param);
		if (count != 1) {
			throw new Exception();
		}
	}


	@Override
	public void updateInfo(Member param) throws Exception {
		int count = session.update("org.ppiyung.ppiyung.member.updateMember",param);
		if (count != 1) {
			throw new Exception();
		}
		
	}


}
