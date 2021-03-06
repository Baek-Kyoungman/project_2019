<%@ page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="poly.dto.KidInfoDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%
	List<KidInfoDTO> nList = (List<KidInfoDTO>) request.getAttribute("nList");
	if (nList == null) {
		nList = new ArrayList();
	}
	
	int pgNum = (int) request.getAttribute("pgNum");
	int total = (int) request.getAttribute("total");
	int totalpg = (total - 1) / 10 + 1;
	
	KidInfoDTO f = new KidInfoDTO();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>[관리페이지] 유치원 관리</title>
<meta charset="utf-8">
<link rel="icon" href="/mypage/dist/img/core-img/favicon.ico">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/adminx.css" media="screen" />
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/common.css" media="screen" />
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/table.css" media="screen" />

<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 관리페이지 - 유치원관리
	Email: 			1920110016@office.kopo.ac.kr
	//////////////////////////////////////////////////////
-->
<body>

	<div class="adminx-container">

		<!--네비게이션 바 시작-->
		<%@include file="/WEB-INF/view/manager/frame/Topbar.jsp"%>
		<!--네비게이션 바 종료-->

		<!-- Sidebar 영역 시작 -->
		<%@include file="/WEB-INF/view/manager/frame/Sidebar4.jsp"%>
		<!-- Sidebar 종료 -->

		<!-- Main Content -->
		<div class="adminx-content">
			<div class="adminx-main-content">
				<div class="container-fluid">
					<!-- BreadCrumb -->
					<nav aria-label="breadcrumb" role="navigation">
						<ol class="breadcrumb adminx-page-breadcrumb">
							<li class="breadcrumb-item"><a href="#">Home</a></li>
							<li class="breadcrumb-item"><a href="#">유치원</a></li>
							<li class="breadcrumb-item active  aria-current=page">유치원 관리</li>
						</ol>
					</nav>
					<div class="pb-3"></div>

					<!--리뷰 컨텐츠 영역 시작-->
					<div class="container table-area">
						<div class="row">
							<div class="col-md-12">
								<input type="button" onclick="location.href='/manager/manager_kid_reg.do'" class="btn btn-list" value="유치원 등록"> 
								<input type="button" onclick="modifyDel()" class="btn btn-list" value="삭제">
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="table table-striped" style="width: 100%">
									<div>
										<div class="row">
											<div class="col-md-1 cell-1 cell-top">선택</div>
											<div class="col-md-1 cell-1 cell-top">No</div>
											<div class="col-md-4 cell-1 cell-top">유치원명</div>
											<div class="col-md-4 cell-1 cell-top">주소</div>
											<div class="col-md-2 cell-1 cell-top">개원일</div>
										</div>
									</div>
									<div>
										<%
											for (KidInfoDTO nDTO : nList) {
										%>
										<div class="row">
											<div class="col-md-1 cell-1"><input type="radio" name="chk" value="<%=CmmUtil.nvl(nDTO.getKid_seq())%>"></div>
											<div class="col-md-1 cell-2"><%=CmmUtil.nvl(nDTO.getKid_seq())%></div>
											<div class="col-md-4 user-cell-3">
												<a href="/manager/manager_kid_detail.do?kid_seq=<%=CmmUtil.nvl(nDTO.getKid_seq())%>"><%=CmmUtil.nvl(nDTO.getKindername())%></a>
											</div>
											<div class="col-md-4 cell-4"><%=CmmUtil.nvl(nDTO.getAddr()) %></div>
											<div class="col-md-2 cell-5"><%=CmmUtil.nvl(nDTO.getOdate()) %></div>
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
	 														<a href="/manager/manager_kid.do?pgNum=<%=pgNum - 1%>">&lt;</a> 
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
														<a href="/manager/manager_kid.do?pgNum=<%=i%>"><%=i%></a>
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
														<a href="/manager/manager_kid.do?pgNum=<%=i%>"><%=i%></a>
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
														<a href="/manager/manager_kid.do?pgNum=<%=i%>"><%=i%></a>
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
														<a href="/manager/manager_kid.do?pgNum=<%=pgNum + 1%>">&gt;</a>
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
					<!--리뷰 컨텐츠 영역 종료-->
				</div>
			</div>
		</div>
		<!-- // Main Content -->
	</div>

	<!--Common_script 시작-->
	<%@include file="/WEB-INF/view/manager/frame/Common_script.jsp"%>
	<!--Common_script 종료-->


	<script type="text/javascript">
		function modifyDel() {
			//alert($("input:radio[name=userCheck]:checked").val());
	
			if (confirm("삭제하시겠습니까?") == true) {
				document.location.href = "/manager/manager_kid_Delete.do?kid_seq=" + $("input:radio[name=chk]:checked").val();
			} else {
				return false;
			}
		}
	</script>
</body>
</html>