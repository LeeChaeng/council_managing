package com.start.Pcouncil.board.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MessageVO {
	private int m_num;
	private String receiver;
	private String sender;
	private String title;
	private String content;
	private String send_date;
	private String read_date;
	private boolean receive_del;
	private boolean sent_del;
	private MultipartFile files_0;
	private byte[] addfile;
	
	
	public byte[] getAddfile() {
		return addfile;
	}
	public void setAddfile(byte[] addfile) {
		this.addfile = addfile;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	public String getRead_date() {
		return read_date;
	}
	public void setRead_date(String read_date) {
		this.read_date = read_date;
	}
	public boolean isReceive_del() {
		return receive_del;
	}
	public void setReceive_del(boolean receive_del) {
		this.receive_del = receive_del;
	}
	public boolean isSent_del() {
		return sent_del;
	}
	public void setSent_del(boolean sent_del) {
		this.sent_del = sent_del;
	}
	public MultipartFile getFiles_0() {
		return files_0;
	}
	public void setFiles_0(MultipartFile files_0) {
		this.files_0 = files_0;
	}
	
	
	
}
