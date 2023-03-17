package org.ppiyung.ppiyung.member.service;

import org.ppiyung.ppiyung.member.dao.MemberDao;
import org.ppiyung.ppiyung.member.vo.Member;
import org.ppiyung.ppiyung.member.vo.SecurityUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SecurityUserDetailService implements UserDetailsService {

	@Autowired
	MemberDao dao;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		try {
			Member param = new Member();
			param.setMemberId(userId);
			
			Member member = dao.selectMemberId(param);
			if (!member.isMemberVerified() || !member.isMemberActive()) {
				throw new Exception();
			}
			
			return new SecurityUserDetails(dao.selectMemberId(param));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
