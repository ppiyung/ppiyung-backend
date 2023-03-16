package org.ppiyung.ppiyung.member.service;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ppiyung.ppiyung.common.util.JwtTokenUtil;
import org.ppiyung.ppiyung.member.dao.MemberDao;
import org.ppiyung.ppiyung.member.vo.Image;
import org.ppiyung.ppiyung.member.vo.Member;
import org.ppiyung.ppiyung.member.vo.MemberExtended;
import org.ppiyung.ppiyung.member.vo.MemberOption;
import org.ppiyung.ppiyung.member.vo.OpenResumeOption;
import org.ppiyung.ppiyung.member.vo.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MemberServiceImpl implements MemberService {

	private Logger log = LogManager.getLogger("base");
	
	@Autowired
	private MemberDao dao;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ResourceLoader rsLoader;

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
	public List<Member> getAllMember(MemberOption option) {
		List<Member> list = dao.getAllMember(option);
		return list;
	}

	@Override
	public int getAllMemberCount(MemberOption option) {
		return dao.getAllMemberCount(option);
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
	public List<MemberExtended> getResumeOpenMember(OpenResumeOption option) {
		List<MemberExtended> list = dao.getResumeOpenMember(option);
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
	public boolean updateImageFileInfo(Image image) {
		try {
			dao.updateMemberImage(image);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Image getImageFileInfo(Image image) {
		try {
			return dao.selectMemberImage(image);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
  
	private boolean fileSaveHelper(MultipartFile file, String uploadLocation, String saveFileName) {
		Resource resource = rsLoader.getResource(uploadLocation);
		String uploadFolder = "";
		try {
			uploadFolder = resource.getFile().getCanonicalPath();
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		
		log.debug("파일 저장 시도");
		
		File saveFile = new File(uploadFolder + "/" + saveFileName);
		try {
			file.transferTo(saveFile);
			log.debug("파일 저장 성공: " + uploadFolder + "/" + saveFileName);
			return true;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Image saveImageFile(MultipartFile file, String memberId, boolean isUpdate) {
		String filenameOriginalFull = file.getOriginalFilename();
		String filenameOriginal = 
				filenameOriginalFull.substring(0, filenameOriginalFull.lastIndexOf("."));
		String fileExtension = 
				filenameOriginalFull.substring(filenameOriginalFull.lastIndexOf(".") + 1, filenameOriginalFull.length());
		UUID uuid = UUID.randomUUID();
		String saveFileName = uuid.toString() + "." + fileExtension;

		boolean fileSaveResult = fileSaveHelper(file, "resources/images", saveFileName);
				
		Image image = null;
		boolean dbResult = false;
		if (fileSaveResult) {
			image = new Image(memberId, saveFileName,
					filenameOriginal, fileExtension, null);
			
			if (isUpdate) {
				dbResult = updateImageFileInfo(image);
			} else {
				dbResult = addImageFileInfo(image);
			}
		}
		
		if (fileSaveResult && dbResult) {
			return getImageFileInfo(image);
		} else {
			return null;
		}
	}

	@Override
	public Resume getResumeFileInfo(Resume resume) {
		try {
			return dao.selectMemberResume(resume);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateResumeFileInfo(Resume resume) {
		try {
			dao.updateMemberResume(resume);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addResumeFileInfo(Resume resume) {
		try {
			dao.insertMemberResume(resume);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Resume saveResumeFile(MultipartFile file, String memberId, boolean isUpdate) {
		String filenameOriginalFull = file.getOriginalFilename();
		String filenameOriginal = 
				filenameOriginalFull.substring(0, filenameOriginalFull.lastIndexOf("."));
		String fileExtension = 
				filenameOriginalFull.substring(filenameOriginalFull.lastIndexOf(".") + 1, filenameOriginalFull.length());
		UUID uuid = UUID.randomUUID();
	
		boolean fileSaveResult = fileSaveHelper(file, "resources/resumes", uuid.toString());
				
		Resume resume = null;
		boolean dbResult = false;
		if (fileSaveResult) {
			resume = new Resume(memberId, uuid.toString(),
					filenameOriginal, fileExtension, null, false);
			
			if (isUpdate) {
				dbResult = updateResumeFileInfo(resume);
			} else {
				dbResult = addResumeFileInfo(resume);
			}
		}
		
		if (fileSaveResult && dbResult) {
			return getResumeFileInfo(resume);
		} else {
			return null;
		}
	}

	@Override
	public void serveResumeFile(Resume resume, HttpServletResponse response) {
		String saveFilename = resume.getResumeLocation();
		String originalFilename = resume.getResumeFilename() + "." + resume.getResumeFiletype();
		
		Resource resource = rsLoader.getResource("resources/resumes");
		String uploadFolder = "";
		try {
			uploadFolder = resource.getFile().getCanonicalPath();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		File downloadFile = new File(uploadFolder + "/" + saveFilename);
		
		try(ServletOutputStream stream = response.getOutputStream()) {
			byte fileByte[] = FileUtils.readFileToByteArray(downloadFile);
	        
	        response.setContentType("application/octet-stream");
	        response.setContentLength(fileByte.length);
	        
	        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFilename,"UTF-8") +"\";");
	        response.setHeader("Content-Transfer-Encoding", "binary");
	        
	        stream.write(fileByte);
	        stream.flush();
	        
	        log.debug("이력서 파일 전송 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
