<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../include/header.jsp" %>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!--  general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<!-- ./box-header -->
			
			<form role="form" action="modifyPage" method="post"> <!-- action : 폼을 전송할 서버 쪽 스크립트 파일을 지정합니다. -->
				<input type='hidden' name='bno' value="${boardVO.bno}"> <!-- 게시물 번호 -->
				<input type='hidden' name='page' value="${cri.page}">	<!-- 현재 조회하는 페이지 번호 -->
				<input type='hidden' name='perPageNum' value="${cri.perPageNum}"> <!-- 한 페이지당 출력하는 데이터 개수 -->
			</form>
			
				<div class="box-body">
					<div class="form-group">
						<lable for="exampleInputEmail1">Title</lable>
						<input type="text" name="title" class="form-control" value="${boardVO.title}" readonly="readonly">
					</div>
					<div class="form-group">
						<lable for="exampleInputPassword1">Content</lable>
						<textarea class="form-control" name="content" rows="3" readonly="readonly">${boardVO.content}</textarea>
					</div>
					<div class="form-group">
						<lable for="exampleInputEmail1">Writer</lable>
						<input type="text" name="writer" class="form-control" value="${boardVO.writer}" readonly="readonly">
					</div>
				</div>
				<!-- /.box-body -->
				
				<div class="box-footer">
					<button type="submit" class="btn btn-warning">수정</button>
					<button type="submit" class="btn btn-danger">삭제</button>
					<button type="submit" class="btn btn-primary">목록</button>
				</div>
				
<script>
$(document).ready(function(){
	
	var formObj = $("form[role='form']");
	
	console.log("formObj: ", formObj);
	
	// 수정 버튼
	$(".btn-warning").on("click", function(){
		formObj.attr("action", "/board/modifyPage");
		formObj.attr("method", "get");
		formObj.submit();
	});
	
	// 삭제 버튼
	$(".btn-danger").on("click", function(){
		formObj.attr("method", "post")
		formObj.attr("action", "/board/removePage");
		formObj.submit();
	});
	
	// 목록 버튼 
	$(".btn-primary").on("click", function(){
		formObj.attr("method", "get");
		formObj.attr("action", "/board/listPage");
		formObj.submit();
	});
	
});
</script>
			</div>
		</div>
	</div>
</section>
</div>
<!-- /.content-wrapper -->

<%@include file="../include/footer.jsp"%>
