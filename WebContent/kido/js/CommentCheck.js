/*===============================================================
 * 유효성 검사를 위한 구문(alert로 나옴)
 *==============================================================*/
 	    
function check(){
	
	//댓글 입력여부 검사
	if($("input[name=content]").val()==""){
		alert("댓글을 입력해 주세요.");
		$("input[name=content]").focus();
		return false;
	}
}
