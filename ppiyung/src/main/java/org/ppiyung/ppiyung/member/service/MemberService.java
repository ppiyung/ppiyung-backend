package org.ppiyung.ppiyung.member.service;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.common.entity.PagingEntity;
import org.ppiyung.ppiyung.member.vo.Image;
import org.ppiyung.ppiyung.member.vo.Member;

public interface MemberService {

	public HashMap<String, Object> login(Member param);
	
	public HashMap<String, Object> regenToken(String refreshToken);

	public boolean signin(Member param);

	public boolean modifyMember(Member param);

	public Member getMemberInfo(Member member);

	public List<Member> getAllMember(PagingEntity pagingEntity);

	public boolean leaveMember(String memberId);

	public List<Member> getResumeOpenMember(String workAreaId);

	public boolean addImageFileInfo(Image image);

	public Image getImageFileInfo(Image image);

}
