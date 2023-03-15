package org.ppiyung.ppiyung.member.service;

import java.util.HashMap;
import java.util.List;

import org.ppiyung.ppiyung.common.entity.PagingEntity;
import org.ppiyung.ppiyung.member.vo.Image;
import org.ppiyung.ppiyung.member.vo.Member;
import org.ppiyung.ppiyung.member.vo.MemberExtended;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {

	public HashMap<String, Object> login(Member param);
	
	public HashMap<String, Object> regenToken(String refreshToken);

	public boolean signin(Member param);

	public boolean modifyMember(Member param);

	public Member getMemberInfo(Member member);
	
	public MemberExtended getMemberInfoJoinned(Member member);

	public List<Member> getAllMember(PagingEntity pagingEntity);

	public boolean leaveMember(String memberId);

	public List<Member> getResumeOpenMember(String workAreaId);

	public Image saveImageFile(MultipartFile file, String memberId, boolean isUpdate);
	
	public boolean addImageFileInfo(Image image);

	public Image getImageFileInfo(Image image);

<<<<<<< HEAD
=======
	public boolean updateImageFileInfo(Image image);
>>>>>>> df47f3d2fd9cb166da862599c8526b49e686890b
}
