<%@ page import="poly.dto.R_CommentDTO"%>
<%@ page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="poly.dto.ReviewDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%
	String user_id = (String) session.getAttribute("SS_USER_ID");
	String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

	ReviewDTO fDTO = (ReviewDTO) request.getAttribute("fDTO");
	String content = CmmUtil.nvl(fDTO.getContents());
	String title = CmmUtil.nvl(fDTO.getTitle());
	String type = CmmUtil.nvl(fDTO.getType());

	String review_seq = fDTO.getReview_seq();
	
	List<R_CommentDTO> rList = (List<R_CommentDTO>) request.getAttribute("rList");
	if (rList == null) {
		rList = new ArrayList();
	}

	R_CommentDTO cDTO = (R_CommentDTO) request.getAttribute("cDTO");

	String board_seq = (String) request.getAttribute("board_seq");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>[KIDO] 리뷰게시물 - 상세 게시글</title>
<link rel="icon" href="/kido/img/core-img/favicon.ico">
<link href="/kido/css/style.css" rel="stylesheet">
<link href="/kido/css/responsive/responsive.css" rel="stylesheet">
<link href="/kido/css/others/kido.css" rel="stylesheet">
<link href="/kido/css/others/test.css" rel="stylesheet">
<link href="/kido/css/others/board.css" rel="stylesheet">

<style>
textarea {
	width: 100%;
	overflow: visible;
	height: 100px;
}
.r_comment { 
	display:none; 
}
</style>
</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 리뷰게시물 - 상세 게시글
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
		style="background-image: url(/kido/img/bg-img/seoul_2.jpg)"></div>
	<!-- ***** Breadcumb Area End ***** -->
	<!-- Page Content -->
	<div class="container pageDetail-area">
		<!-- Page Heading/Breadcrumbs -->
		<h4 class="mt-4 mb-3"><%=title%></h4>
		<div class="row">
			<!-- Post Content Column -->
			<div class="col-lg-12 detail-area">
				<hr>
				<p class="mt-4 mb-3"><%=CmmUtil.nvl(fDTO.getSidoCode()) %> / <%=CmmUtil.nvl(fDTO.getSggCode()) %> / <%=type %> </p>
				<hr>
				<!-- Date/Time -->
				<p><%=CmmUtil.nvl(fDTO.getRead_cnt())%>
					<small>by <%=CmmUtil.nvl(fDTO.getUser_id())%>
					</small> <span style="float: right; margin-right: 15px;">조회수 : <%=CmmUtil.nvl(fDTO.getRead_cnt())%></span>
				</p>
				<hr>
				<!-- Post Content -->
				<p><%=content%>
				</p>
			</div>
			<div class="container comment-bottom">
				<hr>
				<!-- 댓글 작성 form 시작 -->
				<form action="/r_commentAdd_Proc.do" method="POST">
					<div class="col-lg-12">
						<h6>댓글</h6>
						<input type="hidden" value="<%=board_seq%>" name="board_seq">
						<textarea name="content" id="" cols="30" rows="10"></textarea>
						<input type="submit" class="btn board-btn" value="등록">
					</div>
					<hr style="margin-top: 50px;">
				</form>
				<!-- 댓글 작성 form 종료 -->

				<%
					for (int i = 0; i < rList.size(); i++) {
				%>
					<script type="text/javascript">
				        var bDisplay = true;
				        function Display<%=rList.get(i).getR_seq() %>() {
				            var display_1_<%=rList.get(i).getR_seq() %> = document.getElementById("display_1_<%=rList.get(i).getR_seq() %>"); // 수정 버튼 클릭 전 보여 줄 댓글
				            var display_2_<%=rList.get(i).getR_seq() %> = document.getElementById("display_2_<%=rList.get(i).getR_seq() %>"); // 수정 버튼 클릭 후 보여 줄 댓글
				            if (display_2_<%=rList.get(i).getR_seq() %>.style.display == 'none') {
				            	display_1_<%=rList.get(i).getR_seq() %>.style.display = 'none';
				            	display_2_<%=rList.get(i).getR_seq() %>.style.display = 'block';
				            } else {
				            	display_2.style.display = 'none';
				            }
				        }
				        //취소하기
				        function Display_2<%=rList.get(i).getR_seq() %>() {
				            var display_1_<%=rList.get(i).getR_seq() %> = document.getElementById("display_1_<%=rList.get(i).getR_seq() %>"); // 수정 버튼 클릭 전 보여 줄 댓글
				            var display_2_<%=rList.get(i).getR_seq() %> = document.getElementById("display_2_<%=rList.get(i).getR_seq() %>"); // 수정 버튼 클릭 후 보여 줄 댓글
				            if (display_1_<%=rList.get(i).getR_seq() %>.style.display == 'none') {
				            	display_2_<%=rList.get(i).getR_seq() %>.style.display = 'none';
				            	display_1_<%=rList.get(i).getR_seq() %>.style.display = 'block';
				            } else {
				            	display_1.style.display = 'none';
				            }
				        }
				        
				    	// 댓글 수정 완료 버튼
				    	function Modify<%=rList.get(i).getR_seq() %>(){
							   if (confirm("댓글을 수정하시겠습니까?") == true) {
							   document.location.href = "/r_commentModify.do?r_seq="+<%=rList.get(i).getR_seq()%>
							   +"&board_seq="+<%=board_seq%>
							   +"&content="+$("#content"+<%=rList.get(i).getR_seq() %>).val();
							      return true;
							   } else {
							      return false;
							   }
							}
				    </script>
				<!-- 댓글 리스트 form 시작 -->

				<!-- 숨겨져 있는 수정 부분 시작 -->
				<div class="r_comment">
					<div id="display_2_<%=rList.get(i).getR_seq()%>" style="display: none;">
						<div class="col-lg-12 user-comment">
								<input type="hidden" value="<%=rList.get(i).getBoard_seq()%>" name="board_seq"> 
								<input type="hidden" value="<%=rList.get(i).getR_seq()%>" name="r_seq">
								<div class="col-md-12 col-md-offset-0">
									<div class="row">
										<!-- 댓글 수정 textarea 부분 시작 -->
										<div class="forumText forumMax col-md-10">
											<input type="text" value="<%=rList.get(i).getContent()%>" name="content<%=rList.get(i).getR_seq() %>" id="content<%=rList.get(i).getR_seq() %>" class="comment-st col-md-10">
										</div>
										<!-- 댓글 수정 textarea 부분 종료 -->
										<div class="col-md-2">
											<input type="button" class="btn comment-btn-free" value="완료" onclick="Modify<%=rList.get(i).getR_seq() %>()">
											<a href="javascript:Display_2<%=rList.get(i).getR_seq()%>();" class="btn comment-btn-free">취소</a>
										</div>
									</div>
									<div class="row">
										<div class="forumTagline col-md-10">
											<p class="forumName text-muted">
												<%=rList.get(i).getWriter()%>
												/
												<%=rList.get(i).getReg_dt().substring(2, 16)%>
											</p>
										</div>
									</div>
								</div>
						</div>
						<hr style="margin-top: 0rem;">
					</div>
				<!-- 숨겨져 있는 수정 부분 종료 -->

				<!-- 기존 댓글이 보여줄 부분 display:block로 보여줌, 수정 버튼 클릭 시  javascript로 인해 display:none으로 바뀜  시작-->
					<div id="display_1_<%=rList.get(i).getR_seq()%>" style="display: block;">
						<div class="col-lg-12 user-comment">
							<input type="hidden" value="<%=rList.get(i).getBoard_seq()%>" name="board_seq"> 
							<input type="hidden" value="<%=rList.get(i).getR_seq()%>" name="r_seq">
							<div class="col-md-12 col-md-offset-0">
								<div class="row">
									<div class="forumText forumMax col-md-10">
										<p style="height: 30px;"><%=rList.get(i).getContent()%></p>
									</div>
									<div class="col-md-2">
										<% if (user_id != null) { %>
											<% if (rList.get(i).getWriter().equals(user_id)) { %>
												<a href="javascript:Display<%=rList.get(i).getR_seq()%>();" class="btn comment-btn-free">수정</a> 
												<a href="/r_commentDelete.do?r_seq=<%=rList.get(i).getR_seq() %>&board_seq=<%=board_seq %>" class="btn comment-btn-free">삭제</a>
											<% } %>
										<% } %>
									</div>
								</div>
								<div class="row">
									<div class="forumTagline col-md-10">
										<p class="forumName text-muted">
											<%=rList.get(i).getWriter()%>
											/
											<%=rList.get(i).getReg_dt().substring(2, 16)%>
										</p>
									</div>
								</div>
							</div>
						</div>
						<hr style="margin-top: 0rem;">
					</div>
				</div>
				<!-- 기존 댓글이 보여줄 부분 display:block로 보여줌, 수정 버튼 클릭 시  javascript로 인해 display:none으로 바뀜  종료-->
				<!-- 댓글 리스트 form 종료 -->
				<% } %>
				<div onclick="" id="load" class="comment_list_btn col-md-12">더보기</div>
				<div class="col-md-12">  
					<% if (user_id != null) { %>
						<% if (CmmUtil.nvl(fDTO.getReg_id()).equals(user_id)) { %>
							<form action="/ReviewDelete.do" method="post" id="delete">
								<input type="hidden" name="review_seq" value="<%=CmmUtil.nvl(fDTO.getReview_seq())%>"> 
								<a href="/reviewModify.do?review_seq=<%=CmmUtil.nvl(fDTO.getReview_seq()) %>" class="btn_list" style="margin-left: 10px;">수정</a> 
								<a href="#" onclick="document.getElementById('delete').submit();" class="btn_list" style="margin-left: 10px;">삭제</a>
							</form>
						<% } %>
					<% } %>
					<a href="/main/ReviewList.do?pgNum=1" class="btn_list">목록</a>
				</div>
			</div>
			<!-- /.col-lg-8 -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /Page Content -->

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
		$(".r_comment").slice(0, 5).show();
		$("#load").click(function(e){ 
		    e.preventDefault();
		    $(".r_comment:hidden").slice(0, 5).show();
		    if($(".r_comment:hidden").length == 0){
		    }
		});
		});
    </script>
</body>
</html>