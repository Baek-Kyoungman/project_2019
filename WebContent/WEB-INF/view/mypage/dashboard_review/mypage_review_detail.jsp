<%@ page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="poly.dto.ReviewDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%
	String user_id = (String) session.getAttribute("SS_USER_ID");
	String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

	List<ReviewDTO> fList = (List<ReviewDTO>) request.getAttribute("fList");

	ReviewDTO fDTO = (ReviewDTO) request.getAttribute("fDTO");

	String contents = CmmUtil.nvl(fDTO.getContents());
	String title = CmmUtil.nvl(fDTO.getTitle());
	String type = CmmUtil.nvl(fDTO.getType());
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>[마이페이지] 리뷰게시물</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/adminx.css" media="screen" />
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/common.css" media="screen" />
</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 마이페이지 - 리뷰게시물
	Email: 			1920110016@office.kopo.ac.kr
	//////////////////////////////////////////////////////
-->
<body>
	<div class="adminx-container">

		<!--네비게이션 바 시작-->
		<%@include file="/WEB-INF/view/mypage/frame/Topbar.jsp"%>
		<!--네비게이션 바 종료-->

		<!-- Sidebar 영역 시작 -->
		<%@include file="/WEB-INF/view/mypage/frame/Sidebar.jsp"%>
		<!-- Sidebar 종료 -->

		<!-- Main Content -->
		<div class="adminx-content">
			<div class="adminx-main-content">
				<div class="container-fluid">
					<!-- BreadCrumb -->
					<nav aria-label="breadcrumb" role="navigation">
						<ol class="breadcrumb adminx-page-breadcrumb">
							<li class="breadcrumb-item"><a href="#">Home</a></li>
							<li class="breadcrumb-item"><a href="#">대시보드</a></li>
							<li class="breadcrumb-item active  aria-current=page">리뷰게시물</li>
						</ol>
					</nav>
					<div class="pb-3"></div>
					<!--게시글 상세 및 수정 영역 시작-->
					<div class="row">
						<div class="col-lg-12">
							<div>
								<input type="hidden" name="review_seq" value="<%=CmmUtil.nvl(fDTO.getReview_seq())%>">
								<div>
									<div>
										<h4 class="mt-4 mb-3"><%=title%></h4>
										<hr>
										<p class="mt-4 mb-3"><%=CmmUtil.nvl(fDTO.getSidoCode())%>
											/
											<%=CmmUtil.nvl(fDTO.getSggCode())%>
											/
											<%=type%></p>
										<hr>
										<div class="">
											<!-- Post Content Column -->
											<div class="notice_box">
												<!-- Date/Time -->
												<p>
													<%=CmmUtil.nvl(fDTO.getChg_dt())%>
													<small>by <%=CmmUtil.nvl(fDTO.getUser_id())%></small>
												</p>
												<hr>
												<!-- Post Content -->
												<div id="notice_content" class="board-area"
													style="width: 100%">
													<p>
														<%=contents%>
													</p>
												</div>
											</div>
											<!-- /.col-lg-8 -->
											<br>
											<hr>
											<div class="notice_btn_box">
												<a class="btn btn-board btn-sm" href="/mypage/dashboard_review.do">목록으로</a> 
												<a href="/mypage/Mypage_review_modify.do?review_seq=<%=CmmUtil.nvl(fDTO.getReview_seq())%>" class="btn btn-board btn-sm">수정하기</a>
												<a href="/mypage/reviewDelete.do?review_seq=<%=CmmUtil.nvl(fDTO.getReview_seq())%>" class="btn btn-board btn-sm pull-right">삭제하기</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--게시글 상세 및 수정 영역 종료-->
				</div>
			</div>
		</div>
		<!-- // Main Content -->
	</div>
	<!-- If you prefer jQuery these are the required scripts -->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
	<script src="/mypage/dist/js/vendor.js"></script>
	<script src="/mypage/dist/js/adminx.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js"></script>
	<script>
		$(document).ready(function() {
			var table = $('[data-table]').DataTable({
				"columns" : [ null, null, null, null, null, {
					"orderable" : false
				} ]
			});
		});
	</script>

	<script type="text/javascript">
	function freeDel() {
	
		if (confirm("삭제 하시겠습니까?") == true) {
			document.location.href = "/mypage/reviewDelete.do?review_seq=" + "<%=CmmUtil.nvl(fDTO.getReview_seq())%>".val();
			} else {
				return false;
			}
		}

		function freeModify() {

			if (confirm("수정 하시겠습니까?") == true) {
				document.location.href = "/review/reviewModify.do?user_id="
						+ $("input[name=user_id]").val();
			} else {
				return false;
			}
		}
	</script>
</body>
</html>