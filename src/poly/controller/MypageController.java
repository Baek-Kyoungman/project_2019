package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import poly.dto.UserInfoDTO;
import poly.service.IMypageService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

// Controller 선언해야만 스프링 프레임 워크에서 Controller인지 인식 가능, 자바 서블릿 역할 수행
@Controller
public class MypageController {
	private Logger log = Logger.getLogger(this.getClass());

	// 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
	@Resource(name = "MypageService")
	private IMypageService mypageService;

	// 설정 화면으로 이동
	@RequestMapping(value = "mypage/setting")
	public String Setting(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./mypage/setting start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");
		String user_name = (String) session.getAttribute("SS_USER_NAME");
		String email = (String) session.getAttribute("SS_EMAIL");
		String user_job = (String) session.getAttribute("SS_USER_JOB");

		UserInfoDTO pDTO = null;

		pDTO = new UserInfoDTO();

		log.info("Mypage_user_id : " + user_id);
		log.info("Mypage_user_name : " + user_name);
		log.info("Mypage_email : " + email);
		log.info("Mypage_user_job : " + user_job);

		if (user_id != null) {

			log.info(this.getClass().getName() + "./mypage/setting end!");
			return "/mypage/Modify/mypage_setting";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 사용자 회원 탈퇴
	@RequestMapping(value = "mypage/userDelete")
	public String userDelete(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./mypage/userDelete start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		log.info("회원탈퇴_user_id : " + user_id);
		int result = 0;

		try {
			result = mypageService.deleteUserInfo(user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result > 0) {
			model.addAttribute("url", "/Logoutbtn.do");
			model.addAttribute("msg", "회원탈퇴에 성공하셨습니다. 60일 이내에 취소 하실 수 있습니다. 취소하시려면 전화 또는 Email로 문의해주세요.");

			log.info(this.getClass().getName() + "./mypage/userDelete end!");
		} else {
			model.addAttribute("url", "/mypage/setting.do");
			model.addAttribute("msg", "회원탈퇴에 실패했습니다.");

			log.info(this.getClass().getName() + "./mypage/userDelete end!");
		}

		return "/redirect";
	}

	// 사용자 비밀번호 수정 실행
	@RequestMapping(value = "mypage/userPassword")
	public String userPassword(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./mypage/userPassword start!");

		String password = CmmUtil.nvl(request.getParameter("password"));
		String passwordRepeat = CmmUtil.nvl(request.getParameter("new_password1"));
		String user_id = (String) session.getAttribute("SS_USER_ID");

		log.info("password 변경 : " + password);
		log.info("passwordRepeat 변경 : " + passwordRepeat);

		UserInfoDTO uDTO = new UserInfoDTO();

		uDTO.setPassword(EncryptUtil.encHashSHA256(password));
		uDTO.setUser_id(user_id);

		if (!password.equals(passwordRepeat)) {
			model.addAttribute("msg", "비밀번호 입력이 잘못되었습니다.");
			model.addAttribute("url", "/mypage/setting.do");

			log.info(this.getClass().getName() + "./mypage/userPassword end!");
		} else if (password.length() < 8 || password.length() > 20) {
			model.addAttribute("msg", "비밀번호는 8~20자 영문 대 소문자,숫자,특수문자를 사용해 주세요.");
			model.addAttribute("url", "/mypage/setting.do");

			log.info(this.getClass().getName() + "./mypage/userPassword end!");
		} else {
			int result = 0;
			try {
				result = mypageService.updateUserPassword(uDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				model.addAttribute("msg", "수정되었습니다.");
				model.addAttribute("url", "/mypage/setting.do");

				log.info("result : " + result);
				log.info(this.getClass().getName() + "./mypage/userPassword end!");
			} else {
				model.addAttribute("msg", "수정에 실패했습니다.");
				model.addAttribute("url", "/mypage/setting.do");

				log.info(this.getClass().getName() + "./mypage/userPassword end!");
			}
		}

		return "/redirect";
	}

	// 사용자 이메일 수정 실행
	@RequestMapping(value = "mypage/userEmail")
	public String userEmail(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./mypage/userEmail start!");

		String email = CmmUtil.nvl(request.getParameter("email"));
		String user_id = (String) session.getAttribute("SS_USER_ID");

		log.info("email 변경 : " + email);

		UserInfoDTO uDTO = new UserInfoDTO();

		uDTO.setEmail(EncryptUtil.encAES128CBC(email));
		uDTO.setUser_id(user_id);

		int result = 0;
		try {
			result = mypageService.updateUserEmail(uDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			model.addAttribute("msg", "수정되었습니다.");
			model.addAttribute("url", "/mypage/setting.do");

			log.info(this.getClass().getName() + "./mypage/userEmail end!");
		} else {
			model.addAttribute("msg", "수정에 실패했습니다.");
			model.addAttribute("url", "/mypage/setting.do");

			log.info(this.getClass().getName() + "./mypage/userEmail end!");
		}

		return "/redirect";
	}

	// 사용자 직업 수정 실행
	@RequestMapping(value = "mypage/userJob")
	public String userJob(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./mypage/userJob start!");

		String user_job = CmmUtil.nvl(request.getParameter("user_job"));
		String user_id = (String) session.getAttribute("SS_USER_ID");

		log.info("user_job 변경 : " + user_job);

		int result = 0;

		UserInfoDTO uDTO = new UserInfoDTO();

		uDTO.setUser_job(user_job);
		uDTO.setUser_id(user_id);

		try {
			result = mypageService.updateUserJob(uDTO);
			log.info("result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			model.addAttribute("msg", "수정되었습니다.");
			model.addAttribute("url", "/mypage/setting.do");

			log.info(this.getClass().getName() + "./mypage/userJob end!");
		} else {
			model.addAttribute("msg", "수정에 실패했습니다.");
			model.addAttribute("url", "/mypage/setting.do");

			log.info(this.getClass().getName() + "./mypage/userJob end!");
		}

		return "/redirect";
	}
}
