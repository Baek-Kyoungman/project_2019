/*===============================================================
 * 아이디 중복검사를 위한 구문
 *===============================================================
 */	
	//아이디 체크여부 확인 (아이디 중복일 경우 = 1 , 중복이 아닐경우 = 0 )
    var idck = '0';
    $(function() {
        //idck 중복검사 버튼을 클릭했을 때 
        $("#idck").click(function() {
            
            //user_id 를 parameter로.
        	var idck = $("#idCheck").val();
            var user_id =  $("#user_id").val();
            
            $.ajax({
                type : 'POST',
                data : {'user_id' : user_id},
                url : "idCheck.do",
                success : function(data) {
                	console.log(data);
                	console.log("success");
                	console.log("count");
                    if (data ==  '0') {
                    	alert('사용 가능한 아이디입니다.');
                        //아이디가 존재할 경우 빨강으로 , 아니면 파랑으로 처리하는 디자인
    	        		$('#user_id').css("color", "green"); 
                        $("#password").focus();
                        //아이디가z` 중복하지 않으면  idck = 1 
                        $("#idCheck").val("1");
                    } else {
                    	alert('아이디가 존재합니다. 다른 아이디를 입력해주세요.');
    	                //아이디가 존재할 경우 빨강으로 , 아니면 파랑으로 처리하는 디자인
    	                $('#user_id').css("color", "red");
    	                $('#user_id').focus();
                    }
                },
                error : function(error) {
                    
                    alert("error : " + error);
                }
            });
        });
    });
    
    /*===============================================================
     * 이메일 중복검사를 위한 구문
     *===============================================================
     */	
    	//이메일 체크여부 확인 (이에밀 중복일 경우 = 1 , 중복이 아닐경우 = 0 )
        var emailck = '0';
        $(function() {
            //idck 중복검사 버튼을 클릭했을 때 
            $("#emailck").click(function() {
                
                //user_id 를 parameter로.
            	var emailck = $("#emailCheck").val();
                var email =  $("#email").val();
                
                $.ajax({
                    type : 'POST',
                    data : {'email' : email},
                    url : "emailCheck.do",
                    success : function(data) {
                    	console.log(data);
                    	console.log("success");
                    	console.log("count2");
                        if (data ==  '0') {
                        	alert('사용 가능한 이메일입니다.');
                            //아이디가 존재할 경우 빨강으로 , 아니면 파랑으로 처리하는 디자인
        	        		$('#email').css("color", "green"); 
                            //아이디가z` 중복하지 않으면  idck = 1 
                            $("#emailCheck").val("1");
                        } else {
                        	alert('이메일이 존재합니다. 다른 이메일을 입력해주세요.');
        	                //아이디가 존재할 경우 빨강으로 , 아니면 파랑으로 처리하는 디자인
        	                $('#email').css("color", "red");
        	                $('#email').focus();
                        }
                    },
                    error : function(error) {
                        
                        alert("error : " + error);
                    }
                });
            });
        });    
/*===============================================================
 * 첫번째 유효성 검사를 위한 구문(alert로 나옴)
 *==============================================================*/
 	    
function check(){
	
	var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/); 	//이메일 정규표현식
	var getCheck= RegExp(/^[a-z0-9]{5,20}$/); 									//아이디 정규표현식
	var getPassword= RegExp(/^[a-zA-Z0-9!@#$]{8,20}$/); 						//비밀번호 정규표현식
	var getName= RegExp(/^[가-힣]{2,20}$/);										//이름 정규표현식	
	var getTel= RegExp(/^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/);										//전화번호 정규표현식	
	var fmt = RegExp(/^\d{6}[1234]\d{6}$/); 									//형식 설정 

	
	//이름 입력여부 검사
	if($("input[name=user_name]").val()==""){
		alert("이름을 입력해 주세요.");
		$("input[name=user_name]").focus();
		return false;
	}
	//이름 유효성 검사 
	else if(!getName.test($("input[name=user_name]").val())){ 
		alert("이름 형식에 맞게 입력해주세요")
		$("input[name=user_name]").val("");
		$("input[name=user_name]").focus();
		return false; 
	}
	//아이디 입력여부 검사
	else if($("input[name=user_id]").val()==""){
		alert("아이디를 입력해 주세요.");
		$("input[name=user_id]").focus();
		return false;
	}
	//아이디 유효성 검사 
	else if(!getCheck.test($("input[name=user_id]").val())){ 
		alert("아이디 형식에 맞게 입력해주세요")
		$("input[name=user_id]").val("");
		$("input[name=user_id]").focus();
		return false; 
	}
	//비밀번호 입력여부 검사
	else if($("input[name=password]").val()==""){
		alert("비밀번호를 입력해 주세요.")
		$("input[name=password]").focus();
		return false;
	}
	//아이디, 비밀번호 일치 확인
	else if($("input[name=user_name]").val() === $("input[name=password]").val()){ 
		alert("아이디와 비밀번호가 일치합니다")
		$("input[name=password]").val("");
		$("input[name=password]").focus();
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
	else if($("input[name=password2]").val()==""){
		alert("비밀번호 재확인을 입력해 주세요.")
		$("input[name=password2]").focus();
		return false;
	}
	
	//비밀번호 일치여부 검사
	else if($("input[name=password]").val()!=$("input[name=password2]").val()){
		alert("비밀번호가 일치하지 않습니다.")
		$("input[name=password2]").val("");
		$("input[name=password2]").focus();
		return false;
    }
    
	//이메일 입력여부 검사
	else if($("input[name=email]").val()==""){
		alert("이메일을 입력해 주세요.")
		$("input[name=email]").focus();
		return false;
	}
    else if(!getMail.test($("input[name=email]").val())){ 
		alert("이메일 형식에 맞게 입력해주세요")
		$("input[name=email]").val("");
		$("input[name=email]").focus();
		return false; 
	}
	//이용약관 동의 체크 안할 시 실패
	else if($("input[name=userAgree]").is(":checked") == false){
    	alert("필수 항목은 반드시 선택하셔야 가입이 가능합니다.");
    	return false;
    }
    // 회원가입 버튼
    var idck = $("#idCheck").val();
    if(confirm("회원가입을 하시겠습니까?")){
        if(idck=="0"){
            alert('아이디 중복검사를 해주세요');
            return false;
        }else if(idck=="1"){

	        location.href("/index.do");
        }
    }
}

/*===============================================================
 * 두번째 유효성 검사를 위한 구문(input tag 밑에 빨간 글씨로 나옴)
 *==============================================================*/
//아이디 정규식
var idJ = /^[a-z0-9]{5,20}$/;
// 비밀번호 정규식
var pwJ = /^[a-zA-Z0-9!@#$]{8,20}$/; 
// 이름 정규식
var nameJ = /^[가-힣]{2,20}$/;
// 이메일 검사 정규식
var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
// 휴대폰 번호 정규식
var phoneJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;

	//아이디 유효성 검사
	$("#user_id").blur(function() {
		if (idJ.test($(this).val())) {
				console.log(nameJ.test($(this).val()));
				$("#id_check").text('');
		} else {
			$('#id_check').text('아이디를 확인해주세요');
			$('#id_check').css('color', 'red');
		}
	});

	// 이름에 특수문자 들어가지 않도록 설정
	$("#user_name").blur(function() {
		if (nameJ.test($(this).val())) {
				console.log(nameJ.test($(this).val()));
				$("#name_check").text('');
		} else {
			$('#name_check').text('이름을 확인해주세요');
			$('#name_check').css('color', 'red');
		}
	});
	
	// 비밀번호 유효성 검사
	// 1-1 정규식 체크
	$("#user_pw").blur(function() {
		if (pwJ.test($('#user_pw').val())) {
				console.log('true');
				$("#pw_check").text('');
		} else {
			console.log('false');
			$('#pw_check').text('비밀번호 형식에 맞게 입력해주세요');
			$('#pw_check').css('color', 'red');
		}
	});	
	
	// 1-2 비밀번호 확인 정규식 체크
	$("#user_pw2").blur(function() {
		if (pwJ.test($('#user_pw2').val())) {
				console.log('true');
				$("#pw2_check").text('');
		} else {
			console.log('false');
			$('#pw2_check').text('비밀번호 재확인을 입력해주세요');
			$('#pw2_check').css('color', 'red');
		}
	});	
	
	// 1-3 패스워드 일치 확인
	$("#user_pw2").blur(function() {
		if ($('#user_pw').val() != $(this).val()) {
			$('#pw2_check').text('비밀번호가 일치하지 않습니다.')
			$('#pw2_check').css('color', 'red');
		} else {
			$('#pw2_check').text('');
		}
	});
	
	//이메일 유효성 검사
	// 1-1 정규식 체크
	$("#email").blur(function() {
		if (mailJ.test($('#email').val())) {
				console.log('true');
				$("#mail_check").text('');
		} else {
			console.log('false');
			$('#mail_check').text('이메일 형식에 맞게 입력해주세요');
			$('#mail_check').css('color', 'red');
		}
	});	
	
	// 휴대전화 유효성 검사
	$('#user_phone').blur(function(){
		if(phoneJ.test($(this).val())){
			console.log(phoneJ.test($(this).val()));
			$("#phone_check").text('');
		} else {
			$('#phone_check').text('휴대폰 번호를 확인해주세요 ');
			$('#phone_check').css('color', 'red');
		}
	});