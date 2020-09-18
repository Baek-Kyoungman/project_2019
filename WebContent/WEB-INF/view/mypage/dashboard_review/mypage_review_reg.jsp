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
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>[마이페이지] 리뷰게시물 - 등록</title>
<link rel="icon" href="/mypage/dist/img/core-img/favicon.ico">

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/adminx.css" media="screen" />
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/common.css" media="screen" />
<link href="/mypage/dist/css/Board/board.css" rel="stylesheet">
</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 사용자 마이페이지 - 리뷰게시물 등록
	Email: 			1920110016@office.kopo.ac.kr
	//////////////////////////////////////////////////////
-->
<body>

	<div class="adminx-container">

		<!--네비게이션 바 시작-->
		<%@include file="/WEB-INF/view/mypage/frame/Topbar.jsp"%>
		<!--네비게이션 바 종료-->
		<!-- expand-hover push -->

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
							<li class="breadcrumb-item active  aria-current= page">리뷰게시물</li>
						</ol>
					</nav>
					<div class="pb-3"></div>
					<!--게시글 상세 및 수정 영역 시작-->
					<div class="notice_container">
						<div class="container-fluid">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="cont_title">게시글 작성</h6>
								</div>
								<div class="card-body">
									<form method="post" id="regform"
										action="/mypage/Mypage_ReviewRegProc.do">
										<div class="notice_box">											
											<div class="table_title_box">
												<div>
													<div class="row">
														<div class="table_title col-lg-2">게시판</div>
														<div class="col-lg-10">
															<!-- 탭 내용 영역 시작 -->
															<div class="tab-content" id="nav-tabContent">
																<div class="tab-pane fade show active" id="nav-places"
																	role="tabpanel" aria-labelledby="nav-places-tab">
																	<select id="sido" name="sidoCode" class="custom-select" onchange="getList(this.selectedIndex)" style="width: 150px;">
																		<option>지역</option>
																		<option value="서울특별시">서울특별시</option>
																	</select> 
																	<select id="sigungu" name="sggCode"
																		class="custom-select" style="width: 150px;">
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
																	<select class="custom-select" name="type" style="width: 150px;">
																		<option selected>유형별</option>
																		<option value="공립(단설)">공립(단설)</option>
																		<option value="공립(병설)">공립(병설)</option>
																		<option value="국립">국립</option>
																		<option value="사립(사인)">사립(사인)</option>
																		<option value="사립(법인)">사립(법인)</option>
																	</select>
																</div>
															</div> <!-- 탭 내용 영역 종료 -->
														</div>
													</div>
												</div>
											</div>
											<hr class="notice_hr_inner">
											<div class="table_title_box">
												<div>
													<div class="row">
														<div class="table_title col-lg-2">제목</div>
														<div class="col-lg-10">
                                                            <input type="text" class="table_input_text" placeholder="제목을 입력해주세요( 50 자 이하 )" name="title" id="title" maxlength="50" style="width: 100%; padding-left: 0.5em;">
                                                        </div>
													</div>
												</div>
											</div>
											<hr class="notice_hr_inner">
											<div class="table_cont_box">
												<div>
													<div class="row">
														<div class="table_title col-lg-2">내용</div>
														<!-- <div><input type="text" class="table_input_text" placeholder="내용을 입력해주세요" name="title" id="smarteditor" style="width: 100%; height: 500px; padding-left: 0.5em;"></div> -->
														<div class="col-lg-10">
                                                            <textarea class="table_textbox" name="contents" id="smarteditor" style="width: 100%; height: 400px; padding-left: 0.5em;"></textarea>
                                                        </div>
													</div>
												</div>
											</div>
											<hr class="notice_hr">
											<div class="notice_btn_box">
												<input type="submit" id="reg_btn" class="btn btn-primary btn_list" value="등록하기"> 
												<input type="button" class="btn btn-danger btn_list" onclick="location.href='/mypage/dashboard_review.do'" value="취소하기">
											</div>
										</div>
									</form>
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
			/* $('.form-control-search').keyup(function(){
			table.search($(this).val()).draw() ;
			}); */
		});
	</script>
	<!-- If you prefer vanilla JS these are the only required scripts -->
	<!-- script src="../dist/js/vendor.js"></script>
            <script src="../dist/js/adminx.vanilla.js"></script-->
	<script>
		function button_event1() {
			if (confirm("변경사항이 저장되지 않을 수 있습니다.") == true) { //확인
				location.href = "/mypage/reviewDelete.do"
			} else { //취소
				return;
			}
		}
	</script>

	<script src="/SE2/js/HuskyEZCreator.js"></script>
	<script type="text/javascript">
		var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

		$(document).ready(
				function() {
					// Editor Setting
					nhn.husky.EZCreator.createInIFrame({
						oAppRef : oEditors, // 전역변수 명과 동일해야 함.
						elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
						sSkinURI : "/SE2/SmartEditor2Skin.html", // Editor HTML
						fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
						htParams : {
							// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
							bUseToolbar : true,
							// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
							bUseVerticalResizer : true,
							// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
							bUseModeChanger : true,
						}
					});

					// 전송버튼 클릭이벤트
					$("#reg_btn").click(
							function() {
								if (confirm("저장하시겠습니까?")) {
									// id가 smarteditor인 textarea에 에디터에서 대입
									oEditors.getById["smarteditor"].exec(
											"UPDATE_CONTENTS_FIELD", []);

									// 이부분에 에디터 validation 검증
									//	if (validation()) {
									//	$("#regform").submit();
									//}
								}
							})
				});

		// 필수값 Check
		function validation() {
			var contents = $.trim(oEditors[0].getContents());
			if (contents === '<p>&nbsp;</p>' || contents === '') { // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
				alert("내용을 입력하세요.");
				oEditors.getById['smarteditor'].exec('FOCUS');
				return false;
			}

			return true;
		}

		// 글쓰기 저장 & 수정
		// function fWrite() {
		// 	if(validation()){
		// 		if(confirm("저장하시겠습니까?")) {
		// 			oEditors[0].exec("UPDATE_CONTENTS_FIELD", []); // Editor내용을 DB에 가져가기 위해 필수로 작성!
		// 														   // oEditors << 전역변수로 선언한 변수명과 동일해야 함.
		// 			$("#boardForm").attr('action','${pageContext.request.contextPath}/save').submit();
		// 		}
		// 	}
		// }
	</script>
</body>
</html>