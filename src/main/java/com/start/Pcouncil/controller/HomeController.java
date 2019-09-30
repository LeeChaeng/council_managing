 package com.start.Pcouncil.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.start.Pcouncil.board.domain.MemberVO;
import com.start.Pcouncil.board.service.MemberService;
import com.start.Pcouncil.common.CommonUtils;


@Controller
public class HomeController {
	
	@Resource(name="com.start.Pcouncil.board.service.MemberService")
	MemberService mMemberService;
	
	
	@RequestMapping("/")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		System.out.println(request.isRequestedSessionIdFromCookie());
		System.out.println(request.getSession().getAttribute("loginInfo_name"));
		
		mv.addObject("loginInfo_name", request.getSession().getAttribute("loginInfo_name"));
		mv.addObject("loginInfo_email", request.getSession().getAttribute("loginInfo_email"));
		System.out.println(request.getSession().getId());
		mv.setViewName("main");

		return mv;
	}
	
	
	@RequestMapping("/callback")
	public ModelAndView test(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		String code = request.getParameter("code");
		
		//profile
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("code", code);
		parameters.add("client_id", "1081631180636-941o7695i80gkt8qhpadr8igdiekh5lb.apps.googleusercontent.com");
		parameters.add("client_secret", "Kf8N5nDdLnwd_VYuxAGX8lV2");
		parameters.add("redirect_uri", "http://localhost:4000/callback");
		parameters.add("grant_type", "authorization_code");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
		ResponseEntity<Map> responseEntity = restTemplate.exchange("https://www.googleapis.com/oauth2/v4/token", HttpMethod.POST, requestEntity, Map.class);
		Map<String, Object> responseMap = responseEntity.getBody();
				
		String[] tokens = ((String)responseMap.get("id_token")).split("\\.");
		
		Base64 base64 = new Base64(true);
		String body = new String(base64.decode(tokens[1]),"utf-8");


		System.out.println(new String(Base64.decodeBase64(tokens[0]),"utf-8"));
		System.out.println(new String(Base64.decodeBase64(tokens[1]),"utf-8"));		
		
		ObjectMapper mapper = new ObjectMapper();
        Map<String, String> result = mapper.readValue(body, Map.class);
        System.out.println(result.get("email"));
        System.out.println(result.get("name"));
        System.out.println(result.get("picture"));
        
        String email = result.get("email");
        String name = result.get("name");
        String photo = result.get("picture");
        System.out.println(photo);
        
        MemberVO member = new MemberVO();
        
        member.setEmail(email);
        member.setName(name);
        member.setPhoto(photo);
        if(mMemberService.memExistService(email)) {
        	//true 라면 -> 존재하는 것! select해주는 service 호출
        	mMemberService.memSelectService(email);
        } else {
        	//false라면 --> 없는 것ㅂ!! insert service 호출!
        	mMemberService.memInsertService(member);
        }
        
       
		System.out.println("redirecting to home page");
		mv.addObject("email", email);
		mv.addObject("name", name);
		mv.setViewName("redirect:/sessionProcess");
		return mv;
	}
	
	@RequestMapping("/sessionProcess") 
	public String sessionProcess(HttpServletRequest request) throws Exception {
		
		request.getSession().setAttribute("loginInfo_email", request.getParameter("email"));	
		request.getSession().setAttribute("loginInfo_name", request.getParameter("name"));	
		request.getSession().setMaxInactiveInterval(60*30);
		
		System.out.println(request.getSession());
		System.out.println(request.getSession().getAttribute("loginInfo_name"));
		return "redirect:/";
	}
	
	@RequestMapping("/log_out")
	public String logout(HttpServletRequest request) throws Exception {
		
		request.getSession().removeAttribute("loginInfo_email");
		System.out.println("세션 해제");
		request.getSession().removeAttribute("loginInfo_name");
		request.getSession().invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("do_council")
	public String dCouncil() {
		return "dcouncil";
	}
	
	
	
	
}
