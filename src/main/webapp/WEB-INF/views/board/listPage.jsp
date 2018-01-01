<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<%@include file="../include/header.jsp"%>

<!-- Main content -->
<section class="content">
	<div class="row">
	<!-- left column -->
	<div class="col-md-12">
		<!-- general form elements -->
		<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">LIST PAGING</h3>
				</div>
				<div class="box-body">
				<table class="table table-bordered">
					<tr>
						<th style="width: 10px">BNO</th>
						<th>TITLE</th>
						<th>WRITER</th>
						<th>REGDATE</th>
						<th style="width: 40px">VIEWCNT</th>
					</tr>
					
					<!-- BoardController에서 전달받은 list를 화면에 출력하는 코드 -->
					<c:forEach items="${list}" var="boardVO">
						<tr>
							<td>${boardVO.bno}</td>
							<td>
								<a href='/board/readPage${pageMaker.makeQuery(pageMaker.cri.page)}&bno=${boardVO.bno}'>
									${boardVO.title}
								</a>
							</td>
							<td>${boardVO.writer}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" 
									value="${boardVO.regdate}" />
							</td>
							<td><span class="badege bg-red">${boardVO.viewcnt}</span></td>
						</tr>
					</c:forEach>
																
				</table>
				</div>
				<!-- /.box-body -->
				<div class="box-footer">
					<div class="text-center">
						<ul class="pagination">
							
							<c:if test="${pageMaker.prev}">
								<li>
									<a href="listPage${pageMaker.makeQuery(pageMaker.startPage -1 )}">&laquo;</a>
								</li>
							</c:if>
							
							<c:forEach begin="${pageMaker.startPage}"
								end="${pageMaker.endPage}" var="idx">
								<li
									<c:out value="${pageMaker.cri.page == idx?'class=active':''}"/>>
									<a href="listPage${pageMaker.makeQuery(idx)}">${idx}</a>
								</li>
							</c:forEach>
							
							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li>
									<a href="listPage${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a>
								</li>
							</c:if>
							
						</ul>
					</div>
					
					<div class="text-center">
						<ul class="pagination">
						
							
						</ul>
					</div>
					
				</div>
				<!-- /.box-footer-->
			</div>
		</div>
		<!--/.col (left) -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<!-- JavaScript를 이용하는 링크의 처리 : 링크에는 단순히 페이지 번호만을 넣고, 모든 것은 <form>과 Javascript를 이용해서 처리 -->
<!-- <form id="jobForm">
	<input type ='hidden' name="page" value=${pageMaker.cri.perPageNum}>
	<input type ='hidden' name="perPageNum" value=${pageMaker.cri.perPageNum}>
</form> -->

<script>
	var result = '${msg}';
	
	if(result == 'SUCCESS'){
		alert("처리가 완료되었습니다.");
	}
	
	/* $(".pagination li a").on("click", function(event){
		
		event.preventDefault();
		
		var targetPage = $(this).attr("href");
		
		var jobForm = $("#jobForm");
		jobForm.find("[name='page']").val(targetPage);
		jobForm.attr("action", "/board/listPage").attr("method", "get");
		jobForm.submit();
	});	 */
	
</script>

<%@include file="../include/footer.jsp"%>
