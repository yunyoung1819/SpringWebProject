package com.ese.service;

import java.util.List;

import com.ese.domain.BoardVO;

/**
 * BoardService �������̽�
 * 
 * @since : 2017.10.29
 * @author Yoon Young
 *
 */
public interface BoardService {
	
	public void regist(BoardVO board) throws Exception; //���
	
	public BoardVO read(Integer bno) throws Exception;  //��ȸ
	
	public void modify(BoardVO board) throws Exception; //����
	
	public void remove(Integer bno) throws Exception;   //����
	
	public List<BoardVO> listAll() throws Exception;    //��ü��ȸ
	
}
