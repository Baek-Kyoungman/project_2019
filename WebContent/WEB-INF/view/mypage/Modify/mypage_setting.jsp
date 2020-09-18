<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="poly.dto.UserInfoDTO"%>
<%
	UserInfoDTO pDTO = (UserInfoDTO) request.getAttribute("pDTO");
	
	String email = (String) session.getAttribute("SS_EMAIL");
	String user_id = (String) session.getAttribute("SS_USER_ID");
	String user_job = (String) session.getAttribute("SS_USER_JOB");
%>
<!DOCTYPE html>
<html>
<head>
<title>[마이페이지] 정보 수정</title>
<meta charset="utf-8">
<link rel="icon" href="/mypage/dist/img/core-img/favicon.ico">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/adminx.css" media="screen" />
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/common.css" media="screen" />
</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 마이페이지 - 정보 수정
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
		<%@include file="/WEB-INF/view/mypage/frame/Sidebar5.jsp"%>
		<!-- Sidebar 종료 -->

		<!-- Main Content -->
		<div class="adminx-content">
			<div class="adminx-main-content">
				<div class="container-fluid">
					<!-- BreadCrumb -->
					<nav aria-label="breadcrumb" role="navigation">
						<ol class="breadcrumb adminx-page-breadcrumb">
							<li class="breadcrumb-item"><a href="#">Home</a></li>
							<li class="breadcrumb-item"><a href="#">설정</a></li>
							<li class="breadcrumb-item active  aria-current=page">내 정보
								수정</li>
						</ol>
					</nav>
					<div class="pb-3"></div>
				</div>
				<!--내 정보 수정 form 영역 시작-->
				<div class="row">
					<div class="col-lg-2"></div>
					<div class="col-lg-4">
						<div class="card mb-grid">
							<div
								class="card-header d-flex justify-content-between align-items-center">
								<div class="card-header-title">개인정보</div>
							</div>
							<div class="card-body collapse show" id="card1">
								<form>
									<div class="form-group">
										<label class="form-label">이름</label>
										<div class="form-control" id="exampleInputEmail1"><%=user_name %></div>
									</div>
									<div class="form-group">
										<label class="form-label" for="exampleInputEmail1">아이디</label>
										<div class="form-control" id="exampleInputEmail1"><%=user_id %></div>
									</div>
									<input type="button" onclick="userDel()"
										class="btn btn-danger btn-block" value="회원탈퇴">
								</form>
							</div>
						</div>
						<div class="card mb-grid">
							<div
								class="card-header d-flex justify-content-between align-items-center">
								<div class="card-header-title">비밀번호 변경</div>
							</div>
							<div class="card-body collapse show" id="card1">
								<form method="post" action="/mypage/userPassword.do"
									onsubmit="return update_password();">
									<!--                                         <div class="form-group">
                                            <label class="form-label">현재 비밀번호</label>
                                            <input type="password" class="form-control form-password" name="password">
                                        </div> -->
									<div class="form-group">
										<label class="form-label">새로운 비밀번호</label> <input
											type="password" class="form-control form-margin-bottom"
											name="password"> <label class="form-label">새로운
											비밀번호 확인</label> <input type="password"
											class="form-control form-margin-bottom" name="new_password1">
									</div>
									<!-- <button type="submit" class="btn btn-primary btn-block" onclick="update_password()">비밀번호 변경</button> -->
									<input type="submit" name="update_password"
										class="btn btn-primary btn-block" value="비밀번호 변경">
								</form>
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="card mb-grid">
							<div
								class="card-header d-flex justify-content-between align-items-center">
								<div class="card-header-title">이메일 변경</div>
							</div>
							<div class="card-body collapse show" id="card1">
								<form method="post" action="/mypage/userEmail.do">
									<div class="form-group">
										<label class="form-label" for="exampleInputEmail1">현재
											이메일</label>
										<div class="form-control"><%=email %></div>
									</div>
									<div class="form-group">
										<label class="form-label" for="exampleInputEmail1">새로운
											이메일</label> <input type="name" class="form-control" name="email"
											value="">
									</div>
									<button type="submit" class="btn btn-primary btn-block"
										onclick="button_event_3();">이메일 변경</button>
								</form>
							</div>
						</div>
						<div class="card mb-grid">
							<div
								class="card-header d-flex justify-content-between align-items-center">
								<div class="card-header-title">직업 변경</div>
							</div>
							<div class="card-body collapse show" id="card1">
								<form method="post" action="/mypage/userJob.do">
									<div class="form-group">
										<label class="form-label" for="exampleInputEmail1">직업</label>
										<select class="form-control" name="user_job">
											<option value="<%=user_job %>"><%=user_job %></option>
											<option value="학생">학생</option>
											<option value="주부">주부</option>
											<option value="회사원">회사원</option>
											<option value="자영업자">자영업자</option>
											<option value="기타">기타</option>
										</select>
									</div>
									<button type="submit" class="btn btn-primary btn-block"
										onclick="button_event_4();">직업 변경</button>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!--내 정보 수정 form 영역 종료-->
			</div>
		</div>
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
	    	function userDel() {
	    		//alert($("input:radio[name=userCheck]:checked").val());
	
	    		if (confirm("회원탈퇴 하시겠습니까?") == true) {
	    			document.location.href = "/mypage/userDelete.do?user_id="
	    					+ $("input[name=user_id]").val();
	    		} else {
	    			return false;
	    		}
	    	}
		</script>

	<script>	    	

	    		function update_password() {
	    		
	    		var getPassword= RegExp(/^[a-zA-Z0-9!@#$]{8,20}$/);

	    		//기존 비밀번호와 새로운 비밀번호 일치 확인
	    		if($("input[name=password]").val() === $("input[name=new_password1]").val()){ 
	    			alert("기존 비밀번호와 새로운 비밀번호가 일치합니다")
	    			$("input[name=new_password1]").val("");
	    			$("input[name=new_password1]").focus();
	    			return false; 
	    			}
	    		
	    		//비밀번호 유효성검사 
	    		else if(!getPassword.test($("input[name=password]").val())){
	    			alert("비밀번호 형식에 맞게 입력해주세요"); 
	    			$("input[name=password]").val("");
	    			$("input[name=password]").focus();
	    			return false;
	    			}

	    		//비밀번호 확인 입력여부 검사
	    		else if($("input[name=new_password1]").val()==""){
	    			alert("새로운 비밀번호를 입력해 주세요.")
	    			$("input[name=new_password1]").focus();
	    			return false;
	    		}
	    		
	    		//비밀번호 일치여부 검사
	    		else if($("input[name=new_password1]").val()!=$("input[name=new_password2]").val()){
	    			alert("비밀번호가 일치하지 않습니다.")
	    			$("input[name=new_password2]").val("");
	    			$("input[name=new_password2]").focus();
	    			return false;
	    		}
	    		
 
	    	}
	    		
	    	</script>

	<script>	
	    		
            function button_event_2(){
            if (confirm("비밀번호 변경 하시겠습니까?") == true){    //확인
                document.form.submit();
            }else{   //취소
                return;
                }
            }
            function button_event_3(){
            if (confirm("이메일 변경 하시겠습니까?") == true){    //확인
                document.form.submit();
            }else{   //취소
                return;
                }
            }
            function button_event_4(){
            if (confirm("직업 변경 하시겠습니까?") == true){    //확인
                document.form.submit();
            }else{   //취소
                return;
			}
            
        </script>
</body>
</html>