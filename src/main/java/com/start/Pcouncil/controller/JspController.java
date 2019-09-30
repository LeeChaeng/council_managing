package com.start.Pcouncil.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.start.Pcouncil.board.mapper.BoardMapper;

@Controller
public class JspController {
	@RequestMapping("/P_board")
	public String board() {
		return "board";
	}
	
	@Resource(name="com.start.Pcouncil.board.mapper.BoardMapper")
	BoardMapper mBoardMapper;
		
	@RequestMapping("/want")
	private String want() throws Exception {
		return "want";
	}
}
