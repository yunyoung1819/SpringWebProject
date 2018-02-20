package com.ese.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import com.ese.domain.BoardVO;
import com.ese.domain.Criteria;
import com.ese.domain.SearchCriteria;
import com.ese.persistence.BoardDAO;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * BoardServiceImpl 
 * BoardService�� ������ Ŭ����
 * 
 * @author Administrator
 * 
 */
//
@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardDAO dao;
	
	@Override
	public void regist(BoardVO board) throws Exception {
		dao.create(board);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		dao.update(board);
	}

	@Override
	public void remove(Integer bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		
		return dao.listCriteria(cri);
	}

	// ȭ�� �ϴ��� ������ ��ȣ ó��
	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		
		return dao.countPaging(cri);
	}

	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		
		return  dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		
		return dao.listSearchCount(cri);
	}
	
	/**
	 * Download Excel File
	 * @Method Name : excelDown
	 * @create Date : 2018. 02. 08.
	 * @made by : Yun Young
	 */
	@Override
	public void excelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FileInputStream fin = null;
		
		try{
			String filePath = request.getSession().getServletContext().getRealPath("/") + "file_uplad_excel"; //파일 경로 지정(없을 경우 자동 생성 있어야함)
			File f = new File(filePath);
			
			boolean result = true;
			if(!f.exists()){
				result = f.mkdir();
			}
			
			if(result){
				System.out.println("excelDown, Folder has been created.");
			}
			
			// Create WorkBook
			String excelTitle = "국내도서 베스트셀러"; //시트 제목, 문서제목
			
			String fileName = UriUtils.encodeFragment(excelTitle+"_"+System.currentTimeMillis(), "UTF-8");
			
			// 엑셀 파일 하나 생성 (WritableWorkbook)
			WritableWorkbook myWorkbook = Workbook.createWorkbook(new File(filePath + File.separator + fileName + ".xls"));
			myWorkbook.setColourRGB(Colour.getInternalColour(61), 218, 238, 243); // 컬러 지정
			
			//엑셀 파일에 시트 생성
			WritableSheet mySheet = myWorkbook.createSheet(excelTitle, 0);
			WritableFont.FontName fontNm = WritableFont.createFont("맑은 고딕"); //맑은 고딕
			// 타이틀! 시트 내용 제일 위에 들어가는 제목
			WritableCellFormat mTitleFormat = new WritableCellFormat(new WritableFont (fontNm, 20, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK, ScriptStyle.NORMAL_SCRIPT));
			// 테이블 헤더
			WritableCellFormat titleFormat = new WritableCellFormat(new WritableFont (fontNm, 9,WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK, ScriptStyle.NORMAL_SCRIPT));  
			// 테이블 바디
			WritableCellFormat dataFormat = new WritableCellFormat(new WritableFont (fontNm, 9,WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK, ScriptStyle.NORMAL_SCRIPT)); // 데이터 셀 포멧 생성
			
			// 타이틀 모양 지정(Alignment)
			mTitleFormat.setAlignment(Alignment.CENTRE); // 셀 가운데 정렬
			mTitleFormat.setVerticalAlignment(VerticalAlignment.CENTRE); // 셀 수직 가운데 정렬
			
			// 테이블 헤더 모양
			titleFormat.setAlignment(Alignment.CENTRE);
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			titleFormat.setBackground(Colour.getInternalColour(61));
			titleFormat.setWrap(true); //자동 줄바꿈 지원
			
			// 테이블 바디 모양
			dataFormat.setAlignment(Alignment.CENTRE);
			dataFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			dataFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			
			//세로줄(열) 0번부터 시작이고, 넓이 지정
			mySheet.setColumnView(0, 15);
			mySheet.setColumnView(1, 15);
			mySheet.setColumnView(2, 20);
			mySheet.setColumnView(3, 20);
			mySheet.setColumnView(4, 20);
			mySheet.setColumnView(5, 20);
			mySheet.mergeCells(0, 0, 12, 0); //mergeCells(int col1, int row1, int col2, int row2)
			
			// 가로줄(행) 0번부터 시작이고, 넓이 지정
			mySheet.setRowView(0, 1000);
			mySheet.setRowView(1, 600);
			
			//테이블 헤더 부분에 들어갈 내용, 형식(열,행)
			mySheet.addCell(new Label(0, 0, "bno", mTitleFormat));
			mySheet.addCell(new Label(0, 1, "title", mTitleFormat));
			mySheet.addCell(new Label(1, 1, "writer", mTitleFormat));
			mySheet.addCell(new Label(2, 1, "regdate", mTitleFormat));
			mySheet.addCell(new Label(3, 1, "viewcnt", mTitleFormat));
			
			BoardVO boardVO;
			
			ArrayList<BoardVO> list = null;
			//list = (ArrayList<BoardVO>) dao.excelDown(request);
			
			//ArrayList<BoardVO> boardVOArrayList = Lists.newArrayList();
			
			for(BoardVO BoardVOList : list){
				boardVO = new BoardVO();
				
				boardVO.setBno(boardVO.getBno());
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}