<%@page import="poly.dto.UserInfoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="poly.dto.UserInfoDTO"%>
<%
	String user_name = (String) session.getAttribute("ID_USER_NAME");
	String email = (String) session.getAttribute("ID_EMAIL");
	String user_id = (String) session.getAttribute("ID_USER_ID");

	UserInfoDTO pDTO = (UserInfoDTO) request.getAttribute("pDTO");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


<!-- Title -->
<title>[KIDO] 아이디 찾기</title>

<!-- Favicon -->
<!--인터넷 웹 브라우저의 주소창에 표시되는 웹사이트나 웹페이지를 대표하는 아이콘-->
<link rel="icon" href="/kido/img/core-img/favicon.ico">

<!-- Core Stylesheet -->
<link href="/kido/css/style.css" rel="stylesheet">

<!-- Responsive CSS -->
<link href="/kido/css/responsive/responsive.css" rel="stylesheet">
<link href="/kido/css/others/kido.css" rel="stylesheet">
<link href="/kido/css/others/find.css" rel="stylesheet">
<link href="/kido/css/others/reg.css" rel="stylesheet">
<link href="/kido/css/util.css" rel="stylesheet">
<link href="/kido/css/main.css" rel="stylesheet">

</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 아이디 찾기
    Email: 			1920110016@office.kopo.ac.kr
	//////////////////////////////////////////////////////
-->

<body>
	<!-- Preloader -->
	<!--새로고침시 효과-->
	<div id="preloader">
		<div class="dorne-load"></div>
	</div>


	<!-- ***** Breadcumb Area Start ***** -->
	<div class="breadcumb-area"></div>
	<!-- ***** Breadcumb Area End ***** -->

	<div class="container">
		<br>
		<div class="row find-area-2">
			<div class="col-lg-3 mb-4"></div>
			<div class="col-lg-6 mb-4 find-area-inner">
				<form class="user">
					<a class="login100-form-title" href="/main/index.do"> 
					<img src="/kido/img/core-img/logo_5.png" alt="AVATAR" style="margin-bottom: 50px">
					</a>
					<div class="text_left inner_text">
						<div class="inner-id-area" style="margin-bottom: 50px;">
							<span class="id_style"><%=user_name%>/<%=email%> 으로 찾은 아이디입니다</span>
						</div>
						<p class="id_style"><%=user_id%></p>
					</div>
					<div class="find-area-1 find-text">
						<hr>
						<a href="/user/loginForm.do" class="btn btn-user btn-block kido-btn find-btn">로그인</a> 
						<a href="/user/userFind.do" class="btn btn-user btn-block kido-btn find-btn">비밀번호 찾기</a>
					</div>
				</form>
			</div>
			<div class="col-lg-3 mb-4"></div>
			<hr>
		</div>
	</div>
	</div>





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