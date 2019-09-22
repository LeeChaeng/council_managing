package com.start.Pcouncil.board.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.start.Pcouncil.board.domain.MemberVO;

@Repository("com.start.Pcouncil.board.mapper.MemberMapper")
public interface MemberMapper {
	public String memExist(String email) throws Exception;
	public String memSelect(String email) throws Exception;
	public void memInsert(MemberVO member) throws Exception;
}
