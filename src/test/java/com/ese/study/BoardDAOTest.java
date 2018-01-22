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
		cri.setPage(15);  			   //3페이지 셋팅
		cri.setPerPageNum(20);  	   //20개 데이터 셋팅
		
		List<BoardVO> list = dao.listCriteria(cri);
		
		for(BoardVO boardVO : list){
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}
	
	// 스프링 MVC의 경우 org.springframework.web.util 패키지에 웹 개발에 필요한 많은 유틸리티 클래스를 제공
	// URI 를 작성할 때 도움이 되는 클래스가 UriComponentsBuilder와 UriComponents 클래스이다
	// UriComponents 클래스는 path나 query에 해당하는 문자열들을 추가해서 원하는 URI를 생성할 때 사용함
	// UriComponentsBuilder 테스트
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
		cri.setKeyword("파우스트");
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
