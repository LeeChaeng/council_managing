package com.start.Pcouncil.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.start.Pcouncil.board.domain.BoardVO;
import com.start.Pcouncil.board.domain.Criteria;
import com.start.Pcouncil.board.mapper.BoardMapper;

@Service("com.start.Pcouncil.board.service.BoardService")
public class BoardService {
	
	@Resource(name="com.start.Pcouncil.board.mapper.BoardMapper")
	BoardMapper mBoardMapper;
	
	public int boardCountService() throws Exception {
		return mBoardMapper.boardCount();
	}
	
	public List<BoardVO> boardListService() throws Exception {
		return mBoardMapper.boardList();
	}
	
	public List<BoardVO> boardPagingService(Criteria cri) throws Exception {
		return mBoardMapper.boardPaging(cri);
	}
	
	public BoardVO boardDetailService(int bno) throws Exception {
		return mBoardMapper.boardDetail(bno);
	}
	
	public int boardInsertService(BoardVO board) throws Exception {
		
		return mBoardMapper.boardInsert(board);
	}
	
	public int boardUpdateService(BoardVO board) throws Exception {
		
		return mBoardMapper.boardUpdate(board);
	}
	
	public int boardDeleteService(int bno) throws Exception {
		
		return mBoardMapper.boardDelete(bno);
	}
	
	public int boardHitService(int bno) throws Exception {
		
		return mBoardMapper.boardHit(bno);
	}
}
