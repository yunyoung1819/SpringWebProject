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
			
			<form role="form"  action="modifyPage" method="post"> 				<!-- action : 폼을 전송할 서버 쪽 스크립트 파일을 지정합니다. -->
				<input type='hidden' name='bno' value="${boardVO.bno}"> 		<!-- 게시물 번호 -->
				<input type='hidden' name='page' value="${cri.page}">			<!-- 현재 조회하는 페이지 번호 -->
				<input type='hidden' name='perPageNum' value="${cri.perPageNum}"> <!-- 한 페이지당 출력하는 데이터 개수 -->
				<input type='hidden' name='searchType' value="${cri.searchType}"><!-- 검색 타입 -->
				<input type='hidden' name='keyword' value="${cri.keyword }"><!-- 검색 단어 -->
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
			</div>
		</div>
	</div>
	
	<!-- 댓글 등록 부분 -->
	<!-- 댓글 등록에 필요한 <div> -->
	<div class="row">
		<div class="col-md-12">
			<div class="box box-success">
				<div class="box-header">
					<h3 class="box-title">ADD NEW REPLY</h3>
				</div>
				<div class="box-body">
					<label for="newReplyWriter">Writer</label>
					<input class="form-control" type="text" placeholder="USER ID" id="newReplyWriter">
					<label for="newReplyText">ReplyText</label>
					<input class="form-control" type="text" placeholder="REPLY TEXT" id="newReplyText">				
				</div>
				<!-- /.box-body -->
				<div class="box-footer">
					<button type="submit" class="btn btn-primary" id="replyAddBtn">ADD REPLY</button>
				</div>
			</div>
			
			<!-- Time line -->
			<ul class="timeline">
				<li class="time-label" id="repliesDiv"><span class="bg-green">Replies List</span></li>
			</ul>
			
			<div class='text-center'>
				<ul id="pagination" class="paginateion pagination-sm no-margin">
				</ul>
			</div>
		</div>
	</div>
</section>
<!-- /.content-wrapper -->

<script>
$(document).ready(function(){
	
	var formObj = $("form[role='form']");
	
	console.log("formObj: ", formObj);
	
	// 수정 버튼
	$(".btn-warning").on("click", function(){
		formObj.attr("action", "/sboard/modifyPage");
		formObj.attr("method", "get");
		formObj.submit();
	});
	
	// 삭제 버튼
	$(".btn-danger").on("click", function(){
		formObj.attr("action", "/sboard/removePage");
		formObj.submit();
	});
	
	// 목록 버튼 
	$(".btn-primary").on("click", function(){
		formObj.attr("method", "get");
		formObj.attr("action", "/sboard/list");
		formObj.submit();
	});
	
});
</script>

<!-- Handlebars를 사용하는 템플릿 코드 -->
<script id="template" type="text/x-handlebars-template">
{{#each .}}
<li class="replyLi" data-rno={{rno}}>
<i class="fa fa-comments bg-blue"></i>
	<div class="timeline-item">
		<span class="time">
			<i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
		</span>
		<h3 class="timeline-header><strong>{{rno}}</strong> -{{replyer}}</h3>
		<div class="timeline-body">{{replytext}}</div>
			<div class="timeline-footer">
				<a class-"btn btn-primary btn-xs"
					data-toggle="modal" data-target="#modifyModal"> Modify </a>
			</div>
	</div>
</li>
{{/each}}
</script>

<!-- prettifyDate regdate 에 대한 Javascript의 처리 -->
<script>
	Handlebars.registerHelper("prettifyDate", function(timeValue){
		
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		var date = dateObj.getDate();
		return year + "/" + month + "/" + date;
	});
	
	var printData = function(replyArr, target, templateObject){
		
		var template = Handlebars.compile(templateObject.html());
		
		var html = template(replyArr);
		$(".replyLi").remove();
		target.after(html);
	}
</script>

<script>
	var bno = ${boardVO.bno}
	var replyPage = 1;
	
	function getPage(pageInfo){
		
		$.getJSON(pageInfo, function(data){
			printData(data.list, $("#repliesDiv"), $('#template'));
			printPaging(data.pageMaker, $(".pagination"));
		});
	}
	
	
</script>
<%@include file="../include/footer.jsp"%>
