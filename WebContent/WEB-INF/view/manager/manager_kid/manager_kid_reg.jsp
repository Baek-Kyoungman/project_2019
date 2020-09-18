<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="poly.dto.KidInfoDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%
	String user_id = (String) session.getAttribute("SS_USER_ID");
	String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

	KidInfoDTO nDTO = (KidInfoDTO) request.getAttribute("nDTO");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>[관리페이지] 유치원 - 등록</title>
<link rel="icon" href="/mypage/dist/img/core-img/favicon.ico">

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/adminx.css" media="screen" />
<link rel="stylesheet" type="text/css" href="/mypage/dist/css/common.css" media="screen" />
<link href="/kido/css/kindDetail.css" rel="stylesheet">

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey= 716487d25b6b667214450a2f67a37a1e&libraries=services"></script>
<style>
    .lg-3-st{
        background: #f3f3f3;
        padding: 10px 10px;
        font-weight: bold;
        color: #333;
        border-bottom: 1px solid #dbdbdb;
        border-left: 1px solid #dbdbdb;
        line-height: 16px;
        letter-spacing: -0.5px;
        font-size: 0.85rem;
        font-family: -apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Helvetica Neue,Arial,sans-serif;
    }
    .lg-9-st{
        border-bottom: 1px solid #dbdbdb;
        border-left: 1px solid #dbdbdb;
        padding: 10px 10px;
        line-height: 16px;
    }
    .input-st{
        display: block;
        line-height: 1.5;
        color: #495057;
        background-color: #fff;
        border: 1px solid #ced4da;
        border-radius: .25rem;
        padding: 6.8px;
    }
    .input-padding{
    	padding: 13px;
    }
</style>
</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 관리페이지 - 유치원 등록
	Email: 			1920110016@office.kopo.ac.kr
	//////////////////////////////////////////////////////
-->
<body>
	<div class="adminx-container">

		<!--네비게이션 바 시작-->
		<%@include file="/WEB-INF/view/manager/frame/Topbar.jsp"%>
		<!--네비게이션 바 종료-->

		<!-- Sidebar 영역 시작 -->
		<%@include file="/WEB-INF/view/manager/frame/Sidebar4.jsp"%>
		<!-- Sidebar 종료 -->

		<!-- Main Content -->
		<div class="adminx-content">
			<div class="adminx-main-content">
				<div class="container-fluid">
					<!-- BreadCrumb -->
					<nav aria-label="breadcrumb" role="navigation">
						<ol class="breadcrumb adminx-page-breadcrumb">
							<li class="breadcrumb-item"><a href="#">Home</a></li>
							<li class="breadcrumb-item"><a href="#">유치원</a></li>
							<li class="breadcrumb-item active  aria-current=page">유치원 관리</li>
						</ol>
					</nav>
					<div class="pb-3"></div>
					<!--게시글 상세 및 수정 영역 시작-->
					<div class="row">
						<div class="col-lg-12">
							<div class="card form-area-kid">
								<form method="post" id="regform" action="/manager/manager_kid_RegProc.do" onsubmit="return !!(check()&validation());">
								<div class="manager-rightContent" id="rightContent">
									<div class="pageTitle kinderInfo clearfix" style="margin-bottom: 70px;">
										<h1> 유치원 등록 </h1>
									</div>
									<div class="container">
								        <div class="row align-items-center">
								            <div class="col-lg-3 lg-3-st card-block" style="padding: 1.3rem;">기관명</div>
								            <div class="col-lg-9 lg-9-st"><input type="text" class="form-control input-st" style="width:80%;" name="kindername"></div>
								        </div>
								        <div class="row align-items-center">
								            <div class="col-lg-3 lg-3-st card-block" style="padding: 1.3rem;">대표자명</div>
								            <div class="col-lg-9 lg-9-st"><input type="text" class="form-control input-st" style="width:80%;" name="rppnname"></div>
								        </div>
								        <div class="row align-items-center">
								            <div class="col-lg-3 lg-3-st card-block" style="padding: 1.3rem;">원장명</div>
								            <div class="col-lg-9 lg-9-st"><input type="text" class="form-control input-st" style="width:80%;" name="ldgrname"></div>
								        </div>        
								        <div class="row align-items-center">
								            <div class="col-lg-3 lg-3-st card-block" style="padding: 1.3rem;">설립유형</div>
								            <div class="col-lg-9 lg-9-st">
								                <select class="form-control" id="exampleFormControlSelect1" name="establish" style="width: 80%;height: 30px;">
								                    <option value="유형별">유형별</option>
								                    <option value="공립(단설)">공립(단설)</option>
								                    <option value="공립(병설)">공립(병설)</option>
								                    <option value="국립">국립</option>
								                    <option value="사립(사인)">사립(사인)</option>
								                    <option value="사립(법인)">사립(법인)</option>
								                </select>
								            </div>
								        </div> 
								        <div class="row align-items-center">
								            <div class="col-lg-3 lg-3-st card-block" style="padding: 1.3rem;">설립일</div>
								            <div class="col-lg-9 lg-9-st input-padding"><input class="form-control date-default" type="text" name="edate" style="width:80%;height: 30px;padding: 15px 0 15px 0;" placeholder="설립일"></div>
								        </div>
								        <div class="row align-items-center">
								            <div class="col-lg-3 lg-3-st card-block" style="padding: 1.3rem;">개원일</div>
								            <div class="col-lg-9 lg-9-st input-padding"><input class="form-control date-default" type="text" name="odate" style="width:80%;height: 30px;padding: 15px 0 15px 0;" placeholder="개원일"></div>
								        </div>               
								        <div class="row align-items-center">
								            <div class="col-lg-3 lg-3-st card-block" style="padding: 1.3rem;">관할행정기관</div>
								            <div class="col-lg-9 lg-9-st input-padding">
								                <div class="row">
								                    <input type="text" class="col-lg-4 form-control input-credit-card" style="margin-left: 15px;" name="officeedu" placeholder="○○시교육청">
								                    <input type="text" class="col-lg-4 form-control input-credit-card" name="subofficeedu" placeholder="○○교육지원청">
								                </div>
								            </div>
								        </div>
								        <div class="row align-items-center">
								            <div class="col-lg-3 lg-3-st card-block" style="padding: 1.3rem;">전화번호</div>
								            <div class="col-lg-9 lg-9-st"><input type="text" class="form-control input-st" style="width:80%;" name="telno"></div>
								        </div>
								        <div class="row align-items-center">
								            <div class="col-lg-3 lg-3-st card-block" style="padding: 1.3rem;">홈페이지</div>
								            <div class="col-lg-9 lg-9-st"><input type="text" class="form-control input-st" style="width:80%;" name="hpaddr"></div>
								        </div>
								        <div class="row align-items-center">
								            <div class="col-lg-3 lg-3-st card-block" style="padding: 1.3rem;">운영시간</div>
								            <div class="col-lg-9 lg-9-st"><input type="text" class="form-control input-st" style="width:80%;" name="opertime" placeholder="○○시○○분~○○시○○분"></div>
								        </div>
								        <div class="row align-items-center">
								            <div class="col-lg-3 lg-3-st card-block" style="padding: 1.3rem; height: 204.6px;">주소</div>
								            <div class="col-lg-9 lg-9-st" style="padding: 22px 0 22px 10px;">
								                 <input type="text" id="sample6_postcode" class="form-control input-credit-card" placeholder="우편번호" style="width:80%;">
								                <input type="button" onclick="sample6_execDaumPostcode()" class="form-control input-credit-card" value="우편번호 찾기" style="width:80%;"><br>
								                <input type="text" id="sample6_address" class="form-control input-credit-card" name="addr" placeholder="주소" style="width:80%;"><br>
								                <input type="text" id="sample6_extraAddress" class="form-control input-credit-card" placeholder="참고항목" style="width:80%;">
								            </div>
								        </div>
								        <div class="row align-items-center">
								            <div class="col-lg-12 lg-3-st card-block" style="text-align: center;">학급 및 유아수(명)</div>
								        </div>
								        <div class="row">
								            <div class="col-lg-1 lg-3-st">연령</div>
								            <div class="col-lg-2 lg-3-st">만 3세 반</div>
								            <div class="col-lg-2 lg-3-st">만 4세 반</div>
								            <div class="col-lg-2 lg-3-st">만 5세 이상 반</div>
								            <div class="col-lg-2 lg-3-st">혼합반</div>
								            <div class="col-lg-2 lg-3-st">특수학급</div>
								            <div class="col-lg-1 lg-3-st">총계</div>
								        </div>
								        <div class="row">
								            <div class="col-lg-1 lg-3-st ">학급수</div>
								            <div class="col-lg-2 lg-9-st"><input type="text" style="width:100px;" name="clcnt3" class="form-control input-credit-card"></div>
								            <div class="col-lg-2 lg-9-st"><input type="text" style="width:100px;" name="clcnt4" class="form-control input-credit-card"></div>
								            <div class="col-lg-2 lg-9-st"><input type="text" style="width:100px;" name="clcnt5" class="form-control input-credit-card"></div>
								            <div class="col-lg-2 lg-9-st"><input type="text" style="width:100px;" name="mixclcnt" class="form-control input-credit-card"></div>
								            <div class="col-lg-2 lg-9-st"><input type="text" style="width:100px;" name="shclcnt" class="form-control input-credit-card"></div>
								            <div class="col-lg-1 lg-9-st"></div>
								        </div>
								        <div class="row">
								            <div class="col-lg-1 lg-3-st">유아수</div>
								            <div class="col-lg-2 lg-9-st"><input type="text" style="width:100px;" name="ppcnt3" class="form-control input-credit-card"></div>
								            <div class="col-lg-2 lg-9-st"><input type="text" style="width:100px;" name="ppcnt4" class="form-control input-credit-card"></div>
								            <div class="col-lg-2 lg-9-st"><input type="text" style="width:100px;" name="ppcnt5" class="form-control input-credit-card"></div>
								            <div class="col-lg-2 lg-9-st"><input type="text" style="width:100px;" name="mixppcnt" class="form-control input-credit-card"></div>
								            <div class="col-lg-2 lg-9-st"><input type="text" style="width:100px;" name="shppcnt" class="form-control input-credit-card"></div>
								            <div class="col-lg-1 lg-9-st"></div>
								        </div>
								    </div>
									<div class="pageBtn">
										<a onclick="formSubmit(); return false;" class="green list">등록하기</a>
										<a onclick="location.href='/manager/manager_kid.do'" class="green list">취소하기</a>
									</div>
								</div>
								</form>
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
	<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js"></script>

	<script type="text/javascript">
    flatpickr(".date-default", {
        allowInput: true
      });
	</script>
	
	<script>
	    function formSubmit()
	    {
	    document.getElementById("regform").submit();
	    }
	</script>
	
	<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
	            }
	        }).open();
	    }
	</script>
</body>
</html>