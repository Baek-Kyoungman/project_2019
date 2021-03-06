<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String user_id = (String) session.getAttribute("SS_USER_ID");
	String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>[KIDO] 이용자약관</title>
<link rel="icon" href="/kido/img/core-img/favicon.ico">
<link href="/kido/css/style.css" rel="stylesheet">
<link href="/kido/css/responsive/responsive.css" rel="stylesheet">
<link href="/kido/css/others/animate.css" rel="stylesheet">
<link href="/kido/css/others/common.css" rel="stylesheet">
</head>
<!-- 
	//////////////////////////////////////////////////////
	2019 1학년 2학기 개인 프로젝트 
	주제 : 서울시에 위치한 공립유치원 위치 현황 및 상세정보 찾기 서비스
	페이지 명: 이용자 약관
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


	<!-- ***** Breadcumb Area Start ***** -->
	<div class="breadcumb-area bg-img bg-overlay"
		style="background-image: url(/kido/img/bg-img/seoul_2.jpg)"></div>
	<!-- ***** Breadcumb Area End ***** -->

	<section>
		<div class="ui vertical doubling stripe segment">
			<div class="ui container ftco-animate">
				<h1 class="ui center aligned header">이용자약관</h1>
				<pre>KIDO 이용자약관


KIDO 관련 제반 서비스의 이용과 관련하여 필요한 사항을 규정합니다.
회사는 정보통신망 이용촉진 및 정보보호 등에 관한 법률, 개인정보 보호법, 통신비밀보호법, 전기통신사업법 등 정보통신서비스제공자가 준수하여야 할 관련 법규상의 개인정보보호 규정을 준수하며, 관련 법령에 의거한 개인정보취급/처리방침을 정하여 이용자의 권익 보호에 최선을 다하고 있습니다.
                    
제 1조 목적
제 2조 용어의 정의
제 3조 약관의 명시와 개정
제 4조 약관의 해석
제 5조 이용계약의 성립
제 6조 이용신청의 승낙과 제한
제 7조 서비스의 내용
제 8조 (서비스 결제 및 환불)
제 9조 서비스 이용시간
제 10조 서비스 제공의 중지
제 11조 정보의 제공 및 광고의 게재
제 12조 콘텐츠의 책임과 “회사”의 정보 수정 권한
제 13조 콘텐츠의 권한 및 활용
제 14조 “회사”의 의무
제 15조 회원의 의무
제 16조 회원탈퇴 및 회원자격 상실
제 17조 손해배상
제 18조 "회원"의 개인정보보호
제 19조 분쟁의 해결
                    
제 1 조 (목적)
                    
본 약관은 주식회사 KIDO(이하 "회사")가 운영하는 앱서비스, 웹사이트(이하 "사이트")에서 제공하는 인터넷 관련 서비스를 이용함에 있어, 사이트와 회원 간의 이용 조건 및 제반 절차, 기타 필요한 사항을 규정함을 목적으로 한다.
                    
제 2 조 (용어의 정의)
                    
이 약관에서 사용하는 용어의 정의는 아래와 같다.
                    
1. "사이트"라 함은 회사가 서비스를 "회원"에게 제공하기 위하여 컴퓨터 등 정보 통신 설비를 이용하여 설정한 가상의 영업장 또는 회사가 운영하는 모바일 어플리케이션과 웹사이트를 말한다.
                    
2. "서비스"라 함은 회사가 운영하는 사이트를 통해 개인이 등록한 자료를 관리하여 대학 정보를 제공하는 서비스, 대학생활 정보, 쪽지, 캠퍼스리뷰, 캠퍼스랭킹, 캠퍼스 채널, 자유게시판, 지식채널A, 대학게시판 등의 목적으로 등록하는 자료를 DB화하여 각각의 목적에 맞게 분류 가공, 집계하여 정보를 제공하는 서비스와 사이트에서 제공하는 모든 부대 서비스를 말한다.
                    
3. "회원"이라 함은 서비스를 이용하기 위하여 동 약관에 동의하거나 페이스북 등 연동 된 서비스를 통해 "회사"와 이용 계약을 체결한 "개인 회원"을 말한다.
                    
4. "아이디"라 함은 회원의 식별과 회원의 서비스 이용을 위하여 "회원"이 가입 시 사용한 이메일 주소를 말한다.
                    
5. "비밀번호"라 함은 "회사"의 서비스를 이용하려는 사람이 아이디를 부여 받은 자와 동일인임을 확인하고 "회원"의 권익을 보호하기 위하여 "회원"이 선정한 문자와 숫자의 조합을 말한다.
                    
6. "비회원"이라 함은 "회원"에 가입하지 않고 "회사"가 제공하는 서비스를 이용하는 자를 말한다.
                    
7. “게시물”이라 함은 서비스에 게시되는 모든 문자, 사진, 정보 등을 말합니다.
                    
8. “모든 약관”이라 함은 KIDO 통합서비스이용약관, KIDO 통합개인정보취급방침 등 회사와 회원이 계약한 모든 약관을 말한다.
                    
                    
제 3 조 (약관의 명시와 개정)
                    
1. "회사"는 이 약관의 내용과 상호, 영업소 소재지, 대표자의 성명, 사업자등록번호, 연락처 등을 "회원"이 알 수 있도록 초기 화면에 게시하거나 기타의 방법으로 "회원"에게 공지해야 한다.
                    
2. "회사"는 약관의규제등에관한법률, 전기통신기본법, 전기통신사업법, 정보통신망이용촉진등에관한법률 등 관련법을 위배하지 않는 범위에서 이 약관을 개정할 수 있다.
                    
3. "회사"가 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 개정약관의 적용일자 7일 전부터 적용일자 전일까지 공지한다.
                    
4. "회원"은 변경된 약관에 대해 거부할 권리가 있다. "회원"은 변경된 약관이 공지된 지 7일 이내에 거부 의사를 표명할 수 있다. "회원"이 거부하는 경우 본 서비스 제공자인 "회사"는 7일의 기간을 정하여 "회원"에게 사전 통지 후 당해 "회원"과의 계약을 해지할 수 있다. 만약, "회원"이 거부 의사를 표시하지 않거나, 전항에 따라 시행일 이후에 "서비스"를 이용하는 경우에는 동의한 것으로 간주한다.
                    
제 4 조 (약관의 해석)
                    
1. 이 약관에서 규정하지 않은 사항에 관해서는 약관의규제등에관한법률, 전기통신기본법, 전기통신사업법, 정보통신망이용촉진등에관한법률 등의 관계법령에 따른다.
                    
2. "회원"이 "회사"와 개별 계약을 체결하여 서비스를 이용하는 경우에는 개별 계약이 우선한다.
                    
제 5 조 (이용계약의 성립)
                    
1. "회사"의 서비스 이용계약(이하 "이용계약")은 서비스를 이용하고자 하는 자가 본 약관과 개인정보취급방침을 읽고 "동의" 또는 "확인" 버튼을 누른 경우 본 약관에 동의한 것으로 간주한다.
                    
2. 제1항 신청에 있어 "회사"는 "회원"의 종류에 따라 전문기관을 통한 실명확인 및 본인인증을 요청할 수 있다. "회원"은 본인인증에 필요한 이름, 생년월일, 연락처 등을 제공하여야 한다.
                    
3. "회원"이 이용신청(회원가입 신청) 작성 후에 "회사"가 웹 상의 안내 및 전자메일로 "회원"에게 통지함으로써 이용계약이 성립된다.
                    
4. 페이스북 등 외부 서비스와의 연동을 통해 이용계약을 신청할 경우, 본 약관과 개인정보취급방침, 서비스 제공을 위해 “회사”가 “회원”의 외부 서비스 계정 정보 접근 및 활용에 “동의” 또는 “확인” 버튼을 누르면 “회사”가 웹 상의 안내 및 전자메일로 “회원”에게 통지함으로써 이용계약이 성립된다.
                    
제 6 조 (이용신청의 승낙과 제한)
                    
1. "회사"는 전조의 규정에 의한 이용신청 고객에 대하여 업무수행 상 또는 기술상 지장이 없는 경우에는 원칙적으로 접수 순서에 따라 서비스 이용을 승낙한다.
                    
2. "회사"는 아래 사항에 해당하는 경우에 대해서는 이용 신청을 승낙하지 아니한다.
가. 실명이 아니거나 타인의 명의를 이용하여 신청한 경우
나. 이용계약 신청서의 내용을 허위로 기재한 경우
                    
3. "회사"는 아래 사항에 해당하는 경우에는 그 신청에 대하여 승낙 제한 사유가 해소될 때까지 승낙을 유보할 수 있다.
                    
가. "회사"가 설비의 여유가 없는 경우
나. "회사"의 기술상 지장이 있는 경우
다. 기타 "회사"의 귀책 사유로 이용 승낙이 곤란한 경우
                    
제 7 조 (서비스의 내용)
                    
1. "회사"는 제2조 2항의 서비스를 제공할 수 있으며 그 내용은 다음 각 호와 같다.
                    
가. 대학, 학과 후기 등록과 조회 서비스
나. 대학 정보 조회와 관련된 제반 서비스
다. 이용자 간의 교류와 소통에 관련한 서비스
라. 기타 "회사"가 추가 개발하거나 제휴계약 등을 통해 "회원"에게 제공하는 일체의 서비스
                    
2. "회사"는 필요한 경우 서비스의 내용을 추가 또는 변경할 수 있다. 단, 이 경우 "회사"는 추가 또는 변경내용을 "회원"에게 공지해야 한다.
                    
                    
제 8 조 (서비스 결제 및 환불)
                    
1. "회원"은 "회사"가 제공하는 다양한 결제수단을 통해 유료서비스를 이용할 수 있으며,
결제가 비정상적으로 처리되어 정상처리를 요청할 경우 회사는 회원의 결제금액을 정상처리 할 의무를 가집니다.
                    
2. "회사"는 부정한 방법 또는 "회사"가 금지한 방법을 통해 충전 및 결제된 금액에 대해서는 이를 취소하거나 환불을 제한할 수 있습니다.
                    
3. "회원"은 다음 각 호의 사유가 있으면 아래의 4항의 규정에 따라서 "회사"로부터 결제 취소, 환불 및 보상을 받을 수 있습니다.
1) 결제를 통해 사용할 수 있는 서비스가 전무하며 그에 대한 책임이 전적으로 회사에 있을 경우
(단, 사전 공지된 시스템 정기 점검 등의 불가피한 경우는 제외)
2) "회사" 또는 결제대행사의 시스템의 오류로 인하여 결제기록이 중복으로 발생한 경우
3) "서비스" 종료 명목으로 "회사"가 "회원"에게 해지를 통보하는 경우
4) 기타 소비자 보호를 위하여 당사에서 따로 정하는 경우.
5) 아이튠즈 인앱 결제의 경우 애플사에 결제 취소, 환불 권한이 있으므로 애플사로 부터의 적절한 결제 취소, 환불이 이루어지지 않을 경우
부가세와 수수료를 제외한 금액으로 회사의 환불이 이루어집니다. (30~40% 제외 금액)
                    
4. 환불, 결제 취소 절차는 다음 각 항목과 같습니다.
1) 환불을 원하는 회원은 이메일을 통해 회원 본인임을 인증하는 절차를 거쳐 고객센터에 접수해야 하며 본인 인증과 동시에 환불을 신청하여야 합니다.
2) 회사는 회원의 환불 요청 사유가 적합한지를 판단하고 적합한 절차를 거친 것으로 판명된 회원에게 환불합니다.
3) 회사는 회원에게 환불되어야 할 금액 산정 방식과 절차를 회원에게 상세히 설명하고 난 후, 회원에게 해당 환불 및 결제 취소 처리합니다.
4) 회원은 구매시점으로부터 7일 이내인 경우 환불이 가능하며 구매시점 7일 이후에는 시스템 오류에 의한 미지급 등 회사의 귀책사유로 인정되는 경우에만 환불이 가능합니다.
5) 회원이 이용약관을 위반한 행위로 인해 사용금지, 강제탈퇴 등의 제재를 당하는 경우 위약벌로서 아이템 환불 및 보상이 불가합니다.
6) 회원의 자진탈퇴로 인해 계약이 해지되는 경우, 회원이 보유한 아이템은 자동으로 소멸되어 복구 및 환불이 불가능합니다.
7) 신원 인증과정에서 회원의 등록정보가 허위 정보로 판명되거나 가입 조건에 부합되지 않는 점이 판명될 경우
징계 및 강제 탈퇴가 되며 회원 본인의 귀책사유로 인해 환불 및 보상이 불가능합니다.
8) 회사와 이용자 간에 발생한 분쟁은 전자거래기본법 제32조에 의거하여 설치된 기관인 전자거래분쟁조정위원회의 조정에 따를 수 있습니다.
                                        
                                        
제 9 조 (서비스 이용시간)
                                        
1. "회사"는 특별한 사유가 없는 한 연중무휴, 1일 24시간 서비스를 제공한다. 다만, "회사"는 서비스의 종류나 성질에 따라 제공하는 서비스 중 일부에 대해서는 별도로 이용시간을 정할 수 있으며, 이 경우 "회사"는 그 이용시간을 사전에 "회원"에게 공지 또는 통지하여야 한다.
                                        
2. "회사"는 자료의 가공과 갱신을 위한 시스템 작업시간, 장애해결을 위한 보수작업 시간, 회선 장애 등이 발생한 경우 일시적으로 서비스를 중단할 수 있으며 계획된 작업의 경우 공지란에 서비스 중단 시간과 작업 내용을 알려야 한다.
                                        
제 10 조 (서비스 제공의 중지)
                                        
"회사"는 다음 각 호에 해당하는 경우 서비스의 제공을 중지할 수 있다.
                                        
1. 설비의 보수 등 "회사"의 필요에 의해 사전에 "회원"들에게 통지한 경우
2. 기간통신사업자가 전기통신서비스 제공을 중지하는 경우
3. 기타 불가항력적인 사유에 의해 서비스 제공이 객관적으로 불가능한 경우
                                        
제 11 조 (정보의 제공 및 광고의 게재)
                                        
1. "회사"는 "회원"에게 서비스 이용에 필요가 있다고 인정되거나 서비스 개선 및 회원대상의 서비스 소개 등의 목적으로 하는 각종 정보에 대해서 전자우편이나 서신우편을 이용한 방법으로 제공할 수 있다.
                                        
2. "회사"는 제공하는 서비스와 관련되는 정보 또는 광고를 서비스 화면, 홈페이지 등에 게재할 수 있으며, "회원"들에게 메일을 통해 알릴 수 있다.
                                        
3. "회사"는 서비스상에 게재되어 있거나 본 서비스를 통한 광고주의 판촉활동에 "회원"이 참여하거나 교신 또는 거래를 함으로써 발생하는 모든 손실과 손해에 대해 책임을 지지 않는다.
                                        
4. 본 서비스의 "회원"은 서비스 이용 시 노출되는 광고게재에 대해 동의 하는 것으로 간주한다.
                                        
제 12 조 (콘텐츠의 책임과 "회사"의 정보 수정 권한)
                                        
1. 콘텐츠는 “회원”이 작성 및 등록한 대학 리뷰와 평점, 쪽지, 자유게시판, 지식채널-A, 대학게시판 등 게시물을 말한다.
                                        
2. "회원"은 콘텐츠를 사실에 근거하여 성실하게 작성해야 하며, “회원” 본인이 작성하는 것을 원칙으로 한다.
                                        
3. "회사"는 "회원"이 작성한 콘텐츠를 검토하여 “콘텐츠 삭제 규칙”및 제14조 회원의 의무에 명시된 조건에 문제가 없을 경우 “사이트”에 게시하는 방식의 필터링 작업을 수행하며, 이 작업에서 “콘텐츠 삭제 규칙” 및 회원의 의무 위반으로 판단되어 게시가 허락되지 않을 경우 “회원”에게 사전 통보 없이 “사이트”에 게시되지 않는다.
                                        
4. 콘텐츠 내용에 오자, 탈자 또는 사회적 통념에 어긋나는 문구와 내용, 명백하게 허위의 사실에 기초한 내용이 있을 경우 “회사”는 이를 언제든지 삭제하거나 수정할 수 있다.
                                        
5. "회원"이 등록한 자료로 인해 타인(또는 타법인)으로부터 허위사실 및 명예훼손 등으로 삭제 요청이 접수된 경우 "회사"는 "회원"에게 사전 통지 없이 본 자료를 삭제 혹은 비노출 처리 할 수 있다.
                                        
6. 다른 사용자를 불쾌하게 만들 수 있는 컨텐츠를 허용하지 않는다.
                                        
7. “회사”에서 제공되는 모든 “서비스”는 정보제공의 목적으로 제공되는 것이며 실제 대학 입시를 위해 사용 할 경우 정보의 오류로 인한 피해에 대해서 회사는 어떠한 책임도 지지 않는다.
                                        
제 13 조 (콘텐츠의 권한 및 활용)
                                        
1. "회원"이 입력한 콘텐츠는 익명으로 공개되며, 모든 콘텐츠는 게시와 동시에 “회사”가 사용 권리를 획득하게 된다.
                                        
2. 모든 콘텐츠는 대학 및 관련 동향의 통계 자료로 활용될 수 있고 그 자료는 매체를 통해 언론에 배포될 수 있다.
                                        
제 14 조 ("회사"의 의무)
                                        
1. "회사"는 본 약관에서 정한 바에 따라 계속적, 안정적으로 서비스를 제공할 수 있도록 최선의 노력을 다해야 한다.
                                        
2. "회사"는 서비스와 관련한 "회원"의 불만사항이 접수되는 경우 이를 즉시 처리하여야 하며, 즉시 처리가 곤란한 경우에는 그 사유와 처리일정을 서비스 화면 또는 기타 방법을 통해 동 "회원"에게 통지하여야 한다.
                                        
3. 천재지변 등 예측하지 못한 일이 발생하거나 시스템의 장애가 발생하여 서비스가 중단될 경우 이에 대한 손해에 대해서는 "회사"가 책임을 지지 않는다. 다만 자료의 복구나 정상적인 서비스 지원이 되도록 최선을 다할 의무를 진다.
                                        
제 15 조 (회원의 의무)
                                        
1. "회원"은 관계법령과 본 약관의 규정 및 기타 "회사"가 통지하는 사항을 준수하여야 하며, 기타 "회사"의 업무에 방해되는 행위를 해서는 안 된다.
                                        
2. "회원"은 서비스를 이용하여 얻은 정보를 "회사"의 사전동의 없이 복사, 복제, 번역, 출판, 방송 기타의 방법으로 사용하거나 이를 타인에게 제공할 수 없다.
                                        
3. "회원"은 본 서비스를 대학 정보 확인 및 건전한 대입 및 취업 이외의 목적으로 사용해서는 안되며 이용 중 다음 각 호의 행위를 해서는 안 된다.
                                        
가. 다른 회원의 아이디를 부정 사용하는 행위
나. 범죄행위을 목적으로 하거나 기타 범죄행위와 관련된 행위
다. 타인의 명예를 훼손하거나 모욕하는 행위
라. 타인의 지적재산권 등의 권리를 침해하는 행위
마. 해킹행위 또는 바이러스의 유포 행위
바. 타인의 의사에 반하여 광고성 정보 등 일정한 내용을 계속적으로 전송하는 행위
사. 서비스의 안정적인 운영에 지장을 주거나 줄 우려가 있다고 판단되는 행위
아. 사이트의 정보 및 서비스를 이용한 영리 행위
자. 그밖에 선량한 풍속, 기타 사회질서를 해하거나 관계법령에 위반하는 행위
                                        
제 16조 (회원탈퇴 및 회원자격 상실)
                                        
1. 회원은 회사에 언제든지 탈퇴를 요청할 수 있으며, 탈퇴 요청은 사이트 〔마이페이지〕메뉴의 〔회원정보변경〕메뉴에서 〔탈퇴하기〕버튼을 통해 회원탈퇴를 하실 수 있습니다.
                                        
2. 회원이 회원탈퇴를 하는 경우 작성한 게시물은 약관 제 12조 2항에 따라 삭제되지 않습니다.
                                        
3. 회사는 회원이 이 약관 및 개별서비스 이용약관을 위반한 경우 경고, 일시적 이용정지, 영구적 이용정지 등의 단계로 서비스 이용을 제한하거나 이용계약을 해지할 수 있습니다.
                                        
4. 회원은 제 3항에 따른 서비스 이용정지 기타 서비스 이용과 관련된 이용제한에 대한 회사가 정한 절차에 따라 이의신청을 할 수 있으며, 회사는 회원의 이의신청이 정당하다고 판단되는 경우 즉시 서비스 이용을 재개합니다.
                                        
제 17 조 (손해배상)
                                        
1. "회사"가 "회원"에게 손해를 입히거나 기타 "회사"가 제공하는 모든 서비스와 관련하여 "회사"의 책임있는 사유로 인해 이용자에게 손해가 발생한 경우 "회사"는 그 손해를 배상하여야 한다.
                                        
2. "회원"이 이 약관의 규정에 위반한 행위로 "회사" 및 제3자에게 손해를 입히거나 "회원"의 책임 있는 사유에 의해 "회사" 및 제3자에게 손해를 입힌 경우에는 "회원"은 그 손해를 배상하여야 한다.
                                        
3. 타 회원의 귀책사유로 "회원"의 손해가 발생한 경우 "회사"는 이에 대한 배상 책임이 없다.
                                        
제 18 조 ("회원"의 개인정보보호)
"회사"는 "회원"의 개인정보보호를 위하여 노력해야 한다. "회원"의 개인정보보호에 관해서는 정보통신망이용촉진 및 정보보호 등에 관한 법률에 따르고, "사이트"에 "개인정보취급방침"을 고지한다.
                                        
제 19 조 (분쟁의 해결)
1. "회사"와 "회원"은 서비스와 관련하여 발생한 분쟁을 원만하게 해결하기 위하여 필요한 모든 노력을 하여야 한다.
                                        
2. 전항의 노력에도 불구하고, 동 분쟁에 관한 소송발생시 소송은 "회사"의 주소지 관할법원으로 한다.
                                    
                    </pre>
			</div>
		</div>
	</section>

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