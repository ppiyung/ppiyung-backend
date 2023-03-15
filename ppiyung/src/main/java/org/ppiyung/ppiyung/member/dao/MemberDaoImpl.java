package org.ppiyung.ppiyung.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.ppiyung.ppiyung.common.entity.PagingEntity;
import org.ppiyung.ppiyung.member.vo.Image;
import org.ppiyung.ppiyung.member.vo.Member;
import org.ppiyung.ppiyung.member.vo.MemberExtended;
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
		Member member = session.selectOne("org.ppiyung.ppiyung.member.select", param);
		return member;
	}
	
	@Override
	public MemberExtended selectMemberIdJoinned(Member param) {
		MemberExtended member = session.selectOne("org.ppiyung.ppiyung.member.selectJoinned", param);
		return member;
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


	@Override
	public List<Member> getAllMember(PagingEntity pagingEntity) {
		List<Member> list = session.selectList("org.ppiyung.ppiyung.member.selectAll",pagingEntity);

		return list;

	}


	@Override
	public void leaveMember(String param) throws Exception {
		int count = session.update("org.ppiyung.ppiyung.member.deleteMember", param);
		if (count != 1) {
			throw new Exception();
		}
	}


	@Override
	public List<Member> getResumeOpenMember(String param) {
		List<Member> list = session.selectList("org.ppiyung.ppiyung.member.seletResumeOpenMember" , param);
		return list;
	}


	@Override
	public void insertMemberImage(Image image) throws Exception {
		int count = session.insert("org.ppiyung.ppiyung.member.insertImg", image);
		if (count != 1) {
			throw new Exception();
		}
	}


	@Override
	public Image getMemberImage(Image image) {
		return session.selectOne("org.ppiyung.ppiyung.member.selectImg" , image);
	}
}
