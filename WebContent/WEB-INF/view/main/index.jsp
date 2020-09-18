<%@page import="poly.util.CmmUtil"%>
<%@page import="poly.dto.ReviewDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String user_id = (String) session.getAttribute("SS_USER_ID");
	String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");
	
	List<ReviewDTO> rList = (List<ReviewDTO>) request.getAttribute("rList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>KIDO</title>
<link rel="icon" href="/kido/img/core-img/favicon.ico">
<link href="/kido/css/style.css" rel="stylesheet">
<link href="/kido/css/responsive/responsive.css" rel="stylesheet">
</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: KIDO
	Email: 			1920110016@office.kopo.ac.kr
	//////////////////////////////////////////////////////
-->
<body>
	<!-- Preloader -->
	<!--새로고침시 효과-->
	<div id="preloader">
		<div class="dorne-load"></div>
	</div>

	<!-- ***** Top 영역 시작 ***** -->
	<!-- Navigation 헤더 -->
	<% if (user_id == null) { %>
		<%@include file="/WEB-INF/view/main/frame/TopbarLogout.jsp"%>
	<% } else { %>
		<% if (userAuthor.equals("1")) { %>
			<%@include file="/WEB-INF/view/main/frame/TopbarLoginAdmin.jsp"%>
		<% } else { %>
			<%@include file="/WEB-INF/view/main/frame/TopbarLoginUser.jsp"%>
		<% } %>
	<% } %>
	<!-- /Navigation 헤더 -->
	<!-- 1 : 관리자, 0 : 사용자 -->
	<!-- ***** Top 영역 종료 ***** -->

	<!-- ***** 메인화면 영역 시작 ***** -->
	<section class="dorne-welcome-area bg-img bg-overlay"
		style="background-image: url(/kido/img/bg-img/bg-1.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center justify-content-center">
				<div class="col-12 col-md-10">
					<div class="hero-content">
						<h2>Discover places near you</h2>
						<h4>This is your best kindergarten guide.</h4>
					</div>
					<!-- Hero Search Form -->
					<div class="hero-search-form">
						<!-- 탭 영역 시작 -->
						<div class="nav nav-tabs" id="heroTab" role="tablist">
							<a class="nav-item nav-link active" id="nav-places-tab" data-toggle="tab" href="#nav-places" role="tab" aria-controls="nav-places" aria-selected="true">Review</a>
							<a class="nav-item nav-link" id="nav-events-tab" data-toggle="tab" href="#nav-events" role="tab" aria-controls="nav-events" aria-selected="false">Free</a>
						</div>
						<!-- 탭 영역 종료 -->
						<!-- 탭 내용 영역 시작 -->
						<div class="tab-content" id="nav-tabContent">
							<div class="tab-pane fade show active" id="nav-places" role="tabpanel" aria-labelledby="nav-places-tab">
								<h6>무엇을 찾고 계십니까?</h6>
								<form action="/main/ReviewList_search.do" method="post">
									<select id="sido" name="sidoCode" class="custom-select" onchange="getList(this.selectedIndex)">
										<option>지역</option>
										<option value="서울특별시">서울특별시</option>
									</select> 
									<select id="sigungu" name="sggCode" class="custom-select">
										<option selected>행정구역</option>
										<option value="강남구">강남구</option>
										<option value="강동구">강동구</option>
										<option value="강북구">강북구</option>
										<option value="강서구">강서구</option>
										<option value="관악구">관악구</option>
										<option value="광진구">광진구</option>
										<option value="구로구">구로구</option>
										<option value="금천구">금천구</option>
										<option value="노원구">노원구</option>
										<option value="도봉구">도봉구</option>
										<option value="동대문구">동대문구</option>
										<option value="동작구">동작구</option>
										<option value="마포구">마포구</option>
										<option value="서대문구">서대문구</option>
										<option value="서초구">서초구</option>
										<option value="성동구">성동구</option>
										<option value="성북구">성북구</option>
										<option value="송파구">송파구</option>
										<option value="양천구">양천구</option>
										<option value="영등포구">영등포구</option>
										<option value="용산구">용산구</option>
										<option value="은평구">은평구</option>
										<option value="종로구">종로구</option>
										<option value="중구">중구</option>
										<option value="중랑구">중랑구</option>
									</select> 
									<select class="custom-select" name="type">
										<option selected>유형별</option>
										<option value="공립(단설)">공립(단설)</option>
										<option value="공립(병설)">공립(병설)</option>
										<option value="국립">국립</option>
										<option value="사립(사인)">사립(사인)</option>
										<option value="사립(법인)">사립(법인)</option>
									</select>
									<button type="submit" class="btn dorne-btn">
										<i class="fa fa-search pr-2" aria-hidden="true"></i> Search
									</button>
								</form>
							</div>
							<div class="tab-pane fade" id="nav-events" role="tabpanel" aria-labelledby="nav-events-tab">
								<h6>무엇을 찾고 계십니까?</h6>
								<form action="/main/FreeList_search.do" method="post">
									<select id="sido" name="sidoCode" class="custom-select" onchange="getList(this.selectedIndex)">
										<option>지역</option>
										<option value="서울특별시">서울특별시</option>
									</select> 
									<select id="sigungu" name="sggCode" class="custom-select">
										<option selected>행정구역</option>
										<option value="강남구">강남구</option>
										<option value="강동구">강동구</option>
										<option value="강북구">강북구</option>
										<option value="강서구">강서구</option>
										<option value="관악구">관악구</option>
										<option value="광진구">광진구</option>
										<option value="구로구">구로구</option>
										<option value="금천구">금천구</option>
										<option value="노원구">노원구</option>
										<option value="도봉구">도봉구</option>
										<option value="동대문구">동대문구</option>
										<option value="동작구">동작구</option>
										<option value="마포구">마포구</option>
										<option value="서대문구">서대문구</option>
										<option value="서초구">서초구</option>
										<option value="성동구">성동구</option>
										<option value="성북구">성북구</option>
										<option value="송파구">송파구</option>
										<option value="양천구">양천구</option>
										<option value="영등포구">영등포구</option>
										<option value="용산구">용산구</option>
										<option value="은평구">은평구</option>
										<option value="종로구">종로구</option>
										<option value="중구">중구</option>
										<option value="중랑구">중랑구</option>
									</select> 
									<select class="custom-select" name="type">
										<option selected>유형별</option>
										<option value="공립(단설)">공립(단설)</option>
										<option value="공립(병설)">공립(병설)</option>
										<option value="국립">국립</option>
										<option value="사립(사인)">사립(사인)</option>
										<option value="사립(법인)">사립(법인)</option>
									</select>
									<button type="submit" class="btn dorne-btn">
										<i class="fa fa-search pr-2" aria-hidden="true"></i> Search
									</button>
								</form>
							</div>
						</div>
						<!-- 탭 내용 영역 종료 -->
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- ***** 메인화면 영역 종료 ***** -->
	<!-- ***** 카테고리 영역 시작 ***** -->
	<section class="dorne-catagory-area">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="all-catagories">
						<div class="row">
							<!-- 1 싱글 카테고리 영역 시작 -->
							<div class="col-12 col-sm-6 col-md">
								<div class="single-catagory-area wow fadeInUpBig" data-wow-delay="0.6s">
									<div class="catagory-content" onclick="location.href='/main/ReviewList.do'">
										<a href="#"><h6>리뷰게시판</h6></a>
									</div>
								</div>
							</div>
							<!-- 1 싱글 카테고리 영역 종료 -->
							<!-- 2 싱글 카테고리 영역 시작 -->
							<div class="col-12 col-sm-6 col-md">
								<div class="single-catagory-area wow fadeInUpBig" data-wow-delay="0.8s">
									<div class="catagory-content" onclick="location.href='/main/FreeList.do'">
										<a href="#"><h6>자유게시판</h6></a>
									</div>
								</div>
							</div>
							<!-- 2 싱글 카테고리 영역 종료-->							
							<!-- 3 싱글 카테고리 영역 시작 -->
							<div class="col-12 col-sm-6 col-md">
								<div class="single-catagory-area wow fadeInUpBig" data-wow-delay="0.8s">
									<div class="catagory-content" onclick="location.href='/Map_search.do'">
										<a href="#"><h6>유치원 조회</h6></a>
									</div>
								</div>
							</div>
							<!-- 3 싱글 카테고리 영역 종료-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- ***** 카테고리 영역 종료 ***** -->
	<!-- ***** About 영역 시작 ***** -->
	<section class="dorne-about-area section-padding-0-100">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="about-content text-center">
						<h2>KIDO</h2>
						<small>어린 아이들이 존재는 이 세상에서 가장 빛나는 은혜다. 죄악에 물들지 않은 어린 아이들의
							생명체는 한없이 고귀하다. 우리는 어린 아이들에게서 미(美)를 발견하고 행복을 느낀다. 어린 아이들에게서만 이
							세상에서 천국의 그림자를 엿볼 수 있다. 어린 아이들의 생활은 고스란히 천국에 속한다. H.F. 아미엘</small>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- ***** About 영역 종료 ***** -->
	<!-- ***** KIDO Pick 영역 시작 ***** -->
	<section class="dorne-editors-pick-area bg-img bg-overlay-9 section-padding-100" style="background-image: url(img/bg-img/hero-2.jpg);">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="section-heading text-center">
						<span></span>
						<h4>최근리뷰</h4>
					</div>
				</div>
			</div>
			<div class="row">
				<%
					for (ReviewDTO rDTO : rList) {
				%>
				<%
					String title = CmmUtil.nvl(rDTO.getTitle());
				%>
                <div class="col-12 col-lg-6">
                    <div class="single-feature-events-area d-sm-flex align-items-center wow fadeInUpBig" data-wow-delay="0.2s" style="height:120px; ">
                        <div class="feature-events-content col-md-12" style="margin-left: 10px">
                            <h5 style="margin-bottom: 20px;"><%=title %></h5>
                            <small><%=CmmUtil.nvl(rDTO.getUser_id()) %></small>
                        </div>
                        <div class="feature-events-details-btn">
                            <a href="/ReviewDetail.do?review_seq=<%=CmmUtil.nvl(rDTO.getReview_seq()) %>">+</a>
                        </div>
                    </div>
                </div>
				<% } %>                
            </div>
		</div>
	</section>
	<!-- ***** KIDO Pick 영역 종료 ***** -->


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