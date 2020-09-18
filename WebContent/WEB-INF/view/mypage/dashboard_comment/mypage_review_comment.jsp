<%@ page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="poly.dto.R_CommentDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%
	List<R_CommentDTO> nList = (List<R_CommentDTO>) request.getAttribute("nList");
	if (nList == null) {
		nList = new ArrayList();
	}
	
	int pgNum = (int) request.getAttribute("pgNum");
	int total = (int) request.getAttribute("total");
	int totalpg = (total - 1) / 10 + 1;
%>
<!DOCTYPE html>
<html>
<head>
<title>[마이페이지] 댓글</title>
<meta charset="utf-8">
<link rel="icon" href="/mypage/dist/img/core-img/favicon.ico">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/adminx.css" media="screen" />
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/common.css" media="screen" />
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/table.css" media="screen" />
<link href="/kido/css/others/test.css" rel="stylesheet">

<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 마이페이지 - 리뷰게시물_댓글
	Email: 			1920110016@office.kopo.ac.kr
	//////////////////////////////////////////////////////
-->
<body>

	<div class="adminx-container">

		<!--네비게이션 바 시작-->
		<%@include file="/WEB-INF/view/mypage/frame/Topbar.jsp"%>
		<!--네비게이션 바 종료-->

		<!-- Sidebar 영역 시작 -->
		<%@include file="/WEB-INF/view/mypage/frame/Sidebar3.jsp"%>
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
							<li class="breadcrumb-item active  aria-current=page">리뷰게시물_댓글</li>
						</ol>
					</nav>
					<div class="pb-3"></div>

					<!--댓글 컨텐츠 영역 시작-->
					<div class="container table-area">
						<div class="row">
							<div class="col-lg-12">
								<div class="table table-striped" style="width: 100%; min-height: 470px;">
									<div>
										<div class="row">
											<div class="col-md-1 cell-2 cell-top">No</div>
											<div class="col-md-5 cell-3 cell-top">댓글</div>
											<div class="col-md-2 cell-4 cell-top">작성자</div>
											<div class="col-md-2 cell-5 cell-top">작성일</div>
											<div class="col-md-2 cell-1 cell-top">선택</div>										
										</div>
									</div>
									<div>
										<%
											for (R_CommentDTO nDTO : nList) {
										%>
										<%
											String content = CmmUtil.nvl(nDTO.getContent());
											String reg_dt = CmmUtil.nvl(nDTO.getReg_dt());
										%>
										<script type="text/javascript">
									        var bDisplay = true;
									        function Display<%=CmmUtil.nvl(nDTO.getR_seq()) %>() {
									            var display_1_<%=CmmUtil.nvl(nDTO.getR_seq()) %> = document.getElementById("display_1_<%=CmmUtil.nvl(nDTO.getR_seq()) %>"); // 수정 버튼 클릭 전 보여 줄 댓글
									            var display_2_<%=CmmUtil.nvl(nDTO.getR_seq()) %> = document.getElementById("display_2_<%=CmmUtil.nvl(nDTO.getR_seq()) %>"); // 수정 버튼 클릭 후 보여 줄 댓글
									            if (display_2_<%=CmmUtil.nvl(nDTO.getR_seq()) %>.style.display == 'none') {
									            	display_1_<%=CmmUtil.nvl(nDTO.getR_seq()) %>.style.display = 'none';
									            	display_2_<%=CmmUtil.nvl(nDTO.getR_seq()) %>.style.display = 'block';
									            } else {
									            	display_2.style.display = 'none';
									            }
									        }
									        //취소하기
									        function Display_2<%=CmmUtil.nvl(nDTO.getR_seq()) %>() {
									            var display_1_<%=CmmUtil.nvl(nDTO.getR_seq()) %> = document.getElementById("display_1_<%=CmmUtil.nvl(nDTO.getR_seq()) %>"); // 수정 버튼 클릭 전 보여 줄 댓글
									            var display_2_<%=CmmUtil.nvl(nDTO.getR_seq()) %> = document.getElementById("display_2_<%=CmmUtil.nvl(nDTO.getR_seq()) %>"); // 수정 버튼 클릭 후 보여 줄 댓글
									            if (display_1_<%=CmmUtil.nvl(nDTO.getR_seq()) %>.style.display == 'none') {
									            	display_2_<%=CmmUtil.nvl(nDTO.getR_seq()) %>.style.display = 'none';
									            	display_1_<%=CmmUtil.nvl(nDTO.getR_seq()) %>.style.display = 'block';
									            } else {
									            	display_1.style.display = 'none';
									            }
									        }
									        
									    	// 댓글 수정 완료 버튼
									    	function Modify<%=CmmUtil.nvl(nDTO.getR_seq()) %>(){
												   if (confirm("댓글을 수정하시겠습니까?") == true) {
												   document.location.href = "/mypage_r_commentModify.do?r_seq="+<%=CmmUtil.nvl(nDTO.getR_seq())%>
												   +"&board_seq="+<%=CmmUtil.nvl(nDTO.getBoard_seq())%>
												   +"&content="+$("#content"+<%=CmmUtil.nvl(nDTO.getR_seq()) %>).val();
												      return true;
												   } else {
												      return false;
												   }
												}
									    </script>
									    <!-- 수정 댓글 부분 -->
										<div id="display_2_<%=CmmUtil.nvl(nDTO.getR_seq())%>" style="display: none;">
												<div class="row">
													<input type="hidden" value="<%=CmmUtil.nvl(nDTO.getR_seq())%>" name="r_seq">
													<input type="hidden" value="<%=CmmUtil.nvl(nDTO.getBoard_seq())%>" name="board_seq">
													<div class="col-md-1 cell-2"><%=CmmUtil.nvl(nDTO.getR_seq())%></div>
													<div class="col-md-5 cell-3"><input type="text" value="<%=content%>" name="content<%=CmmUtil.nvl(nDTO.getR_seq())%>" id="content<%=CmmUtil.nvl(nDTO.getR_seq())%>" class="col-md-12"></div>
													<div class="col-md-2 cell-4"><%=CmmUtil.nvl(nDTO.getWriter()) %></div>
													<div class="col-md-2 cell-5"><%=reg_dt %></div>
													<div class="col-md-2 cell-1">
														<input type="button" class="btn comment-btn-free" value="완료" onclick="Modify<%=CmmUtil.nvl(nDTO.getR_seq()) %>()" style="margin-top: -10px">
														<a href="javascript:Display_2<%=CmmUtil.nvl(nDTO.getR_seq())%>();" class="btn comment-btn-free" style="margin-top: -10px">취소</a>
													</div>		
												</div>
										</div>
										
										<!-- 기존 댓글 -->
										<div id="display_1_<%=CmmUtil.nvl(nDTO.getR_seq())%>" style="display: block;">
											<div class="row">
												<input type="hidden" value="<%=CmmUtil.nvl(nDTO.getR_seq())%>" name="r_seq">
												<input type="hidden" value="<%=CmmUtil.nvl(nDTO.getBoard_seq())%>" name="board_seq">
												<div class="col-md-1 cell-2"><%=CmmUtil.nvl(nDTO.getR_seq())%></div>
												<div class="col-md-5 cell-3"><%=content%></div>
												<div class="col-md-2 cell-4"><%=CmmUtil.nvl(nDTO.getWriter()) %></div>
												<div class="col-md-2 cell-5"><%=reg_dt %></div>
												<div class="col-md-2 cell-1">
													<a href="javascript:Display<%=CmmUtil.nvl(nDTO.getR_seq())%>();" class="btn comment-btn-free" style="margin-top: -10px">수정</a>
													<a href="/mypage_r_commentDelete.do?r_seq=<%=CmmUtil.nvl(nDTO.getR_seq()) %>" class="btn comment-btn-free" style="margin-top: -10px">삭제</a>
												</div>										
											</div>
										</div>
										<%
											}
										%>
									</div>
									<br>
								</div>
								<div class="container">
									<div class="row justify-content-center">
										<div class="#">
											<nav>
												<ul class="pagination">
													<li class="#">
														<% 
															if (pgNum == 1) {
														%> <a href="#">&lt;</a> <%
														 	} else {
	 													%> <a
														href="/mypage/dashboard_review_comment.do?pgNum=<%=pgNum - 1%>">&lt;</a>
														<% 
	 														}
	 													%>
													</li>
													<li class="#">
														<%
															if (totalpg < 5) {
														%> <%
	 														for (int i = 1; i <= totalpg; i++) {
	 													%> <%
	 														if (i == pgNum) {
	 													%>
													
													<li class="#"><span><%=pgNum%></span></li>
													<%
														} else {
													%>
													<li class="#"><a
														href="/mypage/dashboard_review_comment.do?pgNum=<%=i%>"><%=i%></a></li>
													<%
														}
													%>
													<%
														}
													%>
													<%
														} else {
													%>
													<%
														if (pgNum < 4) {
													%>
													<%
														for (int i = 1; i < 6; i++) {
													%>
													<%
														if (i == pgNum) {
													%>
													<li class="#"><span><%=pgNum%></span></li>
													<%
														} else {
													%>
													<li class="#"><a
														href="/mypage/dashboard_review_comment.do?pgNum=<%=i%>"><%=i%></a></li>
													<%
														}
													%>
													<%
														}
													%>
													<%
														} else {
													%>
													<%
														for (int i = pgNum - 2; i < pgNum + 3; i++) {
													%>
													<%
														if (i == pgNum) {
													%>
													<li class="#"><span><%=pgNum%></span></li>
													<%
														} else {
													%>
													<li class="#"><a
														href="/mypage/dashboard_review_comment?pgNum=<%=i%>"><%=i%></a></li>
													<%
														}
													%>
													<%
														}
													%>
													<%
														}
													%>
													<%
														}
													%>
													</li>
													<li class="#">
														<%
															if (pgNum == totalpg) {
														%> <a href="#">&gt;</a> <%
	 														} else {
	 													%> <a
														href="/mypage/dashboard_review_comment.do?pgNum=<%=pgNum + 1%>">&gt;</a>
														<%
															}
														%>
													</li>
												</ul>
											</nav>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--자유 컨텐츠 영역 종료-->
				</div>
			</div>
		</div>
		<!-- Main Content 종료 -->
	</div>
	<!-- If you prefer jQuery these are the required scripts -->
	<!--Common_script 시작-->
	<%@include file="/WEB-INF/view/mypage/frame/Common_script.jsp"%>
	<!--Common_script 종료-->

	<script>
            $(document).ready(function () {
                var table = $('[data-table]').DataTable({
                    "columns": [
                        null,
                        null,
                        null,
                        null,
                        null, {
                            "orderable": false
                        }
                    ]
                });
            });
        </script>

	<script type="text/javascript">
            
        function modifyDel() {

        	if (confirm("삭제하시겠습니까?") == true) {
        		document.location.href = "/mypage_r_commentDelete.do?r_seq=" + $("input:radio[name=chk]:checked").val();
        	} else {
        		return false;
        	}
        }
	</script>


</body>
</html>