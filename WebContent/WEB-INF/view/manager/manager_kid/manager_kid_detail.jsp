<%@ page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="poly.dto.KidInfoDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%
	String user_id = (String) session.getAttribute("SS_USER_ID");
	String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

	KidInfoDTO nDTO = (KidInfoDTO) request.getAttribute("nDTO");

	String Clcnt3 = CmmUtil.nvl(nDTO.getClcnt3());
	int clcnt3 = Integer.parseInt(Clcnt3);

	String Clcnt4 = CmmUtil.nvl(nDTO.getClcnt4());
	int clcnt4 = Integer.parseInt(Clcnt4);

	String Clcnt5 = CmmUtil.nvl(nDTO.getClcnt5());
	int clcnt5 = Integer.parseInt(Clcnt5);

	String Mixclcnt = CmmUtil.nvl(nDTO.getMixclcnt());
	int mixclcnt = Integer.parseInt(Mixclcnt);

	String Shclcnt = CmmUtil.nvl(nDTO.getShclcnt());
	int shclcnt = Integer.parseInt(Shclcnt);

	String Ppcnt3 = CmmUtil.nvl(nDTO.getPpcnt3());
	int ppcnt3 = Integer.parseInt(Ppcnt3);

	String Ppcnt4 = CmmUtil.nvl(nDTO.getPpcnt4());
	int ppcnt4 = Integer.parseInt(Ppcnt4);

	String Ppcnt5 = CmmUtil.nvl(nDTO.getPpcnt5());
	int ppcnt5 = Integer.parseInt(Ppcnt5);

	String Mixppcnt = CmmUtil.nvl(nDTO.getMixppcnt());
	int mixppcnt = Integer.parseInt(Mixppcnt);

	String Shppcnt = CmmUtil.nvl(nDTO.getShppcnt());
	int shppcnt = Integer.parseInt(Shppcnt);
	
	String establish = CmmUtil.nvl(nDTO.getEstablish());
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>[관리페이지] 유치원 상세보기</title>
<link rel="icon" href="/mypage/dist/img/core-img/favicon.ico">

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/adminx.css" media="screen" />
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/common.css" media="screen" />
<link href="/kido/css/kindDetail.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/table.css" media="screen" />

</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 관리페이지 - 유치원 상세보기
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
					<!--게시글 상세 및 수정 영역 시작-->
					<div class="row">
						<div class="col-lg-12">
							<div class="card form-area-kid">
								<input type="hidden" name="free_seq" value="<%=CmmUtil.nvl(nDTO.getKid_seq())%>">
								<div class="manager-rightContent" id="rightContent">
									<div class="pageTitle kinderInfo clearfix">
										<h1><%=CmmUtil.nvl(nDTO.getKindername())%><span><%=establish%></span></h1>
									</div>
									<div>
										<div>
											<div class="tapContentTitle clearfix">
												<h3>요약</h3>
											</div>

											<div>
												<div class="container">
													<div class="row">
														<div class="col-lg-3 lg-3-st">기관명</div>
														<div class="col-lg-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getKindername())%></div>
													</div>
													<div class="row">
														<div class="col-lg-3 lg-3-st">대표자명</div>
														<div class="col-lg-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getRppnname()) %></div>
													</div>
													<div class="row">
														<div class="col-lg-3 lg-3-st">원장명</div>
														<div class="col-lg-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getLdgrname()) %></div>
													</div>
													<div class="row">
														<div class="col-lg-3 lg-3-st">설립유형</div>
														<div class="col-lg-9 lg-9-st"><%=establish%></div>
													</div>
													<div class="row">
														<div class="col-lg-3 lg-3-st">설립일</div>
														<div class="col-lg-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getEdate()) %></div>
													</div>
													<div class="row">
														<div class="col-lg-3 lg-3-st">개원일</div>
														<div class="col-lg-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getOdate()) %></div>
													</div>
													<div class="row">
														<div class="col-lg-3 lg-3-st">관할행정기관</div>
														<div class="col-lg-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getOfficeedu()) %> / <%=CmmUtil.nvl(nDTO.getSubofficeedu()) %></div>
													</div>
													<div class="row">
														<div class="col-lg-3 lg-3-st">전화번호</div>
														<div class="col-lg-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getTelno()) %></div>
													</div>
													<div class="row">
														<div class="col-lg-3 lg-3-st">홈페이지</div>
														<div class="col-lg-9 lg-9-st">
															<a href="<%=CmmUtil.nvl(nDTO.getHpaddr()) %>" target="_blank"><%=CmmUtil.nvl(nDTO.getHpaddr()) %></a>
														</div>
													</div>
													<div class="row">
														<div class="col-lg-3 lg-3-st">운영시간</div>
														<div class="col-lg-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getOdate()) %></div>
													</div>
													<div class="row">
														<div class="col-lg-3 lg-3-st">주소</div>
														<div class="col-lg-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getAddr()) %></div>
													</div>
													<div class="row">
														<div class="col-lg-12 lg-3-st" style="text-align: center;">학급 및 유아수(명)</div>
													</div>
													<div class="row">
														<div class="col-lg-1 lg-3-st">연령</div>
														<div class="col-lg-2 lg-3-st">만 3세 반</div>
														<div class="col-lg-2 lg-3-st">만 4세 반</div>
														<div class="col-lg-2 lg-3-st">만 5세 이상 반</div>
														<div class="col-lg-2 lg-3-st">혼합반</div>
														<div class="col-lg-2 lg-3-st">특수학급</div>
														<div class="col-lg-1 lg-3-st">총계</div>
													</div>
													<div class="row">
														<div class="col-md-1 lg-3-st ">학급수</div>
														<div class="col-md-2 lg-9-st"><%=CmmUtil.nvl(nDTO.getClcnt3()) %></div>
														<div class="col-md-2 lg-9-st"><%=CmmUtil.nvl(nDTO.getClcnt4()) %></div>
														<div class="col-md-2 lg-9-st"><%=CmmUtil.nvl(nDTO.getClcnt5()) %></div>
														<div class="col-md-2 lg-9-st"><%=CmmUtil.nvl(nDTO.getMixclcnt()) %></div>
														<div class="col-md-2 lg-9-st"><%=CmmUtil.nvl(nDTO.getShclcnt()) %></div>
														<div class="col-md-1 lg-9-st"><%=clcnt3 + clcnt4 + clcnt5 + mixclcnt + shclcnt%></div>
													</div>
													<div class="row">
														<div class="col-md-1 lg-3-st">유아수</div>
														<div class="col-md-2 lg-9-st"><%=CmmUtil.nvl(nDTO.getPpcnt3()) %></div>
														<div class="col-md-2 lg-9-st"><%=CmmUtil.nvl(nDTO.getPpcnt4()) %></div>
														<div class="col-md-2 lg-9-st"><%=CmmUtil.nvl(nDTO.getPpcnt5()) %></div>
														<div class="col-md-2 lg-9-st"><%=CmmUtil.nvl(nDTO.getMixppcnt()) %></div>
														<div class="col-md-2 lg-9-st"><%=CmmUtil.nvl(nDTO.getShppcnt()) %></div>
														<div class="col-md-1 lg-9-st"><%=ppcnt3 + ppcnt4 + ppcnt5 + mixppcnt + shppcnt%></div>
													</div>
												</div>
												<br>
											</div>
										</div>
									</div>
									<div class="pageBtn">
										<a href="/manager/manager_kid.do" class="green list" style="width: 200px">목록으로 돌아가기</a> 
										<a href="/manager/manager_kid_modify.do?kid_seq=<%=CmmUtil.nvl(nDTO.getKid_seq()) %>" class="green list" style="width: 200px">수정하기</a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--게시글 상세 및 수정 영역 종료-->
				</div>
			</div>
		</div>
		<!-- // Main Content -->
	</div>
	<!-- If you prefer jQuery these are the required scripts -->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
	<script src="/mypage/dist/js/vendor.js"></script>
	<script src="/mypage/dist/js/adminx.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js"></script>

</body>
</html>