<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="poly.dto.UserInfoDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>    
<%
	String user_name = (String) session.getAttribute("SS_USER_NAME");
%>    
            <nav class="navbar navbar-expand justify-content-between fixed-top">
                <!--로고 시작-->
                <a class="navbar-brand mb-0 h1 d-none d-md-block" href="/main/index.do">
                    KIDO
                </a>
                <!--로고 종료-->
                <div class="d-flex flex-1 d-block d-md-none">
                    <a href="#" class="sidebar-toggle ml-3">
                        <i data-feather="menu"></i>
                    </a>
                </div>
                <ul class="navbar-nav d-flex justify-content-end mr-2">
                    <!-- Notifications -->
                    <li class="nav-item dropdown">
                        <a class="nav-link avatar-with-name" id="navbarDropdownMenuLink" data-toggle="dropdown" href="#">
							<%=user_name %>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="/mypage/setting.do">설정</a>
                            <div class="dropdown-divider"></div>
                            <button type="submit" class="dropdown-item text-danger" onclick="LogOut_btn();">로그아웃</button>
                        </div>
                    </li>
                </ul>
            </nav>
            
            