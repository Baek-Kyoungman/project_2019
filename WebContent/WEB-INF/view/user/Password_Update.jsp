<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String user_name = (String) session.getAttribute("SS_USER_NAME");
	String email = (String) session.getAttribute("SS_EMAIL");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>[KIDO] 비밀번호 변경</title>
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
				<form action="/user/Password_Update_Proc.do" class="user">
					<a class="login100-form-title" href="/main/index.do"> 
						<img src="/kido/img/core-img/logo_7.png" alt="AVATAR" style="margin-bottom: 50px">
					</a>
					<div class="text_left inner_text">
						<div class="inner-id-area"></div>
					</div>
					<div class="find-area-1 find-text">
						<div class="form-group">
							<input type="password" name="password" class="form_control form_control_user" id="InputName" placeholder="비밀번호">
						</div>
						<div class="form-group">
							<input type="password" name="password1"
								class="form_control form_control_user" id="InputEmail"
								placeholder="비밀번호 확인">
						</div>
						<hr>
						<button class="btn btn-user btn-block kido-btn find-btn">확인</button>
					</div>
				</form>
			</div>
			<div class="col-lg-3 mb-4"></div>
			<hr>
		</div>
	</div>
	<!-- /.row -->
	<!-- /Page Content -->


	<script>
		function update_password() {

			var getPassword = RegExp(/^[a-zA-Z0-9!@#$]{8,20}$/);

			//기존 비밀번호와 새로운 비밀번호 일치 확인
			if ($("input[name=password]").val() === $(
					"input[name=new_password1]").val()) {
				alert("기존 비밀번호와 새로운 비밀번호가 일치합니다")
				$("input[name=new_password1]").val("");
				$("input[name=new_password1]").focus();
				return false;
			}

			//비밀번호 유효성검사 
			else if (!getPassword.test($("input[name=password]").val())) {
				alert("비밀번호 형식에 맞게 입력해주세요");
				$("input[name=password]").val("");
				$("input[name=password]").focus();
				return false;
			}

			//비밀번호 확인 입력여부 검사
			else if ($("input[name=new_password1]").val() == "") {
				alert("새로운 비밀번호를 입력해 주세요.")
				$("input[name=new_password1]").focus();
				return false;
			}

			//비밀번호 일치여부 검사
			else if ($("input[name=new_password1]").val() != $(
					"input[name=new_password2]").val()) {
				alert("비밀번호가 일치하지 않습니다.")
				$("input[name=new_password2]").val("");
				$("input[name=new_password2]").focus();
				return false;
			}

		}
	</script>
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