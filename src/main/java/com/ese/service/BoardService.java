package com.ese.service;

import java.util.List;

import com.ese.domain.BoardVO;
import com.ese.domain.Criteria;

/**
 * BoardService �������̽�
 * 
 * @since : 2017.10.29
 * @author Yoon Young
 *
 */
public interface BoardService {
	
	public void regist(BoardVO board) throws Exception; // ���
	
	public BoardVO read(Integer bno) throws Exception;  // ��ȸ
	
	public void modify(BoardVO board) throws Exception; // ����
	
	public void remove(Integer bno) throws Exception;   // ����
	
	public List<BoardVO> listAll() throws Exception;    // ��ü��ȸ
	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception; // ����¡ ó���� �޼ҵ� �߰�
	
	public int listCountCriteria(Criteria cri) throws Exception; // ȭ�� �ϴ��� ������ ��ȣ ó��
	
}
