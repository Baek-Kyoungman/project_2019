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

import poly.dto.F_CommentDTO;
import poly.dto.FreeDTO;
import poly.dto.R_CommentDTO;
import poly.dto.ReviewDTO;
import poly.service.ICommentService;
import poly.service.IFreeService;
import poly.util.CmmUtil;

@Controller
public class FreeController {
	private Logger log = Logger.getLogger(this.getClass());

	// 비즈니스 로직
	@Resource(name = "FreeService")
	private IFreeService freeService;

	// 비즈니스 로직
	@Resource(name = "CommentService")
	private ICommentService commentService;

	// 자유 게시판 리스트 보여주기
	@RequestMapping(value = "main/FreeList")
	public String FreeList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".FreeList start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {

			log.info(this.getClass());

			FreeDTO fDTO = new FreeDTO();

			int pgNum = Integer.parseInt(request.getParameter("pgNum"));
			// 페이징
			int startNum = (pgNum - 1) * 5 + 1;
			int endNum = (pgNum - 1) * 5 + 5;
			List<FreeDTO> fList = new ArrayList<>();

			int total = Integer.parseInt(freeService.getFreeCnt());

			fList = freeService.getFreeList(startNum, endNum);

			if (fList == null) {
				fList = new ArrayList<FreeDTO>();
			} else {
				for (int i = 0; i < fList.size(); i++) {
					fList.get(i).setChg_dt(fList.get(i).getChg_dt().substring(0, 19));
					filterContent(fList.get(i));
					log.info(fList.get(i).getTitle());
					log.info(fList.get(i).getContents());
				}
			}

			model.addAttribute("pgNum", pgNum);
			model.addAttribute("total", total);

			model.addAttribute("fList", fList);

			// 로그 찍기(추후 찍은 로그를 통해 이 함수 호출이 끝났는지 파악하기 용이하다.)
			log.info(this.getClass().getName() + ".FreeList end!");

			// 함수 처리가 끝나고 보여줄 JSP 파일명(/WEB-INF/view/notice/NoticeList.jsp)
			return "/main/free/FreeList";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 자유게시판 등록 페이지
	@RequestMapping(value = "main/FreeReg")
	public String FreeReg(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + ".main/FreeReg ok!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {

			log.info(this.getClass().getName() + ".FreeList end!");
			return "/main/free/FreeReg";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 자유게시판 등록 실행
	@RequestMapping(value = "/FreeRegProc")
	public String FreeRegProc(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./FreeRegProc start!");

		String title = CmmUtil.nvl(request.getParameter("title"));
		String contents = CmmUtil.nvl(request.getParameter("contents"));
		String user_id = (String) session.getAttribute("SS_USER_ID");

		String sidoCode = CmmUtil.nvl(request.getParameter("sidoCode"));
		String sggCode = CmmUtil.nvl(request.getParameter("sggCode"));
		String type = CmmUtil.nvl(request.getParameter("type"));

		if (user_id != null) {
			FreeDTO fDTO = new FreeDTO();
			fDTO.setContents(contents);
			fDTO.setTitle(title);
			fDTO.setReg_id(user_id);
			fDTO.setSidoCode(sidoCode);
			fDTO.setSggCode(sggCode);
			fDTO.setType(type);

			int result = 0;

			log.info("!sidoCode : " + sidoCode);
			log.info("!sggCode : " + sggCode);
			log.info("!type : " + type);
			log.info("!UserID : " + user_id);
			log.info("!title : " + title);
			log.info("!contents : " + contents);

			try {
				result = freeService.insertFreeInfo(fDTO);
				log.info(this.getClass().getName() + ".test!");
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				model.addAttribute("msg", "게시물이 등록되었습니다.");
				model.addAttribute("url", "/main/FreeList.do?pgNum=1");
				session.setAttribute("SS_REG_ID", fDTO.getUser_id());
				log.info("SS_REG_ID : " + user_id);
			} else {
				model.addAttribute("msg", "게시물 등록에 실패했습니다.");
				model.addAttribute("url", "/main/FreeList.do?pgNum=1");
			}

			log.info(this.getClass().getName() + "./FreeRegProc end!");

			return "/redirect";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 자유게시판 상세보기
	@RequestMapping(value = "/FreeDetail")
	public String FreeDetail(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./FreeDetail start!");

		int free_seq = Integer.parseInt(request.getParameter("free_seq"));
		String board_seq = Integer.toString(free_seq);

		log.info("free_seq : " + free_seq);
		log.info("board_seq : " + board_seq);

		int cnt = 0;
		int Read_cnt = 0;

		try {
			cnt = Integer.parseInt(freeService.getFreeCnt());
			Read_cnt = freeService.updateRead_cnt(free_seq);
		} catch (Exception e) {
			e.printStackTrace();
		}

		FreeDTO fDTO = new FreeDTO();
		try {
			fDTO = freeService.getFreeDetail(free_seq);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 댓글 리스트
		List<F_CommentDTO> rList = new ArrayList<>();

		rList = commentService.f_commentList(board_seq);

		if (rList == null) {
			rList = new ArrayList<F_CommentDTO>();
		} else {
			for (int i = 0; i < rList.size(); i++) {
				filterComment(rList.get(i));
				log.info(rList.get(i).getContent());
			}
		}

		model.addAttribute("rList", rList);
		model.addAttribute("board_seq", board_seq);

		if (fDTO == null) {
			fDTO = new FreeDTO();

			model.addAttribute("msg", "게시글이 없습니다.");
			model.addAttribute("url", "/main/FreeList.do?pgNum=1");
			return "redirect";
		} else {
			filterContent(fDTO);

			model.addAttribute("fDTO", fDTO);

			log.info(this.getClass().getName() + "./FreeDetail end!");
			return "/main/free/FreeDetail";
		}
	}

	// 자유 게시물 삭제
	@RequestMapping(value = "/freeDelete")
	public String freeDelete(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./freeDelete start!");

		String free_seq = CmmUtil.nvl(request.getParameter("free_seq"));
		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {
			int result = 0;

			try {
				result = freeService.deleteFreeInfo(free_seq);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (result > 0) {
				model.addAttribute("url", "/main/FreeList.do?pgNum=1");
				model.addAttribute("msg", "삭제되었습니다.");

				log.info(this.getClass().getName() + "./freeDelete end!");
			} else {
				model.addAttribute("url", "/FreeDeatil.do?free_seq=" + free_seq);
				model.addAttribute("msg", "삭제에 실패했습니다.");

				log.info(this.getClass().getName() + "./freeDelete end!");
			}
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
		}
		return "/redirect";
	}

	// 자유게시판 수정 들어가는 페이지
	@RequestMapping(value = "/freeModify")
	public String FreeModify(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./free_modify start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {
			int free_seq = Integer.parseInt(request.getParameter("free_seq"));

			FreeDTO fDTO = new FreeDTO();

			try {
				fDTO = freeService.getFreeDetail(free_seq);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (fDTO == null) {
				fDTO = new FreeDTO();
			} else {
				filterContent(fDTO);
			}

			model.addAttribute("fDTO", fDTO);

			log.info(this.getClass().getName() + "./free_modify end!");
			return "/main/free/FreeModify";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 자유게시판 수정 실행
	@RequestMapping(value = "/freeModifyProc")
	public String FreeModifyProc(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./freeModifyProc start!");

		String title = CmmUtil.nvl(request.getParameter("title"));
		String contents = CmmUtil.nvl(request.getParameter("contents"));
		String free_seq = CmmUtil.nvl(request.getParameter("free_seq"));
		String sidoCode = CmmUtil.nvl(request.getParameter("sidoCode"));
		String sggCode = CmmUtil.nvl(request.getParameter("sggCode"));
		String type = CmmUtil.nvl(request.getParameter("type"));
		String user_id = (String) session.getAttribute("SS_USER_ID");

		FreeDTO fDTO = new FreeDTO();
		fDTO.setTitle(title);
		fDTO.setContents(contents);
		fDTO.setSidoCode(sidoCode);
		fDTO.setSggCode(sggCode);
		fDTO.setType(type);
		fDTO.setFree_seq(free_seq);

		if (user_id != null) {
			int result = 0;

			try {
				result = freeService.updateFree(fDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				model.addAttribute("msg", "수정되었습니다.");
				model.addAttribute("url", "/FreeDetail.do?free_seq=" + free_seq);

				log.info(this.getClass().getName() + "./freeModifyProc end!");
			} else {
				model.addAttribute("msg", "수정을 실패했습니다.");
				model.addAttribute("url", "/FreeDetail.do?free_seq=" + free_seq);

				log.info(this.getClass().getName() + "./freeModifyProc end!");
			}

			return "/redirect";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 마이페이지 자유게시판 목록
	@RequestMapping(value = "mypage/dashboard_free")
	public String Dashboard_free(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./mypage/dashboard_free start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {

			log.info(this.getClass());

			int pgNum = 1;
			if (request.getParameter("pgNum") != null && !request.getParameter("pgNum").equals("")) {
				pgNum = Integer.parseInt(request.getParameter("pgNum"));
			}

			// 페이징
			int startNum = (pgNum - 1) * 10 + 1;
			int endNum = (pgNum - 1) * 10 + 10;
			List<FreeDTO> nList = new ArrayList<>();

			int total = Integer.parseInt(freeService.getMypage_FreeCnt(user_id));

			nList = freeService.getMypage_freeList(user_id, startNum, endNum);

			if (nList == null) {
				nList = new ArrayList<FreeDTO>();
			} else {
				for (int i = 0; i < nList.size(); i++) {
					nList.get(i).setChg_dt(nList.get(i).getChg_dt().substring(2, 16));
					filterContent(nList.get(i));
					log.info(nList.get(i).getTitle());
					log.info(nList.get(i).getContents());
				}
			}
			model.addAttribute("pgNum", pgNum);
			model.addAttribute("total", total);

			model.addAttribute("nList", nList);

			log.info(this.getClass().getName() + "./mypage/dashboard_free end!");
			return "/mypage/dashboard_free/mypage_free";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 마이페이지 자유게시판 상세보기
	@RequestMapping(value = "mypage/dashboard_free_detail")
	public String Dashboard_free_detail(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./mypage/dashboard_free_detail start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {

			int free_seq = Integer.parseInt(request.getParameter("free_seq"));
			log.info("Detail 시작");
			int cnt = 0;
			try {
				cnt = Integer.parseInt(freeService.getFreeCnt());
			} catch (Exception e) {
				e.printStackTrace();
			}
			FreeDTO fDTO = new FreeDTO();
			try {
				fDTO = freeService.getFreeDetail(free_seq);

			} catch (Exception e) {
				e.printStackTrace();
			}
			if (fDTO == null) {
				model.addAttribute("msg", "게시글이 없습니다.");
				model.addAttribute("url", "/mypage/dashboard_free.do");
				return "redirect";
			} else {
				filterContent(fDTO);

				model.addAttribute("fDTO", fDTO);

				log.info(this.getClass().getName() + "./mypage/dashboard_free_detail end!");
				return "/mypage/dashboard_free/mypage_free_detail";
			}

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 마이페이지 자유 게시물 삭제
	@RequestMapping(value = "mypage/freeDelete")
	public String FreeDelete(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./mypage/freeDelete start!");

		String free_seq = CmmUtil.nvl(request.getParameter("free_seq"));
		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

		if (user_id != null) {
			if (userAuthor.equals("0")) {
				int result = 0;

				try {
					result = freeService.deleteFreeInfo(free_seq);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (result > 0) {
					model.addAttribute("url", "/mypage/dashboard_free.do");
					model.addAttribute("msg", "삭제되었습니다.");

					log.info(this.getClass().getName() + "./mypage/freeDelete end!");
				} else {
					model.addAttribute("url", "/mypage/dashboard_free.do");
					model.addAttribute("msg", "삭제에 실패했습니다.");

					log.info(this.getClass().getName() + "./mypage/freeDelete end!");
				}
			} else {
				model.addAttribute("msg", "관리자 권한이 필요합니다.");
				model.addAttribute("url", "/mypage/dashboard_review.do");
			}
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
		}
		return "/redirect";
	}

	// 마이페이지 자유게시물 등록 페이지
	@RequestMapping(value = "mypage/FreeReg")
	public String FreeReg(Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./mypage/FreeReg start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

		if (user_id != null) {
			if (userAuthor.equals("0")) {
				log.info(this.getClass().getName() + "./mypage/FreeReg end!");
				return "/mypage/dashboard_free/mypage_free_reg";
			} else {
				model.addAttribute("msg", "관리자 권한이 필요합니다.");
				model.addAttribute("url", "/mypage/dashboard_review.do");
				return "/redirect";
			}

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 마이페이지 자유게시물 등록 실행
	@RequestMapping(value = "mypage/Mypage_FreeRegProc")
	public String Mypage_FreeRegProc(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./mypage/Mypage_FreeRegProc start!");

		String title = CmmUtil.nvl(request.getParameter("title"));
		String contents = CmmUtil.nvl(request.getParameter("contents"));
		String user_id = (String) session.getAttribute("SS_USER_ID");

		String sidoCode = CmmUtil.nvl(request.getParameter("sidoCode"));
		String sggCode = CmmUtil.nvl(request.getParameter("sggCode"));
		String type = CmmUtil.nvl(request.getParameter("type"));

		if (user_id != null) {
			FreeDTO fDTO = new FreeDTO();
			fDTO.setContents(contents);
			fDTO.setTitle(title);
			fDTO.setReg_id(user_id);
			fDTO.setSidoCode(sidoCode);
			fDTO.setSggCode(sggCode);
			fDTO.setType(type);

			int result = 0;

			log.info("!sidoCode : " + sidoCode);
			log.info("!sggCode : " + sggCode);
			log.info("!type : " + type);
			log.info("!UserID : " + user_id);
			log.info("!title : " + title);
			log.info("!contents : " + contents);

			try {
				result = freeService.insertFreeInfo(fDTO);
				log.info(this.getClass().getName() + ".test!");
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				model.addAttribute("msg", "게시물이 등록되었습니다.");
				model.addAttribute("url", "/mypage/dashboard_free.do");
				session.setAttribute("SS_REG_ID", fDTO.getUser_id());
				log.info("SS_REG_ID : " + user_id);
			} else {
				model.addAttribute("msg", "게시물 등록에 실패했습니다.");
				model.addAttribute("url", "/mypage/FreeReg.do");
			}

			log.info(this.getClass().getName() + "./mypage/Mypage_FreeRegProc end!");

			return "/redirect";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 마이페이지 자유게시물 수정 들어가는 페이지
	@RequestMapping(value = "mypage/Mypage_free_modify")
	public String Mypage_free_modify(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./mypage.Mypage_free_modify start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {
			int free_seq = Integer.parseInt(request.getParameter("free_seq"));

			FreeDTO fDTO = new FreeDTO();

			try {
				fDTO = freeService.getMypage_freeDetail(free_seq);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (fDTO == null) {
				fDTO = new FreeDTO();
			} else {
				filterContent(fDTO);
			}

			model.addAttribute("fDTO", fDTO);

			log.info(this.getClass().getName() + "./mypage.Mypage_free_modify start!");
			return "/mypage/dashboard_free/mypage_free_modify";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 마이페이지 자유게시물 수정
	@RequestMapping(value = "mypage/Mypage_free_modifyProc")
	public String Mypage_free_modifyProc(HttpServletRequest request, HttpSession session, Model model)
			throws Exception {

		log.info(this.getClass().getName() + "./mypage/Mypage_free_modifyProc start!");

		String title = CmmUtil.nvl(request.getParameter("title"));
		String contents = CmmUtil.nvl(request.getParameter("contents"));
		String free_seq = CmmUtil.nvl(request.getParameter("free_seq"));
		String sidoCode = CmmUtil.nvl(request.getParameter("sidoCode"));
		String sggCode = CmmUtil.nvl(request.getParameter("sggCode"));
		String type = CmmUtil.nvl(request.getParameter("type"));
		String user_id = (String) session.getAttribute("SS_USER_ID");

		FreeDTO fDTO = new FreeDTO();
		fDTO.setTitle(title);
		fDTO.setContents(contents);
		fDTO.setFree_seq(free_seq);
		fDTO.setSidoCode(sidoCode);
		fDTO.setSggCode(sggCode);
		fDTO.setType(type);

		if (user_id != null) {
			int result = 0;

			try {
				result = freeService.updateFree(fDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				model.addAttribute("msg", "수정되었습니다.");
				model.addAttribute("url", "/mypage/dashboard_free_detail.do?free_seq=" + free_seq);

				log.info(this.getClass().getName() + "./mypage/Mypage_free_modifyProc end!");
			} else {
				model.addAttribute("msg", "수정을 실패했습니다.");
				model.addAttribute("url", "/mypage/Mypage_free_modify.do?free_seq=" + free_seq);

				log.info(this.getClass().getName() + "./mypage/Mypage_free_modifyProc end!");
			}

			return "/redirect";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 마이페이지 자유 게시물 삭제
	@RequestMapping(value = "mypage/freeDelete2")
	public String FreeDelete2(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./mypage/freeDelete2 start!");

		String free_seq[] = request.getParameterValues("free_seq");
		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

		if (user_id != null) {
			if (userAuthor.equals("0")) {
				int result = 0;

				try {
					for (int i = 0; i < free_seq.length; i++) {

						log.info(this.getClass().getName() + "free_seq" + free_seq[i]);

						result = freeService.deleteFreeInfo(free_seq[i]);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				if (result > 0) {
					model.addAttribute("url", "/mypage/dashboard_free.do");
					model.addAttribute("msg", "삭제되었습니다.");

					log.info(this.getClass().getName() + "./mypage/freeDelete2 end!");
				} else {
					model.addAttribute("url", "/mypage/dashboard_free_detail.do");
					model.addAttribute("msg", "삭제에 실패했습니다.");

					log.info(this.getClass().getName() + "./mypage/freeDelete2 end!");
				}
			} else {
				model.addAttribute("msg", "관리자 권한이 필요합니다.");
				model.addAttribute("url", "/mypage/dashboard_review.do");
			}
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
		}
		return "/redirect";
	}

	// ====================================================================================================================================

	// 관리페이지 자유게시판 목록
	@RequestMapping(value = "manager/board_free")
	public String Board_free(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/board_free start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

		if (user_id != null) {

			log.info(this.getClass());

			int pgNum = 1;
			if (request.getParameter("pgNum") != null && !request.getParameter("pgNum").equals("")) {
				pgNum = Integer.parseInt(request.getParameter("pgNum"));
			}

			// 페이징
			int startNum = (pgNum - 1) * 10 + 1;
			int endNum = (pgNum - 1) * 10 + 10;
			List<FreeDTO> nList = new ArrayList<>();

			int total = Integer.parseInt(freeService.getFreeCnt());

			nList = freeService.getManager_freeList(user_id, startNum, endNum);

			if (nList == null) {
				nList = new ArrayList<FreeDTO>();
			} else {
				for (int i = 0; i < nList.size(); i++) {
					nList.get(i).setChg_dt(nList.get(i).getChg_dt().substring(2, 16));
					filterContent(nList.get(i));
					log.info(nList.get(i).getTitle());
					log.info(nList.get(i).getContents());
				}
			}

			model.addAttribute("pgNum", pgNum);
			model.addAttribute("total", total);

			model.addAttribute("nList", nList);

			log.info(this.getClass().getName() + "./manager/board_free end!");
			return "/manager/board_free/manager_free";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 관리페이지 자유게시판 상세보기
	@RequestMapping(value = "manager/board_free_detail")
	public String Board_free_detail(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/board_free_detail start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {

			int free_seq = Integer.parseInt(request.getParameter("free_seq"));
			log.info("Detail 시작");
			int cnt = 0;
			try {
				cnt = Integer.parseInt(freeService.getFreeCnt());
			} catch (Exception e) {
				e.printStackTrace();
			}
			FreeDTO fDTO = new FreeDTO();
			try {
				fDTO = freeService.getFreeDetail(free_seq);

			} catch (Exception e) {
				e.printStackTrace();
			}
			if (fDTO == null) {
				model.addAttribute("msg", "게시글이 없습니다.");
				model.addAttribute("url", "/manager/board_free.do");

				log.info(this.getClass().getName() + "./manager/board_free_detail end!");
				return "redirect";
			} else {
				filterContent(fDTO);

				model.addAttribute("fDTO", fDTO);

				log.info(this.getClass().getName() + "./manager/board_free_detail end!");
				return "/manager/board_free/manager_free_detail";
			}

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 관리페이지 자유 게시물 삭제
	@RequestMapping(value = "manager/manager_freeDelete")
	public String Manager_FreeDelete(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/manager_freeDelete start!");

		String free_seq = CmmUtil.nvl(request.getParameter("free_seq"));
		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

		if (user_id != null) {
			if (userAuthor.equals("1")) {
				int result = 0;

				try {
					result = freeService.deleteFreeInfo(free_seq);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (result > 0) {
					model.addAttribute("url", "/manager/board_free.do");
					model.addAttribute("msg", "삭제되었습니다.");

					log.info(this.getClass().getName() + "./manager/manager_freeDelete end!");
				} else {
					model.addAttribute("url", "/manager/board_free.do");
					model.addAttribute("msg", "삭제에 실패했습니다.");

					log.info(this.getClass().getName() + "./manager/manager_freeDelete end!");
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

	// 관리페이지 자유게시물 등록 페이지
	@RequestMapping(value = "manager/manager_FreeReg")
	public String Manager_FreeReg(Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/manager_FreeReg start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

		if (user_id != null) {
			if (userAuthor.equals("1")) {

				log.info(this.getClass().getName() + "./manager/manager_FreeReg end!");
				return "/manager/board_free/manager_free_reg";
			} else {
				model.addAttribute("msg", "관리자 권한이 필요합니다.");
				model.addAttribute("url", "/manager/board_review.do");
				return "/redirect";
			}

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 관리페이지 자유게시물 등록 실행
	@RequestMapping(value = "manager/Manager_FreeRegProc")
	public String Manager_FreeRegProc(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/Manager_FreeRegProc start!");

		String title = CmmUtil.nvl(request.getParameter("title"));
		String contents = CmmUtil.nvl(request.getParameter("contents"));
		String user_id = (String) session.getAttribute("SS_USER_ID");

		String sidoCode = CmmUtil.nvl(request.getParameter("sidoCode"));
		String sggCode = CmmUtil.nvl(request.getParameter("sggCode"));
		String type = CmmUtil.nvl(request.getParameter("type"));

		if (user_id != null) {
			FreeDTO fDTO = new FreeDTO();
			fDTO.setContents(contents);
			fDTO.setTitle(title);
			fDTO.setReg_id(user_id);
			fDTO.setSidoCode(sidoCode);
			fDTO.setSggCode(sggCode);
			fDTO.setType(type);

			int result = 0;

			log.info("!sidoCode : " + sidoCode);
			log.info("!sggCode : " + sggCode);
			log.info("!type : " + type);
			log.info("!UserID : " + user_id);
			log.info("!title : " + title);
			log.info("!contents : " + contents);

			try {
				result = freeService.insertFreeInfo(fDTO);
				log.info(this.getClass().getName() + ".test!");
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				model.addAttribute("msg", "게시물이 등록되었습니다.");
				model.addAttribute("url", "/manager/board_free.do");
				session.setAttribute("SS_REG_ID", fDTO.getUser_id());
				log.info("SS_REG_ID : " + user_id);

				log.info(this.getClass().getName() + "./manager/Manager_FreeRegProc end!");
			} else {
				model.addAttribute("msg", "게시물 등록에 실패했습니다.");
				model.addAttribute("url", "/manager/manager_FreeReg.do");

				log.info(this.getClass().getName() + "./manager/Manager_FreeRegProc end!");
			}

			log.info(this.getClass().getName() + ".main/FreeRegProc ok!");

			return "/redirect";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 관리페이지 자유게시물 수정 들어가는 페이지
	@RequestMapping(value = "manager/Manager_free_modify")
	public String Manager_free_modify(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./manager/Manager_free_modify start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {
			int free_seq = Integer.parseInt(request.getParameter("free_seq"));

			FreeDTO fDTO = new FreeDTO();

			try {
				fDTO = freeService.getMypage_freeDetail(free_seq);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (fDTO == null) {
				fDTO = new FreeDTO();
			} else {
				filterContent(fDTO);
			}

			model.addAttribute("fDTO", fDTO);

			log.info(this.getClass().getName() + "./manager/Manager_free_modify end!");
			return "/manager/board_free/manager_free_modify";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 관리페이지 자유게시물 수정
	@RequestMapping(value = "manager/Manager_free_modifyProc")
	public String Manager_free_modifyProc(HttpServletRequest request, HttpSession session, Model model)
			throws Exception {

		log.info(this.getClass().getName() + "./manager/Manager_free_modifyProc start!");

		String title = CmmUtil.nvl(request.getParameter("title"));
		String contents = CmmUtil.nvl(request.getParameter("contents"));
		String free_seq = CmmUtil.nvl(request.getParameter("free_seq"));
		String sidoCode = CmmUtil.nvl(request.getParameter("sidoCode"));
		String sggCode = CmmUtil.nvl(request.getParameter("sggCode"));
		String type = CmmUtil.nvl(request.getParameter("type"));
		String user_id = (String) session.getAttribute("SS_USER_ID");

		FreeDTO fDTO = new FreeDTO();
		fDTO.setTitle(title);
		fDTO.setContents(contents);
		fDTO.setFree_seq(free_seq);
		fDTO.setSidoCode(sidoCode);
		fDTO.setSggCode(sggCode);
		fDTO.setType(type);

		if (user_id != null) {
			int result = 0;

			try {
				result = freeService.updateFree(fDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				model.addAttribute("msg", "수정되었습니다.");
				model.addAttribute("url", "/manager/board_free_detail.do?free_seq=" + free_seq);

				log.info(this.getClass().getName() + "./manager/Manager_free_modifyProc end!");
			} else {
				model.addAttribute("msg", "수정을 실패했습니다.");
				model.addAttribute("url", "/manager/Manager_free_modify.do?free_seq=" + free_seq);

				log.info(this.getClass().getName() + "./manager/Manager_free_modifyProc end!");
			}

			return "/redirect";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 관리페이지 자유 게시물 삭제
	@RequestMapping(value = "manager/manager_freeDelete2")
	public String Manager_FreeDelete2(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/manager_freeDelete2 start!");

		String free_seq[] = request.getParameterValues("free_seq");
		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

		if (user_id != null) {
			if (userAuthor.equals("1")) {
				int result = 0;

				try {
					for (int i = 0; i < free_seq.length; i++) {

						log.info(this.getClass().getName() + "free_seq" + free_seq[i]);

						result = freeService.deleteFreeInfo(free_seq[i]);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				if (result > 0) {
					model.addAttribute("url", "/manager/board_free.do");
					model.addAttribute("msg", "삭제되었습니다.");

					log.info(this.getClass().getName() + "./manager/manager_freeDelete2 end!");
				} else {
					model.addAttribute("url", "/manager/board_free_detail.do");
					model.addAttribute("msg", "삭제에 실패했습니다.");

					log.info(this.getClass().getName() + "./manager/manager_freeDelete2 end!");
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

	public FreeDTO filterContent(FreeDTO fDTO) {
		if (fDTO.getTitle() != null) {
			fDTO.setTitle(fDTO.getTitle().replaceAll("scr!pt", "script"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& #39;", "&#39"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& lt;", "&lt;"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& gt;", "&gt;"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& #40;", "("));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& #41;", ")"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("&nbsp;", " "));
			log.info(fDTO.getTitle());
		}

		if (fDTO.getContents() != null) {
			fDTO.setContents(fDTO.getContents().replaceAll("scr!pt", "script"));
			fDTO.setContents(fDTO.getContents().replaceAll("& lt;script& gt;", "&lt;script&gt;"));
			fDTO.setContents(fDTO.getContents().replaceAll("& lt;/script& gt;", "&lt;/script&gt;"));
			fDTO.setContents(fDTO.getContents().replaceAll("& #39;", "&#39;"));
			fDTO.setContents(fDTO.getContents().replaceAll("& lt;", "<"));
			fDTO.setContents(fDTO.getContents().replaceAll("& gt;", ">"));
			fDTO.setContents(fDTO.getContents().replaceAll("& #40;", "("));
			fDTO.setContents(fDTO.getContents().replaceAll("& #41;", ")"));
			fDTO.setContents(fDTO.getContents().replaceAll("&nbsp;", " "));
			log.info(fDTO.getContents());
		}

		if (fDTO.getType() != null) {
			fDTO.setType(fDTO.getType().replaceAll("& #40;", "("));
			fDTO.setType(fDTO.getType().replaceAll("& #41;", ")"));
			fDTO.setType(fDTO.getType().replaceAll("&nbsp;", " "));
			log.info(fDTO.getType());
		}

		return fDTO;
	}

	public F_CommentDTO filterComment(F_CommentDTO fDTO) {
		if (fDTO.getContent() != null) {
			fDTO.setContent(fDTO.getContent().replaceAll("scr!pt", "script"));
			fDTO.setContent(fDTO.getContent().replaceAll("& lt;script& gt;", "&lt;script&gt;"));
			fDTO.setContent(fDTO.getContent().replaceAll("& lt;/script& gt;", "&lt;/script&gt;"));
			fDTO.setContent(fDTO.getContent().replaceAll("& #39;", "&#39;"));
			fDTO.setContent(fDTO.getContent().replaceAll("& lt;", "&lt;"));
			fDTO.setContent(fDTO.getContent().replaceAll("& gt;", "&gt;"));
			fDTO.setContent(fDTO.getContent().replaceAll("& #40;", "("));
			fDTO.setContent(fDTO.getContent().replaceAll("& #41;", ")"));
			fDTO.setContent(fDTO.getContent().replaceAll("&nbsp;", " "));
			log.info(fDTO.getContent());
		}

		return fDTO;
	}
}
