package com.start.Pcouncil.board.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.start.Pcouncil.board.domain.Criteria;
import com.start.Pcouncil.board.domain.MessageVO;

@Repository("com.start.Pcouncil.board.mapper.MessageMapper")
public interface MessageMapper {
	
	public int messageRecCount(String receiver) throws Exception;
	public int messageSenCount(String receiver) throws Exception;
	public List<MessageVO> messagePaging(Criteria cri) throws Exception;
	public int messageInsert(MessageVO message) throws Exception;
	public List<MessageVO>  messageList() throws Exception;
	public MessageVO messageDetail(int m_num) throws Exception;
	public int messageUpdate(int m_num) throws Exception;
	public int messageRecDelete(int m_num) throws Exception;
	public int messageSenDelete(int m_num) throws Exception;
	public int messageNonCount(String email) throws Exception;
	public String[] messageRanUser() throws Exception;
}
