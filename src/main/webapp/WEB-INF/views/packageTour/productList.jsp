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
					<h3 class="box-title">패키지 여행 상품</h3>
				</div>
				<!-- ./box-header -->
			
				<div class="box-body">
				<table border="1">
					<tr>
						<th>상품 ID 번호</th>
						<th>상품 이미지</th>
						<th>상품명</th>
						<th>가격</th>
					</tr>
					<c:forEach var="row" item="${list }">
					<tr>
						<td>
						</td>
					</tr>
					</c:forEach>
				</table>	
				</div>
				<!-- /.box-body -->
				
			</div>
		</div>
	</div>
	
</section>
<!-- /.content-wrapper -->

<%@include file="../include/footer.jsp"%>
