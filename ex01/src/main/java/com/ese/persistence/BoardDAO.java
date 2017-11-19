package com.ese.persistence;

import java.util.List;

import com.ese.domain.BoardVO;

/**
 * 6. BoardDAO 인터페이스 생성
 */
public interface BoardDAO {

	public void create(BoardVO vo) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public void delete(Integer bno) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
}
