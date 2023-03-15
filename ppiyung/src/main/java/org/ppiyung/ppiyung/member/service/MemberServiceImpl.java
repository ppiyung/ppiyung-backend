package org.ppiyung.ppiyung.member.service;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.common.entity.PagingEntity;
import org.ppiyung.ppiyung.common.util.JwtTokenUtil;
import org.ppiyung.ppiyung.member.dao.MemberDao;
import org.ppiyung.ppiyung.member.vo.Image;
import org.ppiyung.ppiyung.member.vo.Member;
import org.ppiyung.ppiyung.member.vo.MemberExtended;
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
	public HashMap<String, Object> login(Member member) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					member.getMemberId(), member.getMemberPw());
			Authentication authentication = authenticationManager.authenticate(authenticationToken);

			// 3. 인증 정보를 기반으로 JWT 토큰 생성
			HashMap<String, Object> result = jwtTokenUtil.generateToken(authentication);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 회원가입
	@Override
	public boolean signin(Member member) {
		try {
			member.setMemberPw(passwordEncoder.encode(member.getMemberPw()));
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
			member.setMemberPw(passwordEncoder.encode(member.getMemberPw()));
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
	public MemberExtended getMemberInfoJoinned(Member member) {
		MemberExtended result = dao.selectMemberIdJoinned(member);

		return result;
	}

	// 모든 멤버 조회
	@Override
	public List<Member> getAllMember(PagingEntity pagingEntity) {
		List<Member> list = dao.getAllMember(pagingEntity);
		return list;
	}

	// 토큰
	@Override
	public HashMap<String, Object> regenToken(String refreshToken) {
		try {
			return jwtTokenUtil.reGenerateTokenFromRefreshToken(refreshToken);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 회원탈퇴
	@Override
	public boolean leaveMember(String memberId) {
		try {
			dao.leaveMember(memberId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//직무별 이력서 공개 멤버조회
	@Override
	public List<Member> getResumeOpenMember(String workAreaId) {
		List<Member> list = dao.getResumeOpenMember(workAreaId);
		return list;
	}

	@Override
	public boolean addImageFileInfo(Image image) {
		try {
			dao.insertMemberImage(image);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Image getImageFileInfo(Image image) {
		try {
			return dao.getMemberImage(image);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
