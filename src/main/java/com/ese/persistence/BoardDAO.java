package com.ese.persistence;

import java.util.List;
import java.util.Map;

import com.ese.domain.BoardVO;
import com.ese.domain.Criteria;
import com.ese.domain.SearchCriteria;

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
	
	public int countPaging(Criteria cri) throws Exception; // ȭ�� �ϴ��� ������ ��ȣ ó��(tocalCount)�b ��ȯ 
	
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception; // �˻��� ����¡ ó��
	
	public int listSearchCount(SearchCriteria cri) throws Exception; // �˻��� ����¡ ó��
	
	public List<BoardVO> excelDown(Map<String, Object> paramMap) throws Exception;
	
	
	
}
