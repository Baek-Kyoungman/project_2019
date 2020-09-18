<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>[KIDO] 비밀번호 찾기</title>
<link rel="icon" href="/kido/img/core-img/favicon.ico">
<link href="/kido/css/style.css" rel="stylesheet">
<link href="/kido/css/responsive/responsive.css" rel="stylesheet">
<link href="/kido/css/others/kido.css" rel="stylesheet">
<link href="/kido/css/others/find.css" rel="stylesheet">
<link href="/kido/css/others/reg.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/kido/css/util.css">
<link rel="stylesheet" type="text/css" href="/kido/css/main.css">
</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 비밀번호 찾기
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

	<!-- Page Content -->
	<div class="container">
		<!-- Page Heading/Breadcrumbs -->
		<br>
		<!-- Content Row -->
		<div class="row find-area-2">
			<!-- Map Column -->
			<div class="col-lg-3 mb-4"></div>
			<div class="col-lg-6 mb-4 find-area-inner">
				<form action="/user/PasswordFind_Proc.do" class="user" method="post">
					<a class="login100-form-title" href="/main/index.do"> 
						<img src="/kido/img/core-img/logo_6.png" alt="AVATAR" style="margin-bottom: 50px">
					</a>
					<div class="text_left inner_text">
						<div class="inner-id-area">
							<span>내 정보에 등록된 이메일 인증 (go****@na***.com)</span>
						</div>
						<p>개인정보보호를 위해 연락처는 일부분만 보여드리며, * 가 무작위로 표기됩니다.</p>
					</div>
					
					<div class="find-area-1 find-text">
						<div class="form-group">
							<input type="text" name="user_name" class="form_control form_control_user" id="InputName" placeholder="이름을 입력해주세요">
						</div>
						<div class="form-group">
							<input type="email" name="email" class="form_control form_control_user" id="InputEmail" placeholder="이메일 주소 전체를 입력해주세요.">
						</div>
						<hr>
						<a href="/user/userFind.do" class="btn btn-user btn-block kido-btn find-btn">이전으로</a>
						<input type="submit" class="btn btn-user btn-block kido-btn find-btn" value="다음단계">
					</div>
				</form>
			</div>
			<div class="col-lg-3 mb-4"></div>
			<hr>
		</div>
	</div>
	<!-- /.row -->
	</div>
	<!-- /Page Content -->

	<!--sign in-->
	<script src='/kido/js/app.min.js'></script>
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