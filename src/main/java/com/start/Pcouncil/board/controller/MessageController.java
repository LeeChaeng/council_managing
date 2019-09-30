package com.start.Pcouncil.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.start.Pcouncil.board.domain.Criteria;
import com.start.Pcouncil.board.domain.MessageVO;
import com.start.Pcouncil.board.domain.PageMaker;
import com.start.Pcouncil.board.service.MessageService;
import com.start.Pcouncil.common.CommonUtils;

@Controller
public class MessageController {
	
	@Resource(name="com.start.Pcouncil.board.service.MessageService")
	MessageService mMessageService;
	

	@RequestMapping(value = "/send_message", method=RequestMethod.POST)
	public String sendMessage(MessageVO message, HttpServletRequest  request) throws Exception {
		boolean isSession = CommonUtils.isSession(request);
		
		
		if (isSession) { //세션 값이 있다면
			List val = new ArrayList();
			val = CommonUtils.returnSession(request);
		} else { //세션 값이 없다면
			return "redirect:/";
		}
		
		byte[] imgByte = message.getFiles_0().getBytes();
		message.setAddfile(imgByte);
	
		mMessageService.messageInsertService(message);
		
		return "redirect:/want_council";
	}
	
	@RequestMapping("/want_council")
	public String messageList(@ModelAttribute("cri") Criteria cri, Model model, HttpServletRequest request) throws Exception {
		System.out.println(cri.toString());
		boolean isSession = CommonUtils.isSession(request);

		List val = new ArrayList();
		if (isSession) { //세션 값이 있다면
			val = CommonUtils.returnSession(request);
			model.addAttribute("loginInfo_name", val.get(0).toString());
			model.addAttribute("loginInfo_email", val.get(1).toString());
		} else { //세션 값이 없다면
			return "redirect:/";
		}
		
		PageMaker page = new PageMaker();
		page.setCri(cri);
		page.setTotalCount(mMessageService.messageRecCountService(val.get(1).toString()));
		
		model.addAttribute("pageMaker", page);
		model.addAttribute("m_list",mMessageService.messagePagingService(cri));
		System.out.println(mMessageService.messageListService());
		
		return "wcouncil";
	}
	
	@RequestMapping("/want_council/send")
	private String wCouncilSend(@ModelAttribute("cri") Criteria cri, Model model,HttpServletRequest request) throws Exception {
		boolean isSession = CommonUtils.isSession(request);
		System.out.println(cri.toString());

		List val = new ArrayList();
		if (isSession) { //세션 값이 있다면
			val = CommonUtils.returnSession(request);
			model.addAttribute("loginInfo_name", val.get(0).toString());
			model.addAttribute("loginInfo_email", val.get(1).toString());
		} else { //세션 값이 없다면
			return "redirect:/";
		}
		
		PageMaker page = new PageMaker();
		page.setCri(cri);
		page.setTotalCount(mMessageService.messageSenCountService(val.get(1).toString()));
		
		model.addAttribute("pageMaker", page);
		model.addAttribute("m_list",mMessageService.messagePagingService(cri));
		
		return "wcouncil_send"; 	
	}
	
	@RequestMapping("/message_detail/{m_num}")
	private String messageDetail(@PathVariable int m_num, Model model, HttpServletRequest request) throws Exception {
		boolean isSession = CommonUtils.isSession(request);
		
		
		if (isSession) { //세션 값이 있다면
			List val = new ArrayList();
			val = CommonUtils.returnSession(request);
			model.addAttribute("loginInfo_name", val.get(0).toString());
			model.addAttribute("loginInfo_email", val.get(1).toString());
		} else { //세션 값이 없다면
			return "redirect:/";
		}
		model.addAttribute("m_detail", mMessageService.messageDetailService(m_num));
		model.addAttribute("m_update",mMessageService.messageUpdateService(m_num));
		
		return "m_detail";
	}
	
	@RequestMapping("/message_send_detail/{m_num}")
	private String messageSDetail(@PathVariable int m_num, Model model, HttpServletRequest request) throws Exception {
		boolean isSession = CommonUtils.isSession(request);
		
		
		if (isSession) { //세션 값이 있다면
			List val = new ArrayList();
			val = CommonUtils.returnSession(request);
			model.addAttribute("loginInfo_name", val.get(0).toString());
			model.addAttribute("loginInfo_email", val.get(1).toString());
		} else { //세션 값이 없다면
			return "redirect:/";
		}
		
		model.addAttribute("m_detail",mMessageService.messageDetailService(m_num));
		
		return "s_detail";
	}
	
	@RequestMapping("/message_receive_delete/{m_num}")
	private String messageRDelete(@PathVariable int m_num) throws Exception {
		
		mMessageService.messageRecDeleteService(m_num);
		
		return "redirect:/want_council";
	}
	
	@RequestMapping("/message_sender_delete/{m_num}")
	private String messageSDelete(@PathVariable int m_num) throws Exception {
		mMessageService.messageSenDeleteService(m_num);
		
		return "redirect:/want_council/send";
	}
	
	@RequestMapping("/mail/receiver_delete/{mlist}")
	@ResponseBody
	private void checkSDelete(@PathVariable String mlist, Model model) throws Exception {
		
		String[] mnoList = mlist.split("-");
		for(int i=0; i<mnoList.length;i++) {
			System.out.println(mnoList[i]);
		}
		for(int i=0; i<mnoList.length;i++) {
			mMessageService.messageRecDeleteService(Integer.parseInt(mnoList[i]));
		}
	}
	
	@RequestMapping("/mail/sender_delete/{mlist}")
	@ResponseBody
	private void checkRDelete(@PathVariable String mlist, Model model) throws Exception {
		String[] mnoList = mlist.split("-");
		for(int i=0; i<mnoList.length;i++) {
			System.out.println(mnoList[i]);
		}
		for(int i=0; i<mnoList.length;i++) {
			mMessageService.messageSenDeleteService(Integer.parseInt(mnoList[i]));
		}
	}
	
	@RequestMapping("/mail_count")
	@ResponseBody
	public int Mcount(HttpServletRequest request) throws Exception{
		System.out.println(request.getSession().getAttribute("loginInfo_email").toString());
		if (request.getSession().getAttribute("loginInfo_email").toString() != null) {
			return mMessageService.messageNonCountService(request.getSession().getAttribute("loginInfo_email").toString());
		}
		
		return 0;
	}
	
	@RequestMapping("/go_council")
	public String GoCouncil(HttpServletRequest request) throws Exception {
		
		String[] users = new String[3];
		users = mMessageService.messageRanUserService();
		for(int i=0; i<users.length;i++) {
			MessageVO message = new MessageVO();
			message.setSender(request.getSession().getAttribute("loginInfo_email").toString());
			message.setReceiver(users[i]);
			message.setTitle(request.getParameter("title"));
			message.setContent(request.getParameter("content"));
			mMessageService.messageInsertService(message);
		}
		return "redirect:/";
	}
}
