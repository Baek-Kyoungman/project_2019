<%@ page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="poly.dto.ReviewDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%
	String user_id = (String) session.getAttribute("SS_USER_ID");
	String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

	List<ReviewDTO> sList = (List<ReviewDTO>) request.getAttribute("sList");
	if (sList == null) {
		sList = new ArrayList();
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>[KIDO] 리뷰 게시판 - 검색</title>
<link rel="icon" href="/kido/img/core-img/favicon.ico">
<link href="/kido/css/style.css" rel="stylesheet">
<link href="/kido/css/responsive/responsive.css" rel="stylesheet">
<link href="/kido/css/responsive/board.css" rel="stylesheet">
<link href="/kido/css/Board/board.css" rel="stylesheet">
<link href="/kido/css/Board/board_responsive.css" rel="stylesheet">
<style> 
.r_search { 
	display:none; 
}
</style>
</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 리뷰 게시판 - 검색
	Email: 			1920110016@office.kopo.ac.kr
	//////////////////////////////////////////////////////
-->
<body>
	<!-- Preloader -->
	<!--새로고침시 효과-->
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
	<div class="breadcumb-area bg-img bg-overlay"
		style="background-image: url(/kido/img/bg-img/bg-5.jpg)"></div>
	<!-- ***** Breadcumb Area End ***** -->
	<!-- ***** Single Listing Area Start ***** -->
	<section class="dorne-single-listing-area section-padding-30-100">
		<div class="container align-items-end">
			<div class="row">
				<div class="breadcumb-content-2">
					<div class="hero-search-form">
						<div class="home_content_board">
							<!-- 탭 내용 영역 시작 -->
							<div class="tab-content" id="nav-tabContent">
								<div class="tab-pane-board fade show active" id="nav-places"
									role="tabpanel" aria-labelledby="nav-places-tab">
									<h6>리뷰게시판 필터</h6>
									<form action="/main/ReviewList_search.do" method="post">
										<select id="sido" name="sidoCode" class="custom-select"
											onchange="getList(this.selectedIndex)">
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
										<button type="submit" class="btn board-btn">
											<i class="fa fa-search pr-2" aria-hidden="true"></i>검색
										</button>
										<a href="/main/ReviewReg.do"
											class="btn board-btn-2 board-margin-left">글 작성하기</a>
									</form>
								</div>
							</div>
							<!-- 탭 내용 영역 종료 -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-center" style="min-height: 540px;">
				<!-- Single Listing Content -->
				<div class="listing-reviews-area mt-50" id="review" style="width: 100%">
					<% for (ReviewDTO sDTO : sList) { %>
					<div class="r_search">
						<%
							String title = CmmUtil.nvl(sDTO.getTitle());
							String contents = CmmUtil.nvl(sDTO.getContents());
							String type = CmmUtil.nvl(sDTO.getType());
						%>
						<div class="single-review-area-15 board-box-shadow">
							<div class="reviewer-meta d-flex align-items-center row">
								<div class="reviewer-content col-md-9">
									<div class="review-title-ratings d-flex justify-content-between">
										<a href="/ReviewDetail.do?review_seq=<%=CmmUtil.nvl(sDTO.getReview_seq()) %>"><h5><%=title%></h5></a>
									</div>
								</div>
								<div class="reviewer-name col-md-3">
									<p><%=CmmUtil.nvl(sDTO.getSidoCode()) %> / <%=CmmUtil.nvl(sDTO.getSggCode()) %> / <%=type %></p>
									<p><%=CmmUtil.nvl(sDTO.getUser_id()) %> / <%=CmmUtil.nvl(sDTO.getReg_dt().substring(2,16)) %></p>
								</div>
							</div>
						</div>
					</div>
					<% } %>
					<a href="#" id="load" class="fluid ui button">더보기</a>
				</div>
			</div>
		</div>

	</section>
	<!-- ***** Single Listing Area End ***** -->

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

	<script>
    $(function(){
		$(".r_search").slice(0, 5).show(); // select the first ten
		$("#load").click(function(e){ // click event for load more
		    e.preventDefault();
		    $(".r_search:hidden").slice(0, 5).show(); // select next 10 hidden divs and show them
		    if($(".r_search:hidden").length == 0){ // check if any hidden divs still exist
		    }
		});
		});
    </script>

</body>
</html>