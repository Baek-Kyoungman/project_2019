<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>[KIDO] 아이디/비밀번호 찾기 홈</title>
<link rel="icon" href="/kido/img/core-img/favicon.ico">
<link href="/kido/css/style.css" rel="stylesheet">
<link href="/kido/css/responsive/responsive.css" rel="stylesheet">
<link href="/kido/css/others/kido.css" rel="stylesheet">
<link href="/kido/css/others/reg.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/kido/css/util.css">
<link rel="stylesheet" type="text/css" href="/kido/css/main.css">


</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 아이디/비밀번호 찾기 홈
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
		<div class="row reg-row">

			<div class="col-lg-2 mb-4 inner_box"></div>
			<div class="col-lg-8 mb-4">
				<div class="inner_box">
					<div class="find-area-1">
						<a class="login100-form-title" href="/main/index.do"> 
							<img src="/kido/img/core-img/logo_4.png" alt="AVATAR" style="margin-bottom: 50px">
						</a>
						<form action="/user/IdFind_Proc.do" class="user" onsubmit="return id_check();">
							<div class="text_left inner_text">
								<h5>아이디 찾기</h5>
								<small> 이름과 이메일을 입력하시면, 찾을 수 있는 방법을 알려드려요. </small>
							</div>
							<div class="text_left inner_text">이름</div>
							<div class="form-group">
								<input type="text" name="user_name" class="form_control form_control_user" id="InputName" maxlength="20">
							</div>
							<div class="text_left inner_text">이메일</div>
							<div class="form-group">
								<input type="text" name="email" class="form_control form_control_user" id="InputEmail">
							</div>
							<hr>
							<input type="submit" class="btn  btn-user btn-block kido-btn" value="아이디찾기">
							<hr>
						</form>
						<br>
					</div>
					<div class="find-area-2">
						<form action="/user/PasswordFind1.do" class="user" method="post" onsubmit="return pw_check();">
							<div class="text_left inner_text">
								<h5>비밀번호 찾기</h5>
								<small>아이디를 입력하시면, 찾을 수 있는 방법을 알려드려요.</small>
							</div>
							<div class="text_left inner_text">아이디</div>
							<div class="form-group">
								<input type="text" name="user_id" class="form_control form_control_user" id="InputEmail">
							</div>
							<hr>
							<input type="submit" class="btn  btn-user btn-block kido-btn" value="비밀번호찾기">
						</form>
					</div>
					<hr>
				</div>
			</div>
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->

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
	
	<script>
	function id_check(){
		var getName= RegExp(/^[가-힣]{2,20}$/);										//이름 정규표현식
		var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/); 	//이메일 정규표현식
		
		//이름 입력여부 검사
		if($("input[name=user_name]").val()==""){
			alert("이름을 입력해 주세요.");
			$("input[name=user_name]").focus();
			return false;
		}
		//이름 유효성 검사 
		else if(!getName.test($("input[name=user_name]").val())){ 
			alert("이름 형식에 맞게 입력해주세요")
			$("input[name=user_name]").val("");
			$("input[name=user_name]").focus();
			return false; 
		}
		//이메일 입력여부 검사
		else if($("input[name=email]").val()==""){
			alert("이메일을 입력해 주세요.")
			$("input[name=email]").focus();
			return false;
		}
	    else if(!getMail.test($("input[name=email]").val())){ 
			alert("이메일 형식에 맞게 입력해주세요")
			$("input[name=email]").val("");
			$("input[name=email]").focus();
			return false; 
		}
	}
	</script>
	<script>
	function pw_check(){
		var getCheck= RegExp(/^[a-z0-9]{5,20}$/); 									//아이디 정규표현식
		
		//아이디 입력여부 검사
		if($("input[name=user_id]").val()==""){
			alert("아이디를 입력해 주세요.");
			$("input[name=user_id]").focus();
			return false;
		}
		//아이디 유효성 검사 
		else if(!getCheck.test($("input[name=user_id]").val())){ 
			alert("아이디 형식에 맞게 입력해주세요")
			$("input[name=user_id]").val("");
			$("input[name=user_id]").focus();
			return false; 
		}
	}
	</script>
</body>
</html>