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
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>[KIDO] 유치원 상세정보</title>
<link rel="icon" href="/kido/img/core-img/favicon.ico">
<link href="/kido/css/style.css" rel="stylesheet">
<link href="/kido/css/kindDetail.css" rel="stylesheet">
<link href="/kido/css/responsive/responsive.css" rel="stylesheet">
<link href="/kido/css/kakao_map.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/table.css" media="screen" />
</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 유치원 상세정보
	Email: 			1920110016@office.kopo.ac.kr
	//////////////////////////////////////////////////////
-->
<body>
	<!-- Preloader -->
	<div id="preloader">
		<div class="dorne-load"></div>
	</div>

	<!-- Navigation 헤더 -->
	<%
		if (user_id == null) {
	%>
	<%@include file="/WEB-INF/view/main/frame/TopbarLogout.jsp"%>
	<%
		} else {
	%>
	<%
		if (userAuthor.equals("1")) {
	%>
	<%@include file="/WEB-INF/view/main/frame/TopbarLoginAdmin.jsp"%>
	<%
		} else {
	%>
	<%@include file="/WEB-INF/view/main/frame/TopbarLoginUser.jsp"%>
	<%
		}
	%>
	<%
		}
	%>
	<!-- /Navigation 헤더 -->
	<!-- 1 : 관리자, 0 : 사용자 -->
	<!-- ***** Top 영역 종료 ***** -->

	<!-- ***** Breadcumb Area Start ***** -->
	<div class="breadcumb-area bg-img bg-overlay" style="background-image: url(/kido/img/bg-img/bg-6.jpg)"></div>
	<!-- ***** Breadcumb Area End ***** -->

	<div class="container">
		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-8">
				<div class="rightContent" id="rightContent">
					<input type="hidden" name="kid_seq" value="<%=CmmUtil.nvl(nDTO.getKid_seq())%>">
					<div class="pageTitle kinderInfo clearfix">
						<p>
							<a href="/main/index.do">홈으로</a> <span>&lt;</span> <a
								href="/Map_search.do">유치원 조회</a> <span>&lt;</span> <a
								href="javascript:fn_move(1);" class="nowPage">요약</a>
						</p>
						<h1><%=CmmUtil.nvl(nDTO.getKindername())%><span><%=establish%></span>
						</h1>
					</div>
					<div>
						<div>
							<div class="tapContentTitle clearfix">
								<h3>요약</h3>
							</div>

							<div>
								<div class="container">
									<div class="row">
										<div class="col-md-3 lg-3-st">기관명</div>
										<div class="col-md-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getKindername())%></div>
									</div>
									<div class="row">
										<div class="col-md-3 lg-3-st">대표자명</div>
										<div class="col-md-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getRppnname()) %></div>
									</div>
									<div class="row">
										<div class="col-md-3 lg-3-st">원장명</div>
										<div class="col-md-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getLdgrname()) %></div>
									</div>
									<div class="row">
										<div class="col-md-3 lg-3-st">설립유형</div>
										<div class="col-md-9 lg-9-st"><%=establish%></div>
									</div>
									<div class="row">
										<div class="col-md-3 lg-3-st">설립일</div>
										<div class="col-md-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getEdate()) %></div>
									</div>
									<div class="row">
										<div class="col-md-3 lg-3-st">개원일</div>
										<div class="col-md-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getOdate()) %></div>
									</div>
									<div class="row">
										<div class="col-md-3 lg-3-st">관할행정기관</div>
										<div class="col-md-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getOfficeedu()) %>
											/
											<%=CmmUtil.nvl(nDTO.getSubofficeedu()) %></div>
									</div>
									<div class="row">
										<div class="col-md-3 lg-3-st">전화번호</div>
										<div class="col-md-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getTelno()) %></div>
									</div>
									<div class="row">
										<div class="col-md-3 lg-3-st">홈페이지</div>
										<div class="col-md-9 lg-9-st">
											<a href="<%=CmmUtil.nvl(nDTO.getHpaddr()) %>" target="_blank"><%=CmmUtil.nvl(nDTO.getHpaddr()) %></a>
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 lg-3-st">운영시간</div>
										<div class="col-md-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getOpertime()) %></div>
									</div>
									<div class="row">
										<div class="col-md-3 lg-3-st">주소</div>
										<div class="col-md-9 lg-9-st"><%=CmmUtil.nvl(nDTO.getAddr()) %></div>
									</div>
									<div class="row">
										<div class="col-md-12 lg-3-st" style="text-align: center;">학급
											및 유아수(명)</div>
									</div>
									<div class="row">
										<div class="col-md-1 lg-3-st">연령</div>
										<div class="col-md-2 lg-3-st">만 3세 반</div>
										<div class="col-md-2 lg-3-st">만 4세 반</div>
										<div class="col-md-2 lg-3-st">만 5세 이상 반</div>
										<div class="col-md-2 lg-3-st">혼합반</div>
										<div class="col-md-2 lg-3-st">특수학급</div>
										<div class="col-md-1 lg-3-st">총계</div>
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
						<a href="/Map_search.do" class="green list">지도로 가기</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- ****** Footer 영역 시작 ****** -->
	<%@include file="/WEB-INF/view/main/frame/Footer.jsp"%>
	<!-- ****** Footer 영역 종료 ****** -->

	<!-- jQuery-2.2.4 js -->
	<script src="/kido/js/jquery/jquery-2.2.4.min.js"></script>
	<!-- Popper js -->
	<script src="/kido/js/bootstrap/popper.min.js"></script>
	<!-- Bootstrap-4 js -->
	<script src="/kido/js/bootstrap/bootstrap.min.js"></script>
	<!-- All Plugins js -->
	<script src="/kido/js/others/plugins.js"></script>
	<!-- Active JS -->
	<script src="/kido/js/active.js"></script>


</body>
</html>