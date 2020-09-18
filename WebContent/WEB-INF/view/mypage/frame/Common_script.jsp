<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
        <script src="/mypage/dist/js/vendor.js"></script>
        <script src="/mypage/dist/js/adminx.js"></script>
        <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js"></script>
        
         <script type="text/javascript">
            function LogOut_btn(){
            if (confirm("로그아웃 하시겠습니까?") == true){    //확인
                location.href="/Logoutbtn.do"
            }else{   //취소
                return;
                }
            }
         </script>