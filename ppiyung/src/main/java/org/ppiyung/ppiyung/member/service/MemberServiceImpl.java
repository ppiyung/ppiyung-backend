package org.ppiyung.ppiyung.member.service;

import org.ppiyung.ppiyung.member.dao.MemberDao;
import org.ppiyung.ppiyung.member.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao dao;
	
	@Override
	public Member login(Member member) {
		try {
			return dao.login(member);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	@Override
	public boolean signin(Member member) {

		try {
			dao.insertMember(member);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}

	@Override
	public boolean modifyMember(Member member) {
		try {
			dao.updateInfo(member);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	
	}

}
