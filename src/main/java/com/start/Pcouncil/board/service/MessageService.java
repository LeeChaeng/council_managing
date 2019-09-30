package com.start.Pcouncil.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.start.Pcouncil.board.domain.Criteria;
import com.start.Pcouncil.board.domain.MessageVO;
import com.start.Pcouncil.board.mapper.MessageMapper;

@Service("com.start.Pcouncil.board.service.MessageService") 
public class MessageService {
	
	@Resource (name="com.start.Pcouncil.board.mapper.MessageMapper")
	MessageMapper mMessageMapper;
	
	public int messageRecCountService(String receiver) throws Exception {
		return mMessageMapper.messageRecCount(receiver);
	}
	
	public int messageSenCountService(String receiver) throws Exception {
		return mMessageMapper.messageSenCount(receiver);
	}
	
	public List<MessageVO> messagePagingService(Criteria cri) throws Exception {
		return mMessageMapper.messagePaging(cri);
	}
	
	public int messageInsertService(MessageVO message) throws Exception {
		return mMessageMapper.messageInsert(message);
	}
	
	public List<MessageVO>  messageListService() throws Exception {
		return mMessageMapper.messageList();
	}
	
	public MessageVO messageDetailService(int m_num) throws Exception {
		return mMessageMapper.messageDetail(m_num);
	}
	
	public int messageUpdateService(int m_num) throws Exception {
		return mMessageMapper.messageUpdate(m_num);
	}
	
	public int messageRecDeleteService(int m_num) throws Exception {
		return mMessageMapper.messageRecDelete(m_num);
	}
	
	public int messageSenDeleteService(int m_num) throws Exception {
		return mMessageMapper.messageSenDelete(m_num);
	}
	
	public int messageNonCountService(String email) throws Exception {
		return mMessageMapper.messageNonCount(email);
	}
	
	public String[] messageRanUserService() throws Exception {
		return mMessageMapper.messageRanUser();
	}
}
