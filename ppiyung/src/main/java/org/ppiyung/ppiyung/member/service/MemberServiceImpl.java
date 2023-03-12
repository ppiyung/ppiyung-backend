package org.ppiyung.ppiyung.member.service;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.common.util.JwtTokenUtil;
import org.ppiyung.ppiyung.member.dao.MemberDao;
import org.ppiyung.ppiyung.member.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao dao;
	
	@Autowired
    private AuthenticationManager authenticationManager;
    
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public HashMap<String, String> login(Member member) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(member.getMember_id(), member.getMember_pw());
	        Authentication authentication = authenticationManager.authenticate(authenticationToken);
	 
	        // 3. 인증 정보를 기반으로 JWT 토큰 생성
	        HashMap<String, String> result = jwtTokenUtil.generateToken(authentication);
	        return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean signin(Member member) {
		try {
			member.setMember_pw(passwordEncoder.encode(member.getMember_pw()));
			dao.insertMember(member);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 회원정보수정
	@Override
	public boolean modifyMember(Member member) {
		try {
			member.setMember_pw(passwordEncoder.encode(member.getMember_pw()));
			dao.updateInfo(member);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// 회원정보조회
	@Override
	public Member getMemberInfo(Member member) {

		Member result = dao.selectMemberId(member);
		
		return result;

	}

	@Override
	public List<Member> getAllMember() {
		List<Member> list = dao.getAllMember();
		return list;
	}

	@Override
	public String regenToken(String refreshToken) {
		try {
	        return jwtTokenUtil.reGenerateTokenFromRefreshToken(refreshToken);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
