package org.ppiyung.ppiyung.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.ppiyung.ppiyung.common.entity.PagingEntity;
import org.ppiyung.ppiyung.member.vo.Image;
import org.ppiyung.ppiyung.member.vo.Member;
import org.ppiyung.ppiyung.member.vo.MemberExtended;
import org.ppiyung.ppiyung.member.vo.MemberOption;
import org.ppiyung.ppiyung.member.vo.OpenResumeOption;
import org.ppiyung.ppiyung.member.vo.Resume;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {

	public HashMap<String, Object> login(Member param);
	
	public HashMap<String, Object> regenToken(String refreshToken);

	public boolean signin(Member param);

	public boolean modifyMember(Member param);

	public Member getMemberInfo(Member member);
	
	public MemberExtended getMemberInfoJoinned(Member member);

	public List<Member> getAllMember(MemberOption option);
	
	public int getAllMemberCount(MemberOption option);

	public boolean leaveMember(String memberId);

	public List<MemberExtended> getResumeOpenMember(OpenResumeOption option);

	public Image saveImageFile(MultipartFile file, String memberId, boolean isUpdate);
	
	public boolean addImageFileInfo(Image image);

	public Image getImageFileInfo(Image image);

	public boolean updateImageFileInfo(Image image);

	public Resume getResumeFileInfo(Resume resume);
	
	public boolean updateResumeFileInfo(Resume resume);
	
	public boolean addResumeFileInfo(Resume resume);

	public Resume saveResumeFile(MultipartFile file, String memberId, boolean isUpdate);

	public void serveResumeFile(Resume resume, HttpServletResponse response);

	public boolean modifyResume(Resume reqUpdateInfo);
}