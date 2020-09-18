<%@ page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="poly.dto.FreeDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%
	List<FreeDTO> nList = (List<FreeDTO>) request.getAttribute("nList");
	if (nList == null) {
		nList = new ArrayList();
	}
	
	int pgNum = (int) request.getAttribute("pgNum");
	int total = (int) request.getAttribute("total");
	int totalpg = (total - 1) / 10 + 1;
%>
<!DOCTYPE html>
<html>
<head>
<title>[관리페이지] 자유게시물</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="icon" href="/mypage/dist/img/core-img/favicon.ico">
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/adminx.css" media="screen" />
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/common.css" media="screen" />
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/table.css" media="screen" />
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/table.css" media="screen" />

<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 관리페이지 - 자유게시물
	Email: 			1920110016@office.kopo.ac.kr
	//////////////////////////////////////////////////////
-->
<body>

	<div class="adminx-container">

		<!--네비게이션 바 시작-->
		<%@include file="/WEB-INF/view/manager/frame/Topbar.jsp"%>
		<!--네비게이션 바 종료-->

		<!-- Sidebar 영역 시작 -->
		<%@include file="/WEB-INF/view/manager/frame/Sidebar3.jsp"%>
		<!-- Sidebar 종료 -->

		<!-- Main Content 시작 -->
		<div class="adminx-content">
			<div class="adminx-main-content">
				<div class="container-fluid">
					<!-- BreadCrumb -->
					<nav aria-label="breadcrumb" role="navigation">
						<ol class="breadcrumb adminx-page-breadcrumb">
							<li class="breadcrumb-item"><a href="#">Home</a></li>
							<li class="breadcrumb-item"><a href="#">게시판</a></li>
							<li class="breadcrumb-item active  aria-current=page">자유게시물</li>
						</ol>
					</nav>
					<div class="pb-3"></div>

					<!--자유 컨텐츠 영역 시작-->
					<div class="container table-area">
						<div class="row">
							<div class="col-md-12">
								<input type="button" onclick="location.href='/manager/manager_FreeReg.do'" class="btn btn-list" value="글 작성"> 
								<input type="button" onclick="modifyDel()" class="btn btn-list" value="삭제">
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="table table-striped" style="width: 100%">
									<div>
										<div class="row">
											<div class="col-md-1 cell-1 cell-top">선택</div>
											<div class="col-md-1 cell-2 cell-top">No</div>
											<div class="col-md-4 cell-3 cell-top">제목</div>
											<div class="col-md-2 cell-4 cell-top">작성자</div>
											<div class="col-md-2 cell-5 cell-top">작성일</div>
											<div class="col-md-2 cell-6 cell-top">조회수</div>
										</div>
									</div>
									<div>
										<%
											for (FreeDTO nDTO : nList) {
										%>
										<%
											String title = CmmUtil.nvl(nDTO.getTitle());
											String chg_dt = CmmUtil.nvl(nDTO.getChg_dt());
										%>
										<div class="row">
											<div class="col-md-1 cell-1">
												<input type="radio" name="chk" value="<%=CmmUtil.nvl(nDTO.getFree_seq()) %>">
											</div>
											<div class="col-md-1 cell-2"><%=CmmUtil.nvl(nDTO.getFree_seq()) %></div>
											<div class="col-md-4 cell-3">
												<a href="/manager/board_free_detail.do?free_seq=<%=CmmUtil.nvl(nDTO.getFree_seq()) %>"><%=title%></a>
											</div>
											<div class="col-md-2 cell-4"><%=CmmUtil.nvl(nDTO.getUser_id()) %></div>
											<div class="col-md-2 cell-5"><%=chg_dt %></div>
											<div class="col-md-2 cell-6"><%=CmmUtil.nvl(nDTO.getRead_cnt()) %></div>
										</div>
										<%
											}
										%>
									</div>
									<br>
								</div>
								<div class="container">
									<div class="row justify-content-center">
										<div class="#">
											<nav>
												<ul class="pagination">
													<li class="#">
														<% 
															if (pgNum == 1) {
														%> 
															<a href="#">&lt;</a> 
														<%
														 	} else {
	 													%> 
	 														<a href="/manager/board_free.do?pgNum=<%=pgNum - 1%>">&lt;</a> 
	 													<% 
	 														}
	 													%>
													</li>
													<li class="#">
														<%
															if (totalpg < 5) {
														%> 
														<%
	 														for (int i = 1; i <= totalpg; i++) {
	 													%> 
	 													<%
	 														if (i == pgNum) {
	 													%>
													
														<li class="#"><span><%=pgNum%></span></li>
													<%
														} else {
													%>
													<li class="#">
														<a href="/manager/board_free.do?pgNum=<%=i%>"><%=i%></a>
													</li>
													<%
														}
													%>
													<%
														}
													%>
													<%
														} else {
													%>
													<%
														if (pgNum < 4) {
													%>
													<%
														for (int i = 1; i < 6; i++) {
													%>
													<%
														if (i == pgNum) {
													%>
													<li class="#"><span><%=pgNum%></span></li>
													<%
														} else {
													%>
													<li class="#">
														<a href="/manager/board_free.do?pgNum=<%=i%>"><%=i%></a>
													</li>
													<%
														}
													%>
													<%
														}
													%>
													<%
														} else {
													%>
													<%
														for (int i = pgNum - 2; i < pgNum + 3; i++) {
													%>
													<%
														if (i == pgNum) {
													%>
													<li class="#"><span><%=pgNum%></span></li>
													<%
														} else {
													%>
													<li class="#">
														<a href="/manager/board_free.do?pgNum=<%=i%>"><%=i%></a>
													</li>
													<%
														}
													%>
													<%
														}
													%>
													<%
														}
													%>
													<%
														}
													%>
													</li>
													<li class="#">
														<%
															if (pgNum == totalpg) {
														%> 
														<a href="#">&gt;</a> 
														<%
	 														} else {
	 													%>
														<a href="/manager/board_free.do?pgNum=<%=pgNum + 1%>">&gt;</a>
														<%
															}
														%>
													</li>
												</ul>
											</nav>
										</div>
									</div>
								</div>							
							</div>
						</div>
					</div>
					<!--자유 컨텐츠 영역 종료-->
				</div>
			</div>
		</div>
		<!-- Main Content 종료 -->
	</div>
	<!-- If you prefer jQuery these are the required scripts -->
	<!--Common_script 시작-->
	<%@include file="/WEB-INF/view/manager/frame/Common_script.jsp"%>
	<!--Common_script 종료-->

	<script>
		$(document).ready(function() {
			var table = $('[data-table]').DataTable({
				"columns" : [ null, null, null, null, null, {
					"orderable" : false
				} ]
			});
		});
	</script>

	<script type="text/javascript">
		function button_event() {
			if (confirm("삭제하시겠습니까?") == true) { //확인
				document.form.submit();
			} else { //취소
				return;
			}
		}

		function modifyDel() {

			if (confirm("삭제하시겠습니까?") == true) {
				document.location.href = "/manager/manager_freeDelete.do?free_seq="
						+ $("input:radio[name=chk]:checked").val();
			} else {
				return false;
			}
		}
	</script>


</body>
</html>