package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.FreeDTO;
import poly.dto.KidInfoDTO;
import poly.dto.ReviewDTO;
import poly.dto.UserInfoDTO;
import poly.service.IManagerService;
import poly.util.EncryptUtil;

//Controller 선언해야만 Spring 프레임 워크에서 Controller인지 인식 가능, 자바 서블릿 역할 수행
@Controller
public class ManagerController {
	private Logger log = Logger.getLogger(this.getClass());

	// 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
	@Resource(name = "ManagerService")
	private IManagerService managerService;

	// 회원관리 화면
	@RequestMapping(value = "manager/manager_user")
	public String Manager_user(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + ".manager_user start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

		if (user_id != null) {

			log.info(this.getClass());

			int pgNum = 1;

			if (request.getParameter("pgNum") != null) {
				pgNum = Integer.parseInt(request.getParameter("pgNum"));
			}

			int startNum = (pgNum - 1) * 10 + 1;
			int endNum = (pgNum - 1) * 10 + 10;
			List<UserInfoDTO> uList = new ArrayList<>();

			int review_seq = 1;

			if (request.getParameter("review_seq") != null) {
				review_seq = Integer.parseInt(request.getParameter("review_seq"));
			}
			int total = Integer.parseInt(managerService.getUserCnt());

			try {
				uList = managerService.getManager_UserList(user_id, startNum, endNum);

				model.addAttribute("uList", uList);
			} catch (Exception e) {
				e.printStackTrace();
			}

			model.addAttribute("pgNum", pgNum);
			model.addAttribute("total", total);

			model.addAttribute("uList", uList);

			log.info(this.getClass().getName() + ".manager_user end!");
			return "/manager/manager_user/manager_user";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 설정 화면으로 이동
	@RequestMapping(value = "manager/setting")
	public String Setting(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + ".manager/setting start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");
		String user_name = (String) session.getAttribute("SS_USER_NAME");
		String email = (String) session.getAttribute("SS_EMAIL");
		String user_job = (String) session.getAttribute("SS_USER_JOB");

		UserInfoDTO pDTO = null;

		pDTO = new UserInfoDTO();

		log.info("Manager_user_id : " + user_id);
		log.info("Manager_user_name : " + user_name);
		log.info("Manager_email : " + email);
		log.info("Manager_user_job : " + user_job);

		if (user_id != null) {

			log.info(this.getClass().getName() + ".manager/setting end!");
			return "/manager/Modify/manager_setting";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 관리자 회원 탈퇴
	@RequestMapping(value = "manager/userDelete")
	public String userDelete(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "manager/userDelete start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		log.info("관리자_회원탈퇴_user_id : " + user_id);
		int result = 0;

		try {
			result = managerService.deleteUserInfo(user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result > 0) {
			model.addAttribute("url", "/Logoutbtn.do");
			model.addAttribute("msg", "회원탈퇴에 성공하셨습니다. 60일 이내에 취소 하실 수 있습니다. 취소하시려면 전화 또는 Email로 문의해주세요.");

			log.info(this.getClass().getName() + "manager/userDelete end!");
		} else {
			model.addAttribute("url", "/manager/setting.do");
			model.addAttribute("msg", "회원탈퇴에 실패했습니다.");

			log.info(this.getClass().getName() + "manager/userDelete end!");
		}

		return "/redirect";
	}

	// 관리자 회원관리 페이지 권한 변경
	@RequestMapping(value = "manager/ModifyAuthorProc")
	public String ModifyAuthor(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "manager/ModifuAuthorProc start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");
		String userCheck = request.getParameter("userCheck");

		log.info("userCheck 확인 : " + userCheck);

		if (userCheck.equals(user_id)) {
			model.addAttribute("msg", "본인의 권한은 변경하실 수 없습니다.");
			model.addAttribute("url", "/manager/manager_user.do");

			log.info(this.getClass().getName() + "manager/ModifuAuthorProc end!");
		} else if (!userCheck.equals("undefined")) {
			if (user_id != null) {
				if (userAuthor.equals("1")) {
					UserInfoDTO uDTO = new UserInfoDTO();
					try {
						uDTO = managerService.getUserInfo(userCheck);

					} catch (Exception e) {
						e.printStackTrace();
					}
					if (uDTO.getUser_Author().equals("0")) {
						int result = 0;
						try {
							result = managerService.alterAuthor(userCheck);
						} catch (Exception e) {
							e.printStackTrace();
						}

						if (result > 0) {
							model.addAttribute("msg", "관리자로 변경되었습니다.");
							model.addAttribute("url", "/manager/manager_user.do");

							log.info(this.getClass().getName() + "manager/ModifuAuthorProc end!");
						} else {
							model.addAttribute("msg", "관리자 변경에 실패하였습니다.");
							model.addAttribute("url", "/manager/manager_user.do");

							log.info(this.getClass().getName() + "manager/ModifuAuthorProc end!");
						}

					} else {
						int result = 0;
						try {
							result = managerService.alterUser(userCheck);
						} catch (Exception e) {
							e.printStackTrace();
						}

						if (result > 0) {
							model.addAttribute("msg", "사용자로 변경되었습니다.");
							model.addAttribute("url", "/manager/manager_user.do");

							log.info(this.getClass().getName() + "manager/ModifuAuthorProc end!");
						} else {
							model.addAttribute("msg", "사용자 변경에 실패하였습니다.");
							model.addAttribute("url", "/manager/manager_user.do");

							log.info(this.getClass().getName() + "manager/ModifuAuthorProc end!");
						}
					}
				} else {
					model.addAttribute("msg", "관리자 권한이 필요합니다.");
					model.addAttribute("url", "/mypage/dashboard_review/mypage_review.do");
				}

			} else {
				model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
				model.addAttribute("url", "/user/loginForm.do");
			}
		} else {
			model.addAttribute("msg", "사용자를 체크해주세요.");
			model.addAttribute("url", "/manager/manager_user.do");

			log.info(this.getClass().getName() + "manager/ModifuAuthorProc end!");
		}

		return "/redirect";

	}

	// 관리자 회원관리 페이지 상태 변경(정상<->정지)
	@RequestMapping(value = "manager/ModifyStatProc")
	public String ModifyStat(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "manager/ModifuStatProc start");

		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");
		String userCheck = request.getParameter("userCheck");
		log.info("userCheck 확인" + userCheck);
		if (userCheck.equals(user_id)) {
			model.addAttribute("msg", "본인의 상태는 변경하실 수 없습니다.");
			model.addAttribute("url", "/manager/manager_user.do");

			log.info(this.getClass().getName() + "manager/ModifuStatProc end!");

		} else if (!userCheck.equals("undefined")) {
			if (user_id != null) {
				if (userAuthor.equals("1")) {
					UserInfoDTO uDTO = new UserInfoDTO();
					try {
						uDTO = managerService.getUserInfo(userCheck);

					} catch (Exception e) {
						e.printStackTrace();
					}
					if (uDTO.getUser_Stat().equals("0")) {
						int result = 0;
						try {
							result = managerService.alterStop(userCheck);
						} catch (Exception e) {
							e.printStackTrace();
						}

						if (result > 0) {
							model.addAttribute("msg", "유저상태를 활동정지로 변경하였습니다.");
							model.addAttribute("url", "/manager/manager_user.do");

							log.info(this.getClass().getName() + "manager/ModifuStatProc end!");
						} else {
							model.addAttribute("msg", "유저상태 변경에 실패하였습니다.");
							model.addAttribute("url", "/manager/manager_user.do");

							log.info(this.getClass().getName() + "manager/ModifuStatProc end!");
						}

					} else if (uDTO.getUser_Stat().equals("1")) {
						int result = 0;
						try {
							result = managerService.alterNomal(userCheck);
						} catch (Exception e) {
							e.printStackTrace();
						}

						if (result > 0) {
							model.addAttribute("msg", "유저상태를 정상으로 변경하였습니다.");
							model.addAttribute("url", "/manager/manager_user.do");

							log.info(this.getClass().getName() + "manager/ModifuStatProc end!");
						} else {
							model.addAttribute("msg", "유저상태 변경에 실패하였습니다.");
							model.addAttribute("url", "/manager/manager_user.do");

							log.info(this.getClass().getName() + "manager/ModifuStatProc end!");
						}
					} else {
						model.addAttribute("msg", "탈퇴 회원의 상태는 변경하실 수 없습니다.");
						model.addAttribute("url", "/manager/manager_user.do");

						log.info(this.getClass().getName() + "manager/ModifuStatProc end!");
					}
				} else {
					model.addAttribute("msg", "관리자 권한이 필요합니다.");
					model.addAttribute("url", "/mypage/dashboard_review/mypage_review.do");
				}

			} else {
				model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
				model.addAttribute("url", "/user/loginForm.do");
			}
		} else {
			model.addAttribute("msg", "사용자를 체크해주세요.");
			model.addAttribute("url", "/manager/manager_user.do");

			log.info(this.getClass().getName() + "manager/ModifuStatProc end!");
		}

		return "/redirect";

	}

	// 관리자 회원관리 페이지 상태 변경 (정상<->탈퇴)
	@RequestMapping(value = "manager/ModifyDelProc")
	public String ModifyDel(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "manager/ModifyDelProc start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");
		String userCheck = request.getParameter("userCheck");

		log.info("userCheck 확인" + userCheck);

		if (userCheck.equals(user_id)) {
			model.addAttribute("msg", "본인의 상태는 변경하실 수 없습니다.");
			model.addAttribute("url", "/manager/manager_user.do");

		} else if (!userCheck.equals("undefined")) {
			if (user_id != null) {
				if (userAuthor.equals("1")) {
					UserInfoDTO uDTO = new UserInfoDTO();
					try {
						uDTO = managerService.getUserInfo(userCheck);

					} catch (Exception e) {
						e.printStackTrace();
					}
					if (!uDTO.getUser_Stat().equals("2")) {
						int result = 0;
						try {
							result = managerService.alterDel(userCheck);
						} catch (Exception e) {
							e.printStackTrace();
						}

						if (result > 0) {
							model.addAttribute("msg", "유저상태를 탈퇴로 변경하였습니다.");
							model.addAttribute("url", "/manager/manager_user.do");

							log.info(this.getClass().getName() + "manager/ModifyDelProc end!");
						} else {
							model.addAttribute("msg", "유저상태 변경에 실패하였습니다.");
							model.addAttribute("url", "/manager/manager_user.do");

							log.info(this.getClass().getName() + "manager/ModifyDelProc end!");
						}

					} else {
						int result = 0;
						try {
							result = managerService.alterNomal(userCheck);
						} catch (Exception e) {
							e.printStackTrace();
						}

						if (result > 0) {
							model.addAttribute("msg", "유저상태를 정상으로 변경하였습니다.");
							model.addAttribute("url", "/manager/manager_user.do");

							log.info(this.getClass().getName() + "manager/ModifyDelProc end!");
						} else {
							model.addAttribute("msg", "유저상태 변경에 실패하였습니다.");
							model.addAttribute("url", "/manager/manager_user.do");

							log.info(this.getClass().getName() + "manager/ModifyDelProc end!");
						}
					}
				} else {
					model.addAttribute("msg", "관리자 권한이 필요합니다.");
					model.addAttribute("url", "/mypage/dashboard_review/mypage_review.do");
				}

			} else {
				model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
				model.addAttribute("url", "/user/loginForm.do");
			}
		} else {
			model.addAttribute("msg", "사용자를 체크해주세요.");
			model.addAttribute("url", "/manager/manager_user.do");

			log.info(this.getClass().getName() + "manager/ModifyDelProc end!");
		}

		return "/redirect";

	}

	// 관리자 비밀번호 수정 실행
	@RequestMapping(value = "manager/userPassword")
	public String userPassword(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/userPassword start!");

		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("new_password1");
		String user_id = (String) session.getAttribute("SS_USER_ID");

		log.info("password 변경 : " + password);
		log.info("passwordRepeat 변경 : " + passwordRepeat);

		UserInfoDTO uDTO = new UserInfoDTO();

		uDTO.setPassword(EncryptUtil.encHashSHA256(password));
		uDTO.setUser_id(user_id);

		if (!password.equals(passwordRepeat)) {
			model.addAttribute("msg", "비밀번호 입력이 잘못되었습니다.");
			model.addAttribute("url", "/manager/setting.do");

			log.info(this.getClass().getName() + "./manager/userPassword end!");
		} else if (password.length() < 8 || password.length() > 20) {
			model.addAttribute("msg", "비밀번호는 8~20자 영문 대 소문자,숫자,특수문자를 사용해 주세요.");
			model.addAttribute("url", "/manager/setting.do");

			log.info(this.getClass().getName() + "./manager/userPassword end!");
		} else {
			int result = 0;
			try {
				result = managerService.updateUserPassword(uDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				model.addAttribute("msg", "수정되었습니다.");
				model.addAttribute("url", "/manager/setting.do");

				log.info("result : " + result);
				log.info(this.getClass().getName() + "./manager/userPassword end!");
			} else {
				model.addAttribute("msg", "수정에 실패했습니다.");
				model.addAttribute("url", "/manager/setting.do");

				log.info("result : " + result);
				log.info(this.getClass().getName() + "./manager/userPassword end!");
			}
		}

		return "/redirect";
	}

	// 관리자 이메일 수정 실행
	@RequestMapping(value = "manager/userEmail")
	public String userEmail(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/userEmail start!");

		String email = request.getParameter("email");
		String user_id = (String) session.getAttribute("SS_USER_ID");

		log.info("email 변경 : " + email);

		UserInfoDTO uDTO = new UserInfoDTO();

		uDTO.setEmail(EncryptUtil.encAES128CBC(email));
		uDTO.setUser_id(user_id);

		int result = 0;
		try {
			result = managerService.updateUserEmail(uDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			model.addAttribute("msg", "수정되었습니다.");
			model.addAttribute("url", "/manager/setting.do");

			log.info(this.getClass().getName() + "./manager/userEmail end!");
		} else {
			model.addAttribute("msg", "수정에 실패했습니다.");
			model.addAttribute("url", "/manager/setting.do");

			log.info(this.getClass().getName() + "./manager/userEmail end!");
		}

		return "/redirect";
	}

	// 관리자 직업 수정 실행
	@RequestMapping(value = "manager/userJob")
	public String userJob(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/userJob start!");

		String user_job = request.getParameter("user_job");
		String user_id = (String) session.getAttribute("SS_USER_ID");

		log.info("user_job 변경 : " + user_job);

		int result = 0;

		UserInfoDTO uDTO = new UserInfoDTO();

		uDTO.setUser_job(user_job);
		uDTO.setUser_id(user_id);

		try {
			result = managerService.updateUserJob(uDTO);
			log.info("result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			model.addAttribute("msg", "수정되었습니다.");
			model.addAttribute("url", "/manager/setting.do");

			log.info(this.getClass().getName() + "./manager/userJob end!");
		} else {
			model.addAttribute("msg", "수정에 실패했습니다.");
			model.addAttribute("url", "/manager/setting.do");

			log.info(this.getClass().getName() + "./manager/userJob end!");
		}

		return "/redirect";
	}

	// 유치원관리 화면으로 이동
	@RequestMapping(value = "manager/manager_kid")
	public String Manager_kid(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/manager_kid start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {

			log.info(this.getClass());

			int pgNum = 1;

			if (request.getParameter("pgNum") != null) {
				pgNum = Integer.parseInt(request.getParameter("pgNum"));
			}

			int startNum = (pgNum - 1) * 10 + 1;
			int endNum = (pgNum - 1) * 10 + 10;

			List<KidInfoDTO> nList = new ArrayList<>();

			int kid_seq = 1;

			if (request.getParameter("kid_seq") != null) {
				kid_seq = Integer.parseInt(request.getParameter("kid_seq"));
			}

			int total = Integer.parseInt(managerService.getKidCnt());

			try {
				nList = managerService.getManager_kidList(user_id, startNum, endNum);

				model.addAttribute("pgNum", pgNum);
				model.addAttribute("total", total);

				model.addAttribute("nList", nList);
			} catch (Exception e) {
				e.printStackTrace();
			}

			log.info(this.getClass().getName() + "./manager/manager_kid end!");
			return "/manager/manager_kid/manager_kid";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 유치원관리 상세화면으로 이동
	@RequestMapping(value = "manager/manager_kid_detail")
	public String Manager_kid_detail(HttpSession session, HttpServletRequest request, Model model) throws Exception {

		log.info(this.getClass().getName() + "./manager/manager_kid_detail start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {

			int kid_seq = Integer.parseInt(request.getParameter("kid_seq"));
			log.info("manager_kid_detail 시작");
			int cnt = 0;
			try {
				cnt = Integer.parseInt(managerService.getKidCnt());
			} catch (Exception e) {
				e.printStackTrace();
			}
			KidInfoDTO nDTO = new KidInfoDTO();
			try {
				nDTO = managerService.getKidDetail(kid_seq);

			} catch (Exception e) {
				e.printStackTrace();
			}
			if (nDTO == null) {
				model.addAttribute("msg", "유치원 정보가 없습니다.");
				model.addAttribute("url", "/manager/manager_kid.do");

				log.info(this.getClass().getName() + "./manager/manager_kid_detail end!");
				return "redirect";
			} else {
				filterContent(nDTO);
				
				model.addAttribute("nDTO", nDTO);

				log.info(this.getClass().getName() + "./manager/manager_kid_detail end!");
				return "/manager/manager_kid/manager_kid_detail";
			}

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 유치원 등록 페이지
	@RequestMapping(value = "manager/manager_kid_reg")
	public String Manager_kid_reg(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./manager/manager_kid_reg start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {

			log.info(this.getClass().getName() + "./manager/manager_kid_reg end!");
			return "/manager/manager_kid/manager_kid_reg";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 유치원 등록 실행
	@RequestMapping(value = "manager/manager_kid_RegProc")
	public String Manager_kid_RegProc(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/manger_kid_RegProc start!");

		String kindername = request.getParameter("kindername");
		String rppnname = request.getParameter("rppnname");
		String ldgrname = request.getParameter("ldgrname");
		String establish = request.getParameter("establish");
		String edate = request.getParameter("edate");
		String odate = request.getParameter("odate");
		String officeedu = request.getParameter("officeedu");
		String subofficeedu = request.getParameter("subofficeedu");
		String hpaddr = request.getParameter("hpaddr");
		String opertime = request.getParameter("opertime");
		String telno = request.getParameter("telno");
		String addr = request.getParameter("addr");
		String clcnt3 = request.getParameter("clcnt3");
		String clcnt4 = request.getParameter("clcnt4");
		String clcnt5 = request.getParameter("clcnt5");
		String mixclcnt = request.getParameter("mixclcnt");
		String shclcnt = request.getParameter("shclcnt");
		String ppcnt3 = request.getParameter("ppcnt3");
		String ppcnt4 = request.getParameter("ppcnt4");
		String ppcnt5 = request.getParameter("ppcnt5");
		String mixppcnt = request.getParameter("mixppcnt");
		String shppcnt = request.getParameter("shppcnt");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {
			KidInfoDTO fDTO = new KidInfoDTO();
			fDTO.setKindername(kindername);
			fDTO.setRppnname(rppnname);
			fDTO.setLdgrname(ldgrname);
			fDTO.setEstablish(establish);
			fDTO.setEdate(edate);
			fDTO.setOdate(odate);
			fDTO.setOfficeedu(officeedu);
			fDTO.setSubofficeedu(subofficeedu);
			fDTO.setHpaddr(hpaddr);
			fDTO.setOpertime(opertime);
			fDTO.setTelno(telno);
			fDTO.setAddr(addr);
			fDTO.setClcnt3(clcnt3);
			fDTO.setClcnt4(clcnt4);
			fDTO.setClcnt5(clcnt5);
			fDTO.setMixclcnt(mixclcnt);
			fDTO.setShclcnt(shclcnt);
			fDTO.setPpcnt3(ppcnt3);
			fDTO.setPpcnt4(ppcnt4);
			fDTO.setPpcnt5(ppcnt5);
			fDTO.setMixppcnt(mixppcnt);
			fDTO.setShppcnt(shppcnt);

			int result = 0;

			log.info("!kindername : " + kindername);
			log.info("!rppnname : " + rppnname);
			log.info("!ldgrname : " + ldgrname);
			log.info("!establish : " + establish);
			log.info("!edate : " + edate);
			log.info("!odate : " + odate);
			log.info("!officeedu : " + officeedu);
			log.info("!subofficeedu : " + subofficeedu);
			log.info("!hpaddr : " + hpaddr);
			log.info("!opertime : " + opertime);
			log.info("!telno : " + telno);
			log.info("!addr : " + addr);
			log.info("!clcnt3 : " + clcnt3);
			log.info("!clcnt4 : " + clcnt4);
			log.info("!clcnt5 : " + clcnt5);
			log.info("!mixclcnt : " + mixclcnt);
			log.info("!shclcnt : " + shclcnt);
			log.info("!ppclcnt3 : " + ppcnt3);
			log.info("!ppclcnt4 : " + ppcnt4);
			log.info("!ppclcnt5 : " + ppcnt5);
			log.info("!mixppclcnt : " + mixppcnt);
			log.info("!shppclcnt : " + shppcnt);

			try {
				result = managerService.insertKidInfo(fDTO);
				log.info(this.getClass().getName() + ".test!");
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				model.addAttribute("msg", "게시물이 등록되었습니다.");
				model.addAttribute("url", "/manager/manager_kid.do");

				log.info(this.getClass().getName() + "./manager/manger_kid_RegProc end!");
			} else {
				model.addAttribute("msg", "게시물 등록에 실패했습니다.");
				model.addAttribute("url", "/manager/manager_kid_reg.do");

				log.info(this.getClass().getName() + "./manager/manger_kid_RegProc end!");
			}

			log.info(this.getClass().getName() + ".manager_kid_RegProc ok!");

			return "/redirect";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 유치원 삭제
	@RequestMapping(value = "manager/manager_kid_Delete")
	public String Manager_FreeDelete(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/manger_kid_Delete start!");

		String kid_seq = request.getParameter("kid_seq");
		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

		if (user_id != null) {
			if (userAuthor.equals("1")) {
				int result = 0;

				try {
					result = managerService.deleteKidInfo(kid_seq);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (result > 0) {
					model.addAttribute("url", "/manager/manager_kid.do");
					model.addAttribute("msg", "삭제되었습니다.");

					log.info(this.getClass().getName() + "./manager/manger_kid_Delete end!");
				} else {
					model.addAttribute("url", "/manager/manager_kid.do");
					model.addAttribute("msg", "삭제에 실패했습니다.");

					log.info(this.getClass().getName() + "./manager/manger_kid_Delete end!");
				}
			} else {
				model.addAttribute("msg", "관리자 권한이 필요합니다.");
				model.addAttribute("url", "/manager/board_review.do");
			}
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
		}
		return "/redirect";
	}

	// 유치원 수정으로 들어가는 페이지
	@RequestMapping(value = "manager/manager_kid_modify")
	public String Manager_kid_modify(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./manager/manager_kid_modify start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {
			int kid_seq = Integer.parseInt(request.getParameter("kid_seq"));

			KidInfoDTO nDTO = new KidInfoDTO();

			try {
				nDTO = managerService.getKidDetail(kid_seq);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (nDTO == null) {
				nDTO = new KidInfoDTO();
			} else {
				filterContent(nDTO);
			}
			
			model.addAttribute("nDTO", nDTO);

			log.info(this.getClass().getName() + "./manager/manager_kid_modify end!");
			return "/manager/manager_kid/manager_kid_modify";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 유치원 수정 실행
	@RequestMapping(value = "manager/manager_kid_modify_Proc")
	public String Manager_kid_modify_Proc(HttpServletRequest request, HttpSession session, Model model)
			throws Exception {

		log.info(this.getClass().getName() + "./manager_kid_modify_Proc start!");

		String kid_seq = request.getParameter("kid_seq");
		String user_id = (String) session.getAttribute("SS_USER_ID");

		String kindername = request.getParameter("kindername");
		String rppnname = request.getParameter("rppnname");
		String ldgrname = request.getParameter("ldgrname");
		String establish = request.getParameter("establish");
		String edate = request.getParameter("edate");
		String odate = request.getParameter("odate");
		String officeedu = request.getParameter("officeedu");
		String subofficeedu = request.getParameter("subofficeedu");
		String hpaddr = request.getParameter("hpaddr");
		String opertime = request.getParameter("opertime");
		String telno = request.getParameter("telno");
		String addr = request.getParameter("addr");
		String clcnt3 = request.getParameter("clcnt3");
		String clcnt4 = request.getParameter("clcnt4");
		String clcnt5 = request.getParameter("clcnt5");
		String mixclcnt = request.getParameter("mixclcnt");
		String shclcnt = request.getParameter("shclcnt");
		String ppcnt3 = request.getParameter("ppcnt3");
		String ppcnt4 = request.getParameter("ppcnt4");
		String ppcnt5 = request.getParameter("ppcnt5");
		String mixppcnt = request.getParameter("mixppcnt");
		String shppcnt = request.getParameter("shppcnt");

		KidInfoDTO nDTO = new KidInfoDTO();

		nDTO.setKindername(kindername);
		nDTO.setRppnname(rppnname);
		nDTO.setLdgrname(ldgrname);
		nDTO.setEstablish(establish);
		nDTO.setEdate(edate);
		nDTO.setOdate(odate);
		nDTO.setOfficeedu(officeedu);
		nDTO.setSubofficeedu(subofficeedu);
		nDTO.setHpaddr(hpaddr);
		nDTO.setOpertime(opertime);
		nDTO.setTelno(telno);
		nDTO.setAddr(addr);
		nDTO.setClcnt3(clcnt3);
		nDTO.setClcnt4(clcnt4);
		nDTO.setClcnt5(clcnt5);
		nDTO.setMixclcnt(mixclcnt);
		nDTO.setShclcnt(shclcnt);
		nDTO.setPpcnt3(ppcnt3);
		nDTO.setPpcnt4(ppcnt4);
		nDTO.setPpcnt5(ppcnt5);
		nDTO.setMixppcnt(mixppcnt);
		nDTO.setShppcnt(shppcnt);
		nDTO.setKid_seq(kid_seq);

		if (user_id != null) {
			int result = 0;

			try {
				result = managerService.updateKid(nDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				model.addAttribute("msg", "수정되었습니다.");
				model.addAttribute("url", "/manager/manager_kid_detail.do?kid_seq=" + kid_seq);

				log.info(this.getClass().getName() + "./manager_kid_modify_Proc end!");
			} else {
				model.addAttribute("msg", "수정을 실패했습니다.");
				model.addAttribute("url", "/manager/manager_kid_modify.do");

				log.info(this.getClass().getName() + "./manager_kid_modify_Proc end!");
			}

			return "/redirect";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	public KidInfoDTO filterContent(KidInfoDTO nDTO) {

		if (nDTO.getEstablish() != null) {
			nDTO.setEstablish(nDTO.getEstablish().replaceAll("scr!pt", "script"));
			nDTO.setEstablish(nDTO.getEstablish().replaceAll("& lt;script& gt;", "&lt;script&gt;"));
			nDTO.setEstablish(nDTO.getEstablish().replaceAll("& lt;/script& gt;", "&lt;/script&gt;"));
			nDTO.setEstablish(nDTO.getEstablish().replaceAll("& #39;", "&#39;"));
			nDTO.setEstablish(nDTO.getEstablish().replaceAll("& lt;", "<"));
			nDTO.setEstablish(nDTO.getEstablish().replaceAll("& gt;", ">"));
			nDTO.setEstablish(nDTO.getEstablish().replaceAll("& #40;", "("));
			nDTO.setEstablish(nDTO.getEstablish().replaceAll("& #41;", ")"));
			nDTO.setEstablish(nDTO.getEstablish().replaceAll("&nbsp;", " "));
			log.info(nDTO.getEstablish());
		}
		return nDTO;
	}
}
