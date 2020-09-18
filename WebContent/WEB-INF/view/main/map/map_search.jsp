<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="poly.dto.KidInfoDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%
	String user_id = (String) session.getAttribute("SS_USER_ID");
	String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

	KidInfoDTO nDTO = (KidInfoDTO) request.getAttribute("nDTO");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>[KIDO] 유치원 조회</title>
<link rel="icon" href="/kido/img/core-img/favicon.ico">
<link href="/kido/css/style.css" rel="stylesheet">
<link href="/kido/css/app2.css" rel="stylesheet">
<link href="/kido/css/kakao_map.css" rel="stylesheet">
<link href="/kido/css/responsive/responsive.css" rel="stylesheet">

<!-- 카카오 맵 JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=716487d25b6b667214450a2f67a37a1e&libraries=services"></script>
<script type="text/javascript" src="/kido/js/kakao_map.js"></script>
<style type="text/css">
.board-btn-2 {
    min-width: 128px;
    background-color: #bb66dd;
    height : 36px;
    color: #fff;
    font-weight: 600;
    font-size: 13px;
    text-align: center;
    padding: 10px;
}
.board-btn-2:hover {
    min-width: 128px;
    background-color: #8d06c2;
    height : 36px;
    color: #fff;
    font-weight: 600;
    font-size: 13px;
    padding: 10px;
}
</style>
</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 유치원 조회
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
	<section class="dorne-single-listing-area">
		<div class="align-items-end">
			<div class="hero-search-form-2">
				<div class="home_content_board">
					<!-- 탭 내용 영역 시작 -->
					<div class="tab-content" id="nav-tabContent">
						<div class="tab-pane-board-2 fade show active" id="nav-places"
							role="tabpanel" aria-labelledby="nav-places-tab">
							<form action="#" method="get" id="mapSearch">
								<select id="sido" name="selName" class="custom-select board-tab" onchange="getList(this.selectedIndex)">
									<option>지역</option>
									<option value="서울특별시">서울특별시</option>
								</select> 
								<select id="sigungu" name="selName" class="custom-select board-tab">
									<option>행정구역</option>
								</select> 
								<!-- <input type="button" id="Addr_Search" class="board-btn-2" value="지도로 찾기"> -->
								<a href="#"  id="Addr_Search" onclick="document.getElementById('modifyform').submit();" class="board-btn-2">지도로 찾기</a>
							</form>
						</div>
					</div>
					<!-- 탭 내용 영역 종료 -->
				</div>
			</div>
		</div>
	</section>
	<!-- Explore Area -->
	<section class="dorne-explore-area d-md-flex">
		<div id="map" style="width: 100%; height: 800px;"></div>
	</section>

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