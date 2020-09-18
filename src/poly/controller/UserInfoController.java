package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.R_CommentDTO;
import poly.dto.ReviewDTO;
import poly.dto.UserInfoDTO;
import poly.service.IFreeService;
import poly.service.IReviewService;
import poly.service.IUserInfoService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

/*
 * Controller 선언해야만 Spring 프레임워크에서 Controller인지 인식 가능
 * 자바 서블릿 역활 수행
 * */
@Controller
public class UserInfoController {
	private Logger log = Logger.getLogger(this.getClass());

	// 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤 패턴 적용됨)
	@Resource(name = "UserInfoService")
	private IUserInfoService userInfoService;

	// 비즈니스 로직
	@Resource(name = "ReviewService")
	private IReviewService reviewService;

	private String user_name;

	// 메인 화면으로 이동
	@RequestMapping(value = "/main/index")
	public String index(Model model) throws Exception {
		log.info(this.getClass().getName() + "./main/index start!");

		List<ReviewDTO> rList = new ArrayList<>();

		try {
			rList = reviewService.getIndexList();

			model.addAttribute("rList", rList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (rList == null) {
			rList = new ArrayList<ReviewDTO>();
		} else {
			for (int i = 0; i < rList.size(); i++) {
				filterContent(rList.get(i));
			}
		}

		log.info(this.getClass().getName() + "./main/index end!");
		return "/main/index";
	}

	// 로그인을 위한 입력 화면으로 이동
	@RequestMapping(value = "user/loginForm")
	public String loginForm() throws Exception {
		log.info(this.getClass().getName() + ".user/loginForm ok!");

		return "/user/LoginForm";
	}

	// 로그인 처리 및 결과 알려주는 화면으로 이동
	@RequestMapping(value = "user/getUserLoginCheck")
	public String getUserLoginCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + ".getUserLoginCheck start!");

		// 로그인 처리 결과를 저장할 변수 (로그인 성공 : 1, 아이디, 비밀번호 불일치로 인한 실패 : 0, 시스템 에러 : 2)
		int res = 0;

		// 웹(회원정보 입력화면)에서 받는 정보를 저장할 변수
		UserInfoDTO pDTO = null;

		pDTO = new UserInfoDTO();

		try {

			/*
			 * ################################################################ 웹(회원정보
			 * 입력화면)에서 받는 정보를 String 변수에 저장 시작!!
			 * 
			 * 무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장함
			 * ################################################################
			 */

			String user_id = CmmUtil.nvl(request.getParameter("user_id")); // 아이디
			String password = CmmUtil.nvl(request.getParameter("password")); // 비밀번호

			/*
			 * ################################################################ 웹(회원정보
			 * 입력화면)애소 받는 정보를 String 변수에 저장 끝!!
			 * 
			 * 무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장함
			 * ################################################################
			 */

			/*
			 * ################################################################ 반드시, 값을
			 * 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함
			 * 
			 * 반드시 작성할 것 ################################################################
			 */

			log.info("로그인_user_id : " + user_id);
			log.info("로그인_password : " + password);

			/*
			 * ################################################################ 웹(회원정보
			 * 입력화면)에서 받는 정보를 DTO에 저장하기 시작!!
			 * 
			 * 무조건 웹으로 받은 정보는 DTO에 저장해야 한다고 이해하길 권함
			 * ################################################################
			 */

			// 웹(회원정보 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
			pDTO = new UserInfoDTO();

			pDTO.setUser_id(user_id);

			// 비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화함
			pDTO.setPassword(EncryptUtil.encHashSHA256(password));

			/*
			 * ################################################################ 웹(회원정보
			 * 입력화면)에서 받는 정보를 DTO에 저장하기 끝!!
			 * 
			 * 무조건 웹으로 받은 정보는 DTO에 저장해야 한다고 이해하길 권함
			 * 
			 */

			// 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 userInfoService 호출하기
			pDTO = userInfoService.getUserLoginCheck(pDTO);

			/*
			 * 로그인을 성공했다면, 회원아이디 정보를 session에 저장함
			 * 
			 * 세션은 톰켓(was)의 메모리에 존재하며, 웹사이트에 접속한 사람(연결된 객체)akek apahfldp rkqtdmf dhfflsek.
			 * 
			 * 예) 톰켓에 100명의 사용자가 로그인했다면, 사용자 각각 회원아이디를 메모리에 저장하며, 메모리에 저장된 객체의 수는 100개이다.
			 * 따라서 과도한 세션은 톰켓의 메모리 부하를 발생시켜 서버가 다운되는 현상이 있을 수 있기 때문에, 최소한으로 사용하는 것을 권장한다.
			 * 
			 * 스프링에서 세션을 사용하기 위해서는 함수명의 파라미터에 HttpSession session 존재해야한다. 세션은 톰켓의 메모리에 저장되기
			 * 때문에 url마다 전달하는게 필요하지 않고, 그냥 메모리에서 부르면 되기 때문에 jsp, controller에서 쉽게 불러서 쓸 수 있다.
			 */

			if (pDTO == null) { // 로그인 성공

				model.addAttribute("msg", "로그인에 실패하셨습니다.");
				model.addAttribute("url", "/user/loginForm.do");

				/*
				 * 세션에 회원 아이디 저장하기, 추후 로그인 여부를 체크하기 위해 세션에 값이 존재하는지 체크한다. 일반적으로 세션에 저장되는 키는 데문자로
				 * 입력하며, 앞에 SS를 붙인다.
				 * 
				 * Session 단어에서 SS를 가져온 것이다.
				 */
			} else if (pDTO.getUser_Stat().equals("1")) {
				model.addAttribute("msg", "활동정지된 회원입니다. 전화 또는 Email로 문의해주세요.");
				model.addAttribute("url", "/user/loginForm.do");

				log.info(this.getClass().getName() + ".getUserLoginCheck end!");
			} else if (pDTO.getUser_Stat().equals("2")) {
				model.addAttribute("msg", "탈퇴된 회원입니다. 탈퇴를 취소 하시려면 전화 또는 Email로 문의해주세요.");
				model.addAttribute("url", "/user/loginForm.do");

				log.info(this.getClass().getName() + ".getUserLoginCheck end!");
			} else if (pDTO.getUser_Author().equals("0")) {
				String email = EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail()));
				session.setAttribute("SS_USERAUTHOR", "0");
				session.setAttribute("SS_USER_ID", pDTO.getUser_id());
				session.setAttribute("SS_USER_NAME", pDTO.getUser_name());
				session.setAttribute("SS_EMAIL", email);
				session.setAttribute("SS_USER_JOB", pDTO.getUser_job());
				model.addAttribute("msg", "로그인에 성공하셨습니다.");
				model.addAttribute("url", "/main/index.do");

				log.info(this.getClass().getName() + ".getUserLoginCheck end!");
			} else if (pDTO.getUser_Author().equals("1")) {
				String email = EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail()));
				session.setAttribute("SS_USERAUTHOR", "1");
				session.setAttribute("SS_USER_ID", pDTO.getUser_id());
				session.setAttribute("SS_USER_NAME", pDTO.getUser_name());
				session.setAttribute("SS_EMAIL", email);
				session.setAttribute("SS_USER_JOB", pDTO.getUser_job());
				model.addAttribute("msg", "관리자 로그인에 성공하셨습니다.");
				model.addAttribute("url", "/main/index.do");

				log.info(this.getClass().getName() + ".getUserLoginCheck end!");
			}

		} catch (Exception e) {
			// 저장이 실패되면 사용자에게 보여줄 메시지
			res = 2;
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName() + ".insertUserInfo end!");

			/*
			 * 로그인 처리 결과를 jsp에 전달하기 위해 변수 사용 숫자 유형의 데이터 타입은 값을 전달하고 받는데 불편함이 있어 문자
			 * 유형(String)으로 강제 형변환하여 jsp에 전달한다.
			 */
			model.addAttribute("pDTO", pDTO);

			// 변수 초기화(메모리 효율화 시키기 위해 사용함)
			pDTO = null;

		}

		return "/redirect";
	}

	// 로그아웃 버튼
	@RequestMapping(value = "Logoutbtn")
	public String Logoutbtn(HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./Logoutbtn start!");

		session.invalidate();

		model.addAttribute("msg", "로그아웃 하셨습니다.");
		model.addAttribute("url", "/main/index.do");
		return "/redirect";
	}

	// 회원가입 화면으로 이동
	@RequestMapping(value = "/user/userRegForm")
	public String userRegForm() throws Exception {

		log.info(this.getClass().getName() + ".user/userRegForm ok!");

		return "/user/userRegForm";
	}

	// 회원가입 로직 처리
	@RequestMapping(value = "user/insertUserInfo")
	public String insertUserInfo(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + ".insertUserInfo start!");

		// 회원가입 결과에 대한 메시지를 전달할 변수
		String msg = "";

		// 웹(회원정보 입력화면에서 받는 정보를 저장할 변수
		UserInfoDTO pDTO = null;

		try {

			/*
			 * ########################################################## 
			 * 웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 시작!!
			 * 무조건 웹으로 받은 정보는 DTO에 저장하기 웨해 임시로 String 변주에 저장함
			 * ##########################################################
			 */
			String user_job = CmmUtil.nvl(request.getParameter("user_job"));
			String user_id = CmmUtil.nvl(request.getParameter("user_id")); // 아이디
			String user_name = CmmUtil.nvl(request.getParameter("user_name")); // 이름
			String password = CmmUtil.nvl(request.getParameter("password")); // 비밀번호
			String email = CmmUtil.nvl(request.getParameter("email")); // 이메일
			String user_marketing = CmmUtil.nvl(request.getParameter("user_marketing")); // 마케팅

			log.info("회원가입 user_job : " + user_job);

			if (CmmUtil.nvl(request.getParameter("user_job")) == null) {
				user_job = "직업";
			}
			if (CmmUtil.nvl(request.getParameter("user_marketing")) == null) {
				user_marketing = "비동의";
			} else {
				user_marketing = "동의";
			}

			/*
			 * ########################################################## 
			 * 웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 끝!!
			 * 무조건 웹으로 받은 정보는 DTO에 저장하기 웨해 임시로 String 변주에 저장함
			 * ##########################################################
			 */

			/*
			 * ########################################################## 
			 * 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
			 * ##########################################################
			 */

			log.info("user_id : " + user_id);
			log.info("user_name : " + user_name);
			log.info("password : " + password);
			log.info("email : " + email);
			log.info("user_job : " + user_job);
			log.info("user_marketing : " + user_marketing);

			/*
			 * ########################################################## 웹(회원정보 입력화면)에서 받는
			 * 정보를 DTO에 저장하기 시작!!
			 * 
			 * 무조건 웹으로 받은 정보는 DTO에 저장해야 한다고 이해하길 권함
			 * ##########################################################
			 */

			// 웹(회원정보 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
			pDTO = new UserInfoDTO();
			pDTO.setUser_id(user_id);
			pDTO.setUser_name(user_name);
			// 비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화함
			pDTO.setPassword(EncryptUtil.encHashSHA256(password));
			// 민감 정보인 이메일은 AES128-CBC로 암호화함
			pDTO.setEmail(EncryptUtil.encAES128CBC(email));
			pDTO.setUser_job(user_job);
			pDTO.setUser_marketing(user_marketing);

			/*
			 * ########################################################## 
			 * 웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기 끝!!
			 * 무조건 웹으로 받은 정보는 DTO에 저장해야 한다고 이해하길 권함
			 * ##########################################################
			 */

			// 회원가입
			int res = userInfoService.insertUserInfo(pDTO);

			if (res == 1) {
				model.addAttribute("url", "/user/loginForm.do");
				msg = "회원가입되었습니다.";
				log.info("res : " + res);
				
				return "/redirect";
				// 추후 회원가입 입력회면에서 ajax를 활용해서 아이디 중복, 이메일 중복을 체크하길 바람
			} else if (res == 2) {
				model.addAttribute("url", "/user/userRegForm.do");
				msg = "이미 가입된 이메일 주소입니다.";
				log.info("res : " + res);
				
				return "/redirect";
			} else {
				model.addAttribute("url", "/user/userRegForm.do");
				msg = "오류로 인해 회원가입이 실패하였습니다.";
				log.info("res : " + res);
				
				return "/redirect";
			}

		} catch (Exception e) {
			// 저장이 실패되면 사용자에게 보여줄 메시지
			msg = "실패하였습니다. : " + e.toString();
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName() + ".insertUserInfo end!");

			// 회원가입 여부 결과 메시지 전달하기
			model.addAttribute("msg", msg);

			// 회원가입 여부 결과 메시지 전달하기
			model.addAttribute("pDTO", pDTO);

			// 변수 초기화(메모리 효율화 시키기 위해 사용함)
			pDTO = null;
		}

		return "/user/Msg";

	}

	// 아이디 비밀번호 찾기 화면으로 이동
	@RequestMapping(value = "user/userFind")
	public String userFind() throws Exception {

		log.info(this.getClass() + ".user/userFind ok!");

		return "/user/userFind";
	}

	// 아이디 찾기 실행
	@RequestMapping(value = "user/IdFind_Proc")
	public String IdFind_Proc(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + ".IdFind_Proc start!");

		int res = 0;

		// 웹(아이디찾기 실행, 입력화면)에서 받는 정보를 저장할 변수
		UserInfoDTO pDTO = null;

		pDTO = new UserInfoDTO();

		try {

			String user_name = CmmUtil.nvl(request.getParameter("user_name")); // 이름
			String email = CmmUtil.nvl(request.getParameter("email")); // 이메일

			log.info("아이디찾기_user_name : " + user_name);
			log.info("아이디찾기_email : " + email);

			// 웹(아이디 실행, 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
			pDTO = new UserInfoDTO();

			pDTO.setUser_name(user_name);
			pDTO.setEmail(EncryptUtil.encAES128CBC(email));
			
			// 아이디찾기 실행을 위해 이름과 이메일이 일치하는지 확인하기 위한 userInfoService 호출하기
			pDTO = userInfoService.getUserIdCheck(pDTO);

			if (pDTO == null) {

				model.addAttribute("msg", "본인인증에 실패하셨습니다.");
				model.addAttribute("url", "/user/userFind.do");

			} else {
				session.setAttribute("ID_USER_NAME", user_name);
				session.setAttribute("ID_EMAIL", email);
				session.setAttribute("ID_USER_ID", pDTO.getUser_id());
				model.addAttribute("msg", "본인 인증이 되었습니다.");
				model.addAttribute("url", "/user/Id_Find.do");
			}

		} catch (Exception e) {
			// 저장이 실패되면 사용자에게 보여줄 메시지
			res = 2;
			log.info(e.toString());
			e.printStackTrace();
		} finally {

			log.info(this.getClass().getName() + ".IdFind_Proc end!");
			model.addAttribute("pDTO", pDTO);

			// 변수 초기화(메모리 효율화 시키기 위해 사용함)
			pDTO = null;

		}

		return "/redirect";
	}

	// 아이디 찾기 화면
	@RequestMapping(value = "user/Id_Find")
	public String Id_Find(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {

		log.info(this.getClass().getName() + ".Id_Find start!");

		UserInfoDTO pDTO = new UserInfoDTO();

		String user_name = (String) session.getAttribute("ID_USER_NAME");
		String email = (String) session.getAttribute("ID_EMAIL");
		String user_id = (String) session.getAttribute("ID_USER_ID");

		pDTO.setUser_name(user_name);
		pDTO.setEmail(email);
		pDTO.setUser_id(user_id);

		try {

			pDTO = userInfoService.getUserIdFind(pDTO);

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("pDTO", pDTO);

		log.info(this.getClass().getName() + ".Id_Find end!");

		return "/user/Id_Find";

	}

	// 비밀번호 찾기1
	// 비밀번호 찾기 알려주는 화면으로 이동
	@RequestMapping(value = "user/PasswordFind1")
	public String PasswordFind1(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + ".PasswordFind1 start!");

		// 비밀번호 찾기 처리 결과를 저장할 변수 (로그인 성공 : 1, 아이디, 비밀번호 불일치로 인한 실패 : 0, 시스템 에러 : 2)
		int res = 0;

		// 웹(비밀번호찾기 입력화면)에서 받는 정보를 저장할 변수
		UserInfoDTO pDTO = null;

		pDTO = new UserInfoDTO();

		try {

			String user_id = CmmUtil.nvl(request.getParameter("user_id")); // 아이디

			log.info("비밀번호 찾기_user_id : " + user_id);

			// 웹(비밀번호찾기 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
			pDTO = new UserInfoDTO();

			pDTO.setUser_id(user_id);

			// 비밀번호찾기를 위해 아이디가 존재하는지 확인하기 위한 userInfoService 호출하기
			pDTO = userInfoService.password_find(pDTO);

			if (pDTO == null) {

				model.addAttribute("msg", "가입된 아이디가 없습니다.");
				model.addAttribute("url", "/user/userFind.do");

			} else {
				model.addAttribute("url", "/user/PasswordFind2.do");

			}
		} catch (Exception e) {
			// 저장이 실패되면 사용자에게 보여줄 메시지
			res = 2;
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName() + ".Password_find end!");

			model.addAttribute("pDTO", pDTO);

			pDTO = null;

		}

		return "/redirect2";
	}

	// 비밀번호 찾기2
	@RequestMapping(value = "user/PasswordFind2")
	public String PasswordFind2() throws Exception {

		log.info(this.getClass() + ".user/userPasswordFind2 ok!");

		return "/user/PasswordFind2";
	}

	// 비밀번호 찾기 실행
	// 비밀번호찾기 처리 및 결과 알려주는 화면으로 이동
	@RequestMapping(value = "user/PasswordFind_Proc")
	public String PasswordFind_Proc(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + ".PasswordFind_Proc start!");

		// 비밀번호찾기 처리 결과를 저장할 변수 (로그인 성공 : 1, 아이디, 비밀번호 불일치로 인한 실패 : 0, 시스템 에러 : 2)
		int res = 0;

		// 웹(비밀번호찾기 실행, 입력화면)에서 받는 정보를 저장할 변수
		UserInfoDTO pDTO = null;

		pDTO = new UserInfoDTO();

		try {

			String user_name = CmmUtil.nvl(request.getParameter("user_name")); // 아이디
			String email = CmmUtil.nvl(request.getParameter("email")); // 이메일

			log.info("비밀번호찾기_user_name : " + user_name);
			log.info("비밀번호찾기_email : " + email);

			// 웹(비밀번호찾기 실행, 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
			pDTO = new UserInfoDTO();

			pDTO.setUser_name(user_name);
			pDTO.setEmail(EncryptUtil.encAES128CBC(email));

			// 비밀번호찾기 실행을 위해 아이디와 이메일이 일치하는지 확인하기 위한 userInfoService 호출하기
			pDTO = userInfoService.getUserPasswordCheck(pDTO);

			if (pDTO == null) {

				model.addAttribute("msg", "본인인증에 실패하셨습니다.");
				model.addAttribute("url", "/user/PasswordFind2.do");

			} else {
				session.setAttribute("SS_USER_NAME", user_name);
				session.setAttribute("SS_EMAIL", pDTO.getEmail());
				model.addAttribute("msg", "본인 인증이 되었습니다. 비밀번호를 재설정 합니다.");
				model.addAttribute("url", "/user/Password_Update.do");
			}

		} catch (Exception e) {
			// 저장이 실패되면 사용자에게 보여줄 메시지
			res = 2;
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName() + ".PasswordFind_Proc end!");

			model.addAttribute("pDTO", pDTO);

			// 변수 초기화(메모리 효율화 시키기 위해 사용함)
			pDTO = null;

		}

		return "/redirect";
	}

	// 비밀번호 찾기 3
	@RequestMapping(value = "user/Password_Update")
	public String Password_Update() throws Exception {

		log.info(this.getClass().getName() + ".user/Password_Update start!");

		return "/user/Password_Update";
	}

	// 비밀번호 변경 실행
	@RequestMapping(value = "user/Password_Update_Proc")
	public String Password_Update_Proc(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./user/Password_Update_Proc start!");

		String password = CmmUtil.nvl(request.getParameter("password"));
		String passwordRepeat = CmmUtil.nvl(request.getParameter("password1"));
		String user_name = (String) session.getAttribute("SS_USER_NAME");

		log.info("password 변경 : " + password);
		log.info("passwordRepeat 변경 : " + passwordRepeat);

		UserInfoDTO uDTO = new UserInfoDTO();

		uDTO.setPassword(EncryptUtil.encHashSHA256(password));
		uDTO.setUser_name(user_name);

		if (!password.equals(passwordRepeat)) {
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다. 다시 한번 확인해주세요");
			model.addAttribute("url", "/user/Password_Update_Proc.do");
		} else if (password.length() < 8 || password.length() > 20) {
			model.addAttribute("msg", "비밀번호는 8~20자 영문 대 소문자,숫자,특수문자를 사용해 주세요.");
			model.addAttribute("url", "/user/Password_Update_Proc.do");
		} else {
			int result = 0;
			try {
				result = userInfoService.updateUserPassword(uDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				model.addAttribute("msg", "비밀번호가 변경되었습니다.");
				model.addAttribute("url", "/user/loginForm.do");

				log.info("result : " + result);
				log.info(this.getClass().getName() + "./user/Password_Update_Proc end!");
			} else {
				model.addAttribute("msg", "비밀번호가 변경되지 않았습니다.");
				model.addAttribute("url", "/user/userFind.do");

				log.info(this.getClass().getName() + "./user/Password_Update_Proc end!");
			}
		}

		return "/redirect";
	}

	// 아이디 찾기

	// 커뮤니티 운영방침 화면으로 이동
	@RequestMapping(value = "main/community_operation")
	public String community_operation() throws Exception {

		log.info(this.getClass().getName() + "./main/community_operation start!");

		return "/main/community_operation";
	}

	// 개인정보취급방침 화면으로 이동
	@RequestMapping(value = "main/policy")
	public String policy() throws Exception {

		log.info(this.getClass().getName() + "./main/policy start!");

		return "/main/policy";
	}

	// 이용자약관 화면으로 이동
	@RequestMapping(value = "main/terms")
	public String terms() throws Exception {

		log.info(this.getClass().getName() + "./main/terms start!");

		return "/main/terms";
	}

	// 아이디 중복검사
	@RequestMapping(value = "user/idCheck.do", method = RequestMethod.POST)
	public @ResponseBody String idCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + "./user/idCheck start!");

		String user_id = CmmUtil.nvl(request.getParameter("user_id"));
		int count = userInfoService.idCheck(user_id);

		log.info("user_id : " + user_id);
		log.info("count : " + count);

		if (count == 0) {
			return "0";
		} else {
			return "1";
		}
	}

	public ReviewDTO filterContent(ReviewDTO fDTO) {
		if (fDTO.getTitle() != null) {
			fDTO.setTitle(fDTO.getTitle().replaceAll("scr!pt", "script"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& lt;script& gt;", "&lt;script&gt;"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& lt;/script& gt;", "&lt;/script&gt;"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& #39;", "&#39"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& lt;", "&lt;"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& gt;", "&gt;"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& #40;", "("));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& #41;", ")"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("&nbsp;", " "));
			log.info(fDTO.getTitle());
		}

		return fDTO;
	}

}
