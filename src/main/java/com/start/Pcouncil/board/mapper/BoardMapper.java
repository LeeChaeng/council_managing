package com.start.Pcouncil.board.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.start.Pcouncil.board.domain.BoardVO;
import com.start.Pcouncil.board.domain.Criteria;

@Repository("com.start.Pcouncil.board.mapper.BoardMapper")
public interface BoardMapper {
	
	//게시글 개수
	public int boardCount() throws Exception;
	
	//게시글 목록
	public List<BoardVO> boardList() throws Exception;
	
	//게시글 페이징
	public List<BoardVO> boardPaging(Criteria cri) throws Exception;
	
	//게시글 상세
	public BoardVO boardDetail(int bno) throws Exception;
	
	//게시글 작성
	public int boardInsert(BoardVO board) throws Exception;
	
	//게시글 수정
	public int boardUpdate(BoardVO board) throws Exception;
	
	//게시글 삭제
	public int boardDelete(int bno) throws Exception;
	
	//게시글 조회수 
	public int boardHit(int bno) throws Exception;
}
