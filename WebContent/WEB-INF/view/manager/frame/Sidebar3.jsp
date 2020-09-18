<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Sidebar 영역 시작 -->
            <div class="adminx-sidebar expand-hover push" id="sidebar">
                <ul class="sidebar-nav">
                    <!--사이드바 메뉴 1 시작-->
                    <li class="sidebar-nav-item">
                        <a class="sidebar-nav-link" data-toggle="collapse" href="#user" aria-expanded="false" aria-controls="navTables">
                            <span class="sidebar-nav-icon">
                                <i data-feather="user"></i>
                            </span>
                            <span class="sidebar-nav-name"> 회원관리 </span>
                            <span class="sidebar-nav-end">
                                <i data-feather="chevron-right" class="nav-collapse-icon"></i>
                            </span>
                        </a>
                        <ul class="sidebar-sub-nav collapse" id="user">
                            <li class="sidebar-nav-item">
                                <a href="/manager/manager_user.do" class="sidebar-nav-link">
                                    <span class="sidebar-nav-abbr"> Us </span>
                                    <span class="sidebar-nav-name"> 회원관리 </span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <!--사이드바 메뉴 1 종료-->
                    <!--사이드바 메뉴 2 시작-->
                    <li class="sidebar-nav-item">
                        <a class="sidebar-nav-link" data-toggle="collapse" href="#list" aria-expanded="false" aria-controls="navTables">
                            <span class="sidebar-nav-icon">
                                <i data-feather="list"></i>
                            </span>
                            <span class="sidebar-nav-name"> 게시판 </span>
                            <span class="sidebar-nav-end">
                                <i data-feather="chevron-right" class="nav-collapse-icon"></i>
                            </span>
                        </a>
                        <ul class="sidebar-sub-nav collapse show" id="list">
                            <li class="sidebar-nav-item">
                                <a href="/manager/board_review.do" class="sidebar-nav-link">
                                    <span class="sidebar-nav-abbr"> Re </span>
                                    <span class="sidebar-nav-name"> 리뷰게시판 </span>
                                </a>
                            </li>
                            <li class="sidebar-nav-item">
                                <a href="/manager/board_free.do" class="sidebar-nav-link active">
                                    <span class="sidebar-nav-abbr"> Fr </span>
                                    <span class="sidebar-nav-name">  자유게시판 </span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <!--사이드바 메뉴 2 종료-->
					<!--사이드바 메뉴 3 시작-->
					<li class="sidebar-nav-item">
						<a class="sidebar-nav-link collapsed" data-toggle="collapse" href="#navKinder" aria-expanded="false" aria-controls="navKinder">
							<span class="sidebar-nav-icon">
								<i data-feather="inbox"></i>
							</span>
							<span class="sidebar-nav-name"> 유치원 </span>
							<span class="sidebar-nav-end">
								<i data-feather="chevron-right" class="nav-collapse-icon"></i>
							</span>
						</a>
						<ul class="sidebar-sub-nav collapse" id="navKinder">
							<li class="sidebar-nav-item">
								<a href="/manager/manager_kid.do" class="sidebar-nav-link">
									<span class="sidebar-nav-abbr"> Ki </span>
									<span class="sidebar-nav-name"> 유치원 관리 </span>
                                </a>
                            </li>
                        </ul>
                    </li>
					<!--사이드바 메뉴 3 종료-->                    
                    <!--사이드바 메뉴 4 시작-->
                    <li class="sidebar-nav-item">
                        <a class="sidebar-nav-link collapsed" data-toggle="collapse" href="#navForms" aria-expanded="false" aria-controls="navForms">
                            <span class="sidebar-nav-icon">
                                <i data-feather="settings"></i>
                            </span>
                            <span class="sidebar-nav-name"> 설정 </span>
                            <span class="sidebar-nav-end">
                                <i data-feather="chevron-right" class="nav-collapse-icon"></i>
                            </span>
                        </a>
                        <ul class="sidebar-sub-nav collapse" id="navForms">
                            <li class="sidebar-nav-item">
                                <a href="/manager/setting.do" class="sidebar-nav-link">
                                    <span class="sidebar-nav-abbr"> Pr </span>
                                    <span class="sidebar-nav-name"> 내 정보 수정 </span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <!--사이드바 메뉴 4 종료-->
                </ul>
            </div>
            <!-- Sidebar 종료 -->