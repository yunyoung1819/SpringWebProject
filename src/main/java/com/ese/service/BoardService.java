package com.ese.service;

import java.util.List;

import com.ese.domain.BoardVO;

/**
 * BoardService 인터페이스
 * 
 * @since : 2017.10.29
 * @author Yoon Young
 *
 */
public interface BoardService {
	
	public void regist(BoardVO board) throws Exception; //등록
	
	public BoardVO read(Integer bno) throws Exception;  //조회
	
	public void modify(BoardVO board) throws Exception; //수정
	
	public void remove(Integer bno) throws Exception;   //삭제
	
	public List<BoardVO> listAll() throws Exception;    //전체조회
	
}
