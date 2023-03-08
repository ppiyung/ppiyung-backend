package org.ppiyung.ppiyung.test;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/test")
@CrossOrigin(origins = "http://localhost:9090", allowCredentials = "true")
@SessionAttributes(value = {"currentMember"})
public class TestController {
	
	@Autowired
	TestService service;
	
	Logger log = LogManager.getLogger("base");

	@GetMapping(value = "")
	public String home(Locale locale, Model model) {
		log.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "test/home";
	}
	
	@GetMapping(value = "/insert")
	public String getInsertTest() {
		return "test/insertTest";
	}
	
	@PostMapping(value = "/insert")
	public String postInsertTest(@ModelAttribute MemberTestVO newMember, Model model) {
		boolean result = service.registerNewAccount(newMember);
		
		model.addAttribute("result", result);
		
		return "test/insertTestResult";
	}
	
	@GetMapping(value = "json")
	@ResponseBody
	public HashMap<String, Object> getJsonTest() {
		HashMap<String, Object> respBody = new HashMap<String, Object>();
		respBody.put("success", true);
		respBody.put("msg", "안녕 세상");
		return respBody;
	}
	
	@PostMapping(value = "/session")
	@ResponseBody
	public HashMap<String, Object> sessionTest(@RequestBody HashMap<String, String> reqBody, Model model) {
		log.debug(reqBody.get("member_id"));
		log.debug(reqBody.get("member_pw"));
		
		MemberTestVO param = new MemberTestVO();
		param.setMemberId(reqBody.get("member_id"));
		param.setMemberPw(reqBody.get("member_pw"));
		
		MemberTestVO member = service.login(param);
		log.debug(member);
		
		HashMap<String, Object> respBody = new HashMap<String, Object>();
		
		if (member != null) {
			model.addAttribute("currentMember", member);
			respBody.put("success", true);
			respBody.put("msg", "로그인에 성공했습니다.");
			respBody.put("payload", member);
		} else {
			model.addAttribute("currentMember", member);
			respBody.put("success", false);
			respBody.put("msg", "로그인에 실패했습니다.");
			respBody.put("payload", member);
		}

		return respBody;
	}
	
}
