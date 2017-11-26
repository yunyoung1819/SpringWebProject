package com.ese.study;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ese.domain.BoardVO;
import com.ese.domain.Criteria;
import com.ese.persistence.BoardDAO;

/**
 * BoardDAO 의 테스트
 * BoardDAO의 테스트 작업은 jUnit을 사용해서 진행
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {
	
	@Inject
	private BoardDAO dao;

	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Test
	public void testCreate() throws Exception {
		
		BoardVO board = new BoardVO();
		board.setTitle("새로운 글을 넣습니다.");
		board.setContent("새로운 글을 넣습니다.");
		board.setWriter("수지");
		dao.create(board);
	}
	
	@Test
	public void testRead() throws Exception {
		
		logger.info(dao.read(1).toString());
	}
	
	@Test
	public void testUpdate() throws Exception {
		
		BoardVO board = new BoardVO();
		board.setBno(2);
		board.setTitle("수정된 글이다!!");
		board.setContent("수정 테스트");
		dao.update(board);
	}
	
	@Test
	public void testDelete() throws Exception {
		
		dao.delete(2);
	}
	
	@Test
	public void testListPage() throws Exception {
		
		int page=3;
		
		List<BoardVO> list = dao.listPage(page);
		
		for(BoardVO boardVO : list){
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}
	
	@Test
	public void testListCriteria() throws Exception {
		
		Criteria cri = new Criteria(); //Criteria 객체 생성
		cri.setPage(3);  			   //3페이지 셋팅
		cri.setPerPageNum(20);  	   //20개 데이터 셋팅
		
		List<BoardVO> list = dao.listCriteria(cri);
		
		for(BoardVO boardVO : list){
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}
}
