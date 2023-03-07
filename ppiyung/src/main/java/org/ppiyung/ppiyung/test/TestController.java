package org.ppiyung.ppiyung.test;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
	
	@Autowired
	TestService service;
	
	Logger log = LogManager.getLogger("base");
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */

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
	
	@CrossOrigin
	@GetMapping(value = "json")
	@ResponseBody
	public HashMap<String, Object> getJsonTest() {
		HashMap<String, Object> respBody = new HashMap<String, Object>();
		respBody.put("success", true);
		respBody.put("msg", "안녕 세상");
		return respBody;
	}
	
}
