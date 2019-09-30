package com.start.Pcouncil.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class CommonUtils {
	public static boolean isSession(HttpServletRequest request) {
		
		
		//세션 값(=loginInfo_name)이 null 인지 확인 -> 로그인 되어있는지 확인
		if(request.getSession().getAttribute("loginInfo_name") == null) {
			return false;
		}
		return true;
	}
	public static List returnSession(HttpServletRequest request) {
		List slist = new ArrayList();
		
		slist.add(request.getSession().getAttribute("loginInfo_name"));
		slist.add(request.getSession().getAttribute("loginInfo_email"));

		return slist;
	}
}
