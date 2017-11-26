package com.ese.persistence;

import java.util.List;

import com.ese.domain.BoardVO;
import com.ese.domain.Criteria;

/**
 * 6. BoardDAO 인터페이스 생성
 */
public interface BoardDAO {

	public void create(BoardVO vo) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public void delete(Integer bno) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listPage(int page) throws Exception; //BoardDAO 인터페이스에 페이징 처리에 관련된 기능을 추가
	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception; 
	
}
