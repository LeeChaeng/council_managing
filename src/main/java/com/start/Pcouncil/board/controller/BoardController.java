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
import org.springframework.web.servlet.ModelAndView;

import com.start.Pcouncil.board.domain.BoardVO;
import com.start.Pcouncil.board.domain.Criteria;
import com.start.Pcouncil.board.domain.PageMaker;
import com.start.Pcouncil.board.service.BoardService;
import com.start.Pcouncil.common.CommonUtils;

@Controller
public class BoardController {

	@Resource(name="com.start.Pcouncil.board.service.BoardService")
	BoardService mBoardService;
	
	//게시판 리스트 화면 호출
	@RequestMapping("/list")
	private String boardList(@ModelAttribute("cri") Criteria cri, Model model,HttpServletRequest request) throws Exception {
		System.out.println(cri.toString());
		
		boolean isSession = CommonUtils.isSession(request);
		
		
		if (isSession) { //세션 값이 있다면
			List val = new ArrayList();
			val = CommonUtils.returnSession(request);
			model.addAttribute("loginInfo_name", val.get(0).toString());
			model.addAttribute("loginInfo_email", val.get(1).toString());
		} else { //세션 값이 없다면
			return "redirect:/";
		}
		
		model.addAttribute("list", mBoardService.boardPagingService(cri));
		model.addAttribute("count", mBoardService.boardCountService());
		
		//페이징 처리부,,인가,,
		PageMaker page = new PageMaker();
		page.setCri(cri);
		page.setTotalCount(mBoardService.boardCountService());
		
		model.addAttribute("pageMaker", page);
		return "list"; 	//list.jsp호출
	}
	
	@RequestMapping("/detail/{bno}")
	private String boardDetail(@PathVariable int bno, Model model,HttpServletRequest request) throws Exception {
		boolean isSession = CommonUtils.isSession(request);
		
		
		if (isSession) { //세션 값이 있다면
			List val = new ArrayList();
			val = CommonUtils.returnSession(request);
			model.addAttribute("loginInfo_name", val.get(0).toString());
			model.addAttribute("loginInfo_email", val.get(1).toString());
		} else { //세션 값이 없다면
			return "redirect:/";
		}
		
		model.addAttribute("detail", mBoardService.boardDetailService(bno));
		model.addAttribute("update", mBoardService.boardHitService(bno));
		
		return "detail";
	}
	
	//게시글 작성 폼 호출
	@RequestMapping("/insert")
	private ModelAndView boardInsertForm(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("writer", request.getSession().getAttribute("loginInfo_name"));
		mv.setViewName("insert");
		return mv;
	}
	
	
	@RequestMapping("/insertProc")
	private String boardInsertProc(HttpServletRequest request) throws Exception {
		boolean isSession = CommonUtils.isSession(request);
		
		
		if (isSession) { //세션 값이 있다면
			List val = new ArrayList();
			val = CommonUtils.returnSession(request);
		} else { //세션 값이 없다면
			return "redirect:/";
		}
		
		
		BoardVO board = new BoardVO();
		
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setWriter(request.getSession().getAttribute("loginInfo_name").toString());
		
		mBoardService.boardInsertService(board);
		
		return "redirect:/list";
	}
	
	
	//게시글 수정 폼 호출
	@RequestMapping("/update/{bno}")
	private String boardUpdateForm(@PathVariable int bno, Model model) throws Exception {
		
		model.addAttribute("detail", mBoardService.boardDetailService(bno));
		
		return "update";
	}
	
	@RequestMapping("/updateProc")
	private String boardUpdateProc(HttpServletRequest request) throws Exception{
		
		BoardVO board = new BoardVO();
		
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setBno(Integer.parseInt(request.getParameter("bno")));
		
		mBoardService.boardUpdateService(board);
		
		return "redirect:/detail/" + request.getParameter("bno");
	}
	
	@RequestMapping("/delete/{bno}")
	private String boardDelete(@PathVariable int bno) throws Exception {
		
		mBoardService.boardDeleteService(bno);
		
		return "redirect:/list";
	}
}
