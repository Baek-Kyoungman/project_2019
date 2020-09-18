<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>[KIDO] 회원가입</title>
<link rel="icon" href="/kido/img/core-img/favicon.ico">
<link href="/kido/css/style.css" rel="stylesheet">
<link href="/kido/css/responsive/responsive.css" rel="stylesheet">
<link href="/kido/css/others/kido.css" rel="stylesheet">
<link href="/kido/css/others/reg.css" rel="stylesheet">

</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 회원가입
	Email: 			1920110016@office.kopo.ac.kr
	//////////////////////////////////////////////////////
-->
<body>
	<!-- Preloader -->
	<!--새로고침시 효과-->
	<div id="preloader">
		<div class="dorne-load"></div>
	</div>

	<!-- Page Content -->
	<div class="container">
		<!-- Page Heading/Breadcrumbs -->
		<br>
		<!-- Content Row -->
		<div class="row reg-row">
			<!-- 회원가입 상세 Column 시작 -->
			<div class="col-lg-3 mb-4 inner_box"></div>
			<!-- 회원가입 상세 Column 종료 -->

			<div class="col-lg-6 mb-4">
				<div class="reg-logo">
					<a href="/main/index.do"><img
						src="/kido/img/core-img/logo_3.png" alt=""></a>
				</div>
				<div class="inner_box">
					<form class="user" name="f" method="post"
						action="/user/insertUserInfo.do" onsubmit="return check();">
						<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

						<!--이름 name="" id="" start-->
						<div class="text_left inner_text" style="margin-top: 100px;">
							<h5 class="h5-custom">이름</h5>
						</div>
						<div class="form-group">
							<input type="text" class="form_control form_control_user"
								id="user_name" name="user_name" maxlength="20">
						</div>
						<div class="check_font" id="name_check"></div>
						<!--이름 name="" id="" end-->

						<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

						<!--아이디 name="" id="" start-->
						<div class="text_left inner_text">
							<h5 class="h5-custom">아이디</h5>
						</div>
						<div style="margin-bottom: 60px;">
							<div class="form-group">
								<input type="text" class="form_control form_control_user"
									id="user_id" name="user_id">
								<div class="check_font" id="id_check"></div>
								<input type="button" class="btn btn-reg" id="idck" value="중복확인"
									style="margin-top: 10px;">
							</div>
						</div>

						<input type="hidden" id="idCheck" value="0">
						<div class="check_font" id="id_check"></div>
						<!--아이디 name="" id="" end-->

						<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

						<!--비밀번호 name="" id="" start-->
						<div class="text_left inner_text">
							<h5 class="h5-custom">비밀번호</h5>
						</div>
						<div class="form-group">
							<input type="password" class="form_control form_control_user"
								id="user_pw" name="password" maxlength="20">
						</div>
						<div class="check_font" id="pw_check"></div>
						<!--비밀번호 name="" id="" end-->

						<!--비밀번호 재확인 name="" id="" start-->
						<div class="text_left inner_text">
							<h5 class="h5-custom">비밀번호 재확인</h5>
						</div>
						<div class="form-group">
							<input type="password" class="form_control form_control_user"
								id="user_pw2" name="password2" maxlength="20">
						</div>
						<div class="check_font" id="pw2_check"></div>
						<!--비밀번호 재확인 name=R"" id="" end-->

						<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

						<!--이메일 name="" id="" start-->
						<div class="text_left inner_text">
							<h5 class="h5-custom">이메일</h5>
						</div>
						<div class="form-group">
							<input type="text" class="form_control form_control_user"
								id="email" name="email">
							<!-- <input type="button" class="btn btn-reg" id="emailck" value="중복확인" style="margin-top: 10px;"> -->
						</div>
						<div class="check_font" id="mail_check"></div>
						<!-- <input type="hidden" id="emailCheck" value="0"> -->
						<!--이메일 name="" id="" end-->

						<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

						<!--직업 name="" id="" start-->
						<div class="text_left inner_text">
							<h5 class="h5-custom">직업</h5>
						</div>
						<div class="form-group">
							<select name="user_job" class="category">
								<option value="직업">직업</option>
								<option value="학생">학생</option>
								<option value="주부">주부</option>
								<option value="회사원">회사원</option>
								<option value="자영업자">자영업자</option>
								<option value="기타">기타</option>
							</select>
						</div>
						<!--직업 name="" id="" end-->

						<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

						<div class="form-group">
							<div class="custom-control custom-checkbox small">
								<input type="checkbox" class="custom-control-input"
									name="user_marketing" id="mark_info" value="동의"> <label
									class="custom-control-label" for="mark_info">[선택] 마케팅
									정보 수신 동의 - 이메일</label>
							</div>
							<br>
							<div class="custom-control custom-checkbox small">
								<input type="checkbox" class="custom-control-input"
									id="user_accept" name="userAgree" value="동의" checked> <label
									class="custom-control-label" for="user_accept">[필수]
									KIDO의 이용약관에 동의합니다.</label>
							</div>
						</div>
						<hr>
						<input type="submit"
							class="btn btn-primary btn-user kido-btn btn-block" id="frm"
							value="가입하기">
					</form>

					<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

					<hr>
					<br>
					<div class="text-center">
						<a class="small" href="#">커뮤니티운영방침</a> | <a class="small" href="#">개인정보취급방침</a>
						| <a class="small" href="#">이용자약관</a>
						<p>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							Copyright &copy;KIDO
							<script>document.write(new Date().getFullYear());</script>
							All rights reserved
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						</p>
					</div>
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
	<script src="/kido/js/RegCheck.js"></script>

	<!-------------------------------------------------------------------------------------------------------------------------------------------------------- -->


</body>
</html>