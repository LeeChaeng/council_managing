package com.start.Pcouncil.board.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.start.Pcouncil.board.domain.MemberVO;
import com.start.Pcouncil.board.mapper.MemberMapper;

@Service("com.start.Pcouncil.board.service.MemberService")
public class MemberService {
	
	@Resource(name="com.start.Pcouncil.board.mapper.MemberMapper")
	MemberMapper mMemberMapper;
	
	public boolean memExistService(String email) throws Exception {
		if(mMemberMapper.memExist(email)==null)
			return false;
		return true;
	}
	
	public String memSelectService(String email) throws Exception {
		return mMemberMapper.memSelect(email);
	}
	
	public void memInsertService(MemberVO member) throws Exception {
		mMemberMapper.memInsert(member);
	}
}
