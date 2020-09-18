<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<header class="header_area" id="header">
	<div class="container-fluid h-100">
		<div class="row h-100">
			<div class="col-12 h-100">
				<nav class="h-100 navbar navbar-expand-lg">
					<a class="navbar-brand" href="/main/index.do"><img
						src="/kido/img/core-img/logo_2.png" alt=""></a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#dorneNav" aria-controls="dorneNav"
						aria-expanded="false" aria-label="Toggle navigation">
						<span class="fa fa-bars"></span>
					</button>
					<!-- Nav -->
					<div class="collapse navbar-collapse" id="dorneNav">
						<ul class="navbar-nav mr-auto" id="dorneMenu">
							<li class="nav-item active"><a class="nav-link"
								href="/main/index.do">Home <span class="sr-only">(current)</span>
							</a></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">커뮤니티 <i class="fa fa-angle-down"
									aria-hidden="true"></i>
							</a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="/main/ReviewList.do?pgNum=1">리뷰
										게시판</a> <a class="dropdown-item" href="/main/FreeList.do?pgNum=1">자유
										게시판</a>
								</div></li>
							<li class="nav-item"><a class="nav-link"
								href="/Map_search.do">유치원 조회</a></li>
						</ul>
						<!-- Signin btn -->
						<div class="dorne-signin-btn">
							<a href="/user/loginForm.do">로그인 or 회원가입</a>
						</div>
					</div>
				</nav>
			</div>
		</div>
	</div>
</header>