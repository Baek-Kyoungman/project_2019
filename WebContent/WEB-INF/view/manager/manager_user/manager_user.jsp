<%@page import="poly.util.CmmUtil"%>
<%@page import="poly.util.EncryptUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="poly.dto.UserInfoDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%
	List<UserInfoDTO> uList = (List<UserInfoDTO>) request.getAttribute("uList");
	if (uList == null) {
		uList = new ArrayList();
	}
	
	int pgNum = (int) request.getAttribute("pgNum");
	int total = (int) request.getAttribute("total");
	int totalpg = (total - 1) / 10 + 1;
	
	UserInfoDTO f = new UserInfoDTO();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>[관리페이지] 회원관리</title>
<meta charset="utf-8">
<link rel="icon" href="/mypage/dist/img/core-img/favicon.ico">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/adminx.css" media="screen" />
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/table.css" media="screen" />

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 관리페이지 - 회원관리
	Email: 			1920110016@office.kopo.ac.kr
	//////////////////////////////////////////////////////
-->
<body>

	<div class="adminx-container">
		<!--네비게이션 바 시작-->
		<%@include file="/WEB-INF/view/manager/frame/Topbar.jsp"%>
		<!--네비게이션 바 종료-->

		<!-- Sidebar 영역 시작 -->
		<%@include file="/WEB-INF/view/manager/frame/Sidebar.jsp"%>
		<!-- Sidebar 종료 -->

		<!-- Main Content -->
		<div class="adminx-content">
			<div class="adminx-main-content">
				<div class="container-fluid">
					<!-- BreadCrumb -->
					<nav aria-label="breadcrumb" role="navigation">
						<ol class="breadcrumb adminx-page-breadcrumb">
							<li class="breadcrumb-item"><a href="#">Home</a></li>
							<li class="breadcrumb-item"><a href="#">회원관리</a></li>
						</ol>
					</nav>
					<div class="pb-3"></div>
					<!-- Modal -->

					<!--리뷰 컨텐츠 영역 시작-->
					<div class="container table-area">
						<div class="row">
							<div class="col-md-12">
								<input type="button" onclick="modifyDel()" class="btn btn-list" value="강제탈퇴"> 
								<input type="button" onclick="modifyStat()" class="btn btn-list" value="상태변경">
								<input type="button" onclick="modifyAuthor()" class="btn btn-list" value="권한변경">
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="table table-striped" style="width: 100%">
									<div>
										<div class="row">
											<div class="col-md-1 cell cell-top">선택</div>
											<div class="col-md-2 cell cell-top">회원ID</div>
											<div class="col-md-2 cell cell-top">회원이름</div>
											<div class="col-md-3 cell cell-top">Email</div>
											<div class="col-md-1 cell cell-top">직업</div>
											<div class="col-md-1 cell cell-top">가입일</div>
											<div class="col-md-1 cell cell-top">권한</div>
											<div class="col-md-1 cell cell-top">상태</div>
										</div>
									</div>
									<div>
										<%
											for (UserInfoDTO uDTO : uList) {
										%>
										<div class="row">
											<div class="col-md-1 cell"><input type="radio" name="userCheck" value="<%=CmmUtil.nvl(uDTO.getUser_id()) %>"></div>
											<div class="col-md-2 cell"><%=CmmUtil.nvl(uDTO.getUser_id()) %></div>
											<div class="col-md-2 cell"><%=CmmUtil.nvl(uDTO.getUser_name()) %></div>
											<div class="col-md-3 cell"><%=EncryptUtil.decAES128CBC(CmmUtil.nvl(uDTO.getEmail())) %></div>
											<div class="col-md-1 cell"><%=CmmUtil.nvl(uDTO.getUser_job()) %></div>
											<div class="col-md-1 cell"><%=CmmUtil.nvl(uDTO.getReg_dt().substring(2,11)) %></div>
											<div class="col-md-1 cell">
												<%
													if (uDTO.getUser_Author().equals("0")) {
												%> 사용자 <%
													} else {
												%> 관리자 <%
													}
												%>
											</div>
											<div class="col-md-1 cell">
												<%
													if (uDTO.getUser_Stat().equals("0")) {
												%> 정상 <%
													} else if (uDTO.getUser_Stat().equals("1")) {
												%> 정지 <%
													} else {
												%> 탈퇴 <%
													}
												%>
											</div>
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
	 														<a href="/manager/manager_user.do?pgNum=<%=pgNum - 1%>">&lt;</a> 
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
														<a href="/manager/manager_user.do?pgNum=<%=i%>"><%=i%></a>
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
														<a href="/manager/manager_user.do?pgNum=<%=i%>"><%=i%></a>
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
														<a href="/manager/manager_user.do?pgNum=<%=i%>"><%=i%></a>
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
														<a href="/manager/manager_user.do?pgNum=<%=pgNum + 1%>">&gt;</a>
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
		function modifyAuthor() {
			//alert($("input:radio[name=userCheck]:checked").val());

			if (confirm("권한을 변경하시겠습니까?") == true) {
				document.location.href = "/manager/ModifyAuthorProc.do?userCheck="
						+ $("input:radio[name=userCheck]:checked").val();
			} else {
				return false;
			}
		}
		function modifyStat() {
			//alert($("input:radio[name=userCheck]:checked").val());

			if (confirm("유저상태를 변경하시겠습니까?") == true) {
				document.location.href = "/manager/ModifyStatProc.do?userCheck="
						+ $("input:radio[name=userCheck]:checked").val();
			} else {
				return false;
			}
		}
		function modifyDel() {
			//alert($("input:radio[name=userCheck]:checked").val());

			if (confirm("유저상태를 변경하시겠습니까?") == true) {
				document.location.href = "/manager/ModifyDelProc.do?userCheck="
						+ $("input:radio[name=userCheck]:checked").val();
			} else {
				return false;
			}
		}
	</script>
</body>
</html>