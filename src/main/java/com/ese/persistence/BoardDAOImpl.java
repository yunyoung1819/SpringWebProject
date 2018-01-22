package com.ese.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ese.domain.BoardVO;
import com.ese.domain.Criteria;
import com.ese.domain.SearchCriteria;

/**
 * BoardDAO 인터페이스를 구현한 BoardDAOImpl
 * 
 * @since : 2017.10.20
 * @author Administrator
 *
 */

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession session;

	private static String namespace="com.ese.mapper.BoardMapper";
	
	@Override
	public void create(BoardVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return session.selectOne(namespace + ".read", bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(Integer bno) throws Exception {
		session.delete(namespace + ".delete", bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return session.selectList(namespace + ".listAll");
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		
		if(page <= 0){  //page가 0이거나 0보다 작을 때 
			page = 1;
		}
		
		page = (page - 1 ) * 10;
		
		return session.selectList(namespace + ".listPage", page);
	}

	// Criteria
	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		
		return session.selectList(namespace + ".listCriteria", cri); 
	}

	// 화면 하단의 페이지 번호 처리를 위한 Total Count 반환
	@Override
	public int countPaging(Criteria cri) throws Exception {
		
		return session.selectOne(namespace + ".countPaging", cri);
	}

	// 검색 및 페이징 처리
	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listSearchCount", cri);
	}
}
