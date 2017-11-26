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
 * BoardDAO �� �׽�Ʈ
 * BoardDAO�� �׽�Ʈ �۾��� jUnit�� ����ؼ� ����
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
		board.setTitle("���ο� ���� �ֽ��ϴ�.");
		board.setContent("���ο� ���� �ֽ��ϴ�.");
		board.setWriter("����");
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
		board.setTitle("������ ���̴�!!");
		board.setContent("���� �׽�Ʈ");
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
		
		Criteria cri = new Criteria(); //Criteria ��ü ����
		cri.setPage(3);  			   //3������ ����
		cri.setPerPageNum(20);  	   //20�� ������ ����
		
		List<BoardVO> list = dao.listCriteria(cri);
		
		for(BoardVO boardVO : list){
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}
}
