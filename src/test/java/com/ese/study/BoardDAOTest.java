package com.ese.study;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ese.domain.BoardVO;
import com.ese.domain.Criteria;
import com.ese.domain.SearchCriteria;
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
		cri.setPage(15);  			   //3������ ����
		cri.setPerPageNum(20);  	   //20�� ������ ����
		
		List<BoardVO> list = dao.listCriteria(cri);
		
		for(BoardVO boardVO : list){
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}
	
	// ������ MVC�� ��� org.springframework.web.util ��Ű���� �� ���߿� �ʿ��� ���� ��ƿ��Ƽ Ŭ������ ����
	// URI �� �ۼ��� �� ������ �Ǵ� Ŭ������ UriComponentsBuilder�� UriComponents Ŭ�����̴�
	// UriComponents Ŭ������ path�� query�� �ش��ϴ� ���ڿ����� �߰��ؼ� ���ϴ� URI�� ������ �� �����
	// UriComponentsBuilder �׽�Ʈ
	@Test
	public void testURI() throws Exception{
		
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.path("/board/read")
				.queryParam("bno", 13)
				.queryParam("perPageNum", 20)
				.build();
		
		//logger.info("/board/read?bno=13&perPageNum=20");
		logger.info(uriComponents.toString());
	}
	
	@Test
	public void testURI2() throws Exception{
		
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.path("/{module}/{page}")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build()
				.expand("board", "read")
				.encode();
		
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
		
	}
	
	@Test
	public void testDynamic1() throws Exception {
		
		SearchCriteria cri = new SearchCriteria();
		
		cri.setPage(1);
		cri.setKeyword("�Ŀ콺Ʈ");
		cri.setSearchType("t");
		
		logger.info("======================");
		
		List<BoardVO> list = dao.listSearch(cri);
		
		for(BoardVO boardVO : list){
			logger.info(boardVO.getBno() + ": " + boardVO.getTitle());
		}
		
		logger.info("=====================");
		
		logger.info("COUNT: " + dao.listSearchCount(cri));
	}
}
