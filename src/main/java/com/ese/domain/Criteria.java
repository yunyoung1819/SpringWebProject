package com.ese.domain;

public class Criteria {

	private int page;  		//페이지
	private int perPageNum;	//화면당 페이지 개수
	
	public Criteria(){		//생성자, 값 초기화
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public void setPage(int page){  //페이지 값을 셋팅
		
		if(page <= 0){		//page값이 0이거나 0보다 작을 때 1페이지를 셋팅
			this.page = 1;
			return;
		}
		
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum){	//페이지당 개수를 셋팅
		
		if(perPageNum <= 0 || perPageNum > 100){	//페이지당 개수가 0보다 이하이거나 페이지당 개수가 100개 이상일 때
			this.perPageNum = 10;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
	public int getPage(){
		return page;
	}
	
	//method for myBatis SQL Mapper
	public int getPageStart(){	//limit 20, 10 구문에서 시작 위치를 지정할 때 사용함
		
		return (this.page -1) * perPageNum;  //시작 데이터번호 = (페이지 번호 -1) * 페이지당 보여지는 개수
	}
	
	//method for myBatis SQL Mapper
	public int getPerPageNum(){	//한 페이지당 보여지는 개수
		
		return this.perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
}
