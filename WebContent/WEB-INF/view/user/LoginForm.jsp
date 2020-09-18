<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>[KIDO] 로그인</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="icon" href="/kido/img/core-img/favicon.ico">

<link rel="stylesheet" type="text/css" href="/kido/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/kido/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/kido/fonts/iconic/css/material-design-iconic-font.min.css">
<link rel="stylesheet" type="text/css" href="/kido/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css" href="/kido/vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css" href="/kido/vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css" href="/kido/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css" href="/kido/vendor/daterangepicker/daterangepicker.css">
<link href="/kido/css/style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/kido/css/util.css">
<link rel="stylesheet" type="text/css" href="/kido/css/main.css">
</head>
<!-- 
	//////////////////////////////////////////////////////
  	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 로그인
    Email: 			1920110016@office.kopo.ac.kr
    //////////////////////////////////////////////////////
-->

<body>
	<!--새로고침시 효과 Start!-->
	<div id="preloader">
		<div class="dorne-load"></div>
	</div>
	<!--새로고침시 효과 End!-->

	<!--로그인 Area Start!-->
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-85 p-b-20">
				<form class="login100-form validate-form" method="post"
					action="/user/getUserLoginCheck.do" onsubmit="return check()">
					<span class="login100-form-title"> <a href="/main/index.do">
							<img src="/kido/img/core-img/logo_3.png" alt="AVATAR">
					</a>
					</span>

					<div class="wrap-input100 validate-input m-t-85 m-b-35"
						data-validate="아이디를 입력해주세요.">
						<input class="input100" type="text" name="user_id"> <span
							class="focus-input100" data-placeholder="아이디"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-50"
						data-validate="비밀번호를 입력해주세요.">
						<input class="input100" type="password" name="password"> <span
							class="focus-input100" data-placeholder="비밀번호"></span>
					</div>


					<div class="container-login100-form-btn">
						<input type="submit" value="로그인" class="login100-form-btn">

						<div class="check_font" id="name_check"></div>
					</div>

					<ul class="login-more p-t-50">
						<li class="m-b-8"><a href="/user/userFind.do" class="txt2">
								아이디 / 비밀번호 찾기 </a></li>
						<li><a href="/user/userRegForm.do" class="txt2"> 회원가입 </a></li>
					</ul>

				</form>
			</div>
		</div>
	</div>
	<!--로그인 Area End!-->


	<div id="dropDownSelect1"></div>

	<!--===============================================================================================-->
	<script src="/kido/vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="/kido/vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="/kido/vendor/bootstrap/js/popper.js"></script>
	<script src="/kido/vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="/kido/vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="/kido/vendor/daterangepicker/moment.min.js"></script>
	<script src="/kido/vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="/kido/vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="/kido/js/main.js"></script>

	<!-- jQuery-2.2.4 js -->
	<script src="/kido/js/jquery/jquery-2.2.4.min.js"></script>
	<!-- All Plugins js -->
	<script src="/kido/js/others/plugins.js"></script>
	<!-- Active JS -->
	<script src="/kido/js/active.js"></script>


</body>
</html>