package com.ese.persistence;

import java.util.List;

import com.ese.domain.BoardVO;
import com.ese.domain.Criteria;

/**
 * 6. BoardDAO �������̽� ����
 */
public interface BoardDAO {

	public void create(BoardVO vo) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public void delete(Integer bno) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listPage(int page) throws Exception; //BoardDAO �������̽��� ����¡ ó���� ���õ� ����� �߰�
	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception; 
	
}
