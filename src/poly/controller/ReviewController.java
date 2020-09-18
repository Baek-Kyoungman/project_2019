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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import poly.dto.F_CommentDTO;
import poly.dto.FreeDTO;
import poly.dto.R_CommentDTO;
import poly.dto.ReviewDTO;
import poly.service.ICommentService;
import poly.service.IReviewService;
import poly.util.CmmUtil;

@Controller
public class ReviewController {
	private Logger log = Logger.getLogger(this.getClass());

	// 비즈니스 로직
	@Resource
	private IReviewService reviewService;

	// 비즈니스 로직
	@Resource(name = "CommentService")
	private ICommentService commentService;

	// 리뷰 게시판 리스트 보여주기
	@RequestMapping(value = "main/ReviewList")
	public String ReviewList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + "./main/ReviewList start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {

			log.info(this.getClass());

			ReviewDTO rDTO = new ReviewDTO();

			int pgNum = Integer.parseInt(request.getParameter("pgNum"));
			// 페이징
			int startNum = (pgNum - 1) * 5 + 1;
			int endNum = (pgNum - 1) * 5 + 5;
			List<ReviewDTO> fList = new ArrayList<>();

			int total = Integer.parseInt(reviewService.getReviewCnt());

			fList = reviewService.getReviewList(startNum, endNum);

			if (fList == null) {
				fList = new ArrayList<ReviewDTO>();
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
			log.info(this.getClass().getName() + "./main/ReviewList end!");

			// 함수 처리가 끝나고 보여줄 JSP 파일명(/WEB-INF/view/notice/NoticeList.jsp)
			return "/main/review/ReviewList";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 리뷰게시판 등록 페이지
	@RequestMapping(value = "main/ReviewReg")
	public String ReviewReg(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + ".main/ReviewReg start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {

			log.info(this.getClass().getName() + ".main/ReviewReg end!");
			return "/main/review/ReviewReg";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 리뷰게시판 등록 실행
	@RequestMapping(value = "/ReviewRegProc")
	public String ReviewRegProc(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./ReviewRegProc start!");

		String title = CmmUtil.nvl(request.getParameter("title"));
		String contents = CmmUtil.nvl(request.getParameter("contents"));
		String user_id = (String) session.getAttribute("SS_USER_ID");

		String sidoCode = CmmUtil.nvl(request.getParameter("sidoCode"));
		String sggCode = CmmUtil.nvl(request.getParameter("sggCode"));
		String type = CmmUtil.nvl(request.getParameter("type"));

		if (user_id != null) {
			ReviewDTO fDTO = new ReviewDTO();
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
				result = reviewService.insertReviewInfo(fDTO);
				log.info(this.getClass().getName() + ".test!");
			} catch (Exception e) {
				e.printStackTrace();
			} 	

			if (result > 0) {
				model.addAttribute("msg", "게시물이 등록되었습니다.");
				model.addAttribute("url", "/main/ReviewList.do?pgNum=1");
				session.setAttribute("SS_REG_ID", fDTO.getUser_id());

				log.info("SS_REG_ID : " + user_id);
				log.info(this.getClass().getName() + "./ReviewRegProc end!");
			} else {
				model.addAttribute("msg", "게시물 등록에 실패했습니다.");
				model.addAttribute("url", "/main/ReviewList.do?pgNum=1");
			}

			log.info(this.getClass().getName() + "./main/ReviewRegProc end!");

			return "/redirect";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 리뷰게시판 상세보기
	@RequestMapping(value = "/ReviewDetail")
	public String ReviewDetail(HttpServletRequest request, HttpServletResponse response, Model model,
			HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./ReviewDetail start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {
			int review_seq = Integer.parseInt(request.getParameter("review_seq"));
			log.info("리뷰게시판 Detail 시작");

			String board_seq = Integer.toString(review_seq);
			log.info("board_seq : " + board_seq);

			int cnt = 0;
			int Read_cnt = 0;

			try {
				cnt = Integer.parseInt(reviewService.getReviewCnt());
				Read_cnt = reviewService.updateRead_cnt(review_seq);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ReviewDTO fDTO = new ReviewDTO();
			try {
				fDTO = reviewService.getReviewDetail(review_seq);

			} catch (Exception e) {
				e.printStackTrace();
			}

			// 댓글 리스트
			List<R_CommentDTO> rList = new ArrayList<>();

			rList = commentService.r_commentList(board_seq);

			if (rList == null) {
				rList = new ArrayList<R_CommentDTO>();
			} else {
				for (int i = 0; i < rList.size(); i++) {
					filterComment(rList.get(i));
					log.info(rList.get(i).getContent());
				}
			}
			
			model.addAttribute("rList", rList);
			model.addAttribute("board_seq", board_seq);

			if (fDTO == null) {
				fDTO = new ReviewDTO();
				
				model.addAttribute("msg", "게시글이 없습니다.");
				model.addAttribute("url", "/ReviewList.do?pgNum=1");

				log.info(this.getClass().getName() + "./ReviewDetail end!");
				return "redirect";
			} else {
				filterContent(fDTO);
				
				model.addAttribute("fDTO", fDTO);

				log.info(this.getClass().getName() + "./ReviewDetail end!");
				return "/main/review/ReviewDetail";
			}

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 리뷰 게시물 삭제
	@RequestMapping(value = "/ReviewDelete")
	public String reviewDelete(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./ReviewDelete start!");

		String review_seq = CmmUtil.nvl(request.getParameter("review_seq"));
		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {
			int result = 0;

			try {
				result = reviewService.deleteReviewInfo(review_seq);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (result > 0) {
				model.addAttribute("url", "/main/ReviewList.do?pgNum=1");
				model.addAttribute("msg", "삭제되었습니다.");

				log.info(this.getClass().getName() + "./ReviewDelete end!");
			} else {
				model.addAttribute("url", "/ReviewDeatil.do?review_seq=" + review_seq);
				model.addAttribute("msg", "삭제에 실패했습니다.");

				log.info(this.getClass().getName() + "./reviewDelete end!");
			}
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
		}
		return "/redirect";
	}

	// 리뷰게시판 수정 들어가는 페이지
	@RequestMapping(value = "/reviewModify")
	public String reivewModify(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./reviewModify start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {
			int review_seq = Integer.parseInt(request.getParameter("review_seq"));

			ReviewDTO fDTO = new ReviewDTO();

			try {
				fDTO = reviewService.getReviewDetail(review_seq);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if(fDTO == null) {
				fDTO = new ReviewDTO();
			}else {
				filterContent(fDTO);
			}
			
			model.addAttribute("fDTO", fDTO);

			log.info(this.getClass().getName() + "./reviewModify end!");
			return "/main/review/ReviewModify";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 자유게시판 수정 실행
	@RequestMapping(value = "/reviewModifyProc")
	public String reviewModifyProc(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./reviewModifyProc start!");

		String title = CmmUtil.nvl(request.getParameter("title"));
		String contents = CmmUtil.nvl(request.getParameter("contents"));
		String review_seq = CmmUtil.nvl(request.getParameter("review_seq"));
		String sidoCode = CmmUtil.nvl(request.getParameter("sidoCode"));
		String sggCode = CmmUtil.nvl(request.getParameter("sggCode"));
		String type = CmmUtil.nvl(request.getParameter("type"));
		String user_id = (String) session.getAttribute("SS_USER_ID");

		ReviewDTO fDTO = new ReviewDTO();
		fDTO.setTitle(title);
		fDTO.setContents(contents);
		fDTO.setSidoCode(sidoCode);
		fDTO.setSggCode(sggCode);
		fDTO.setType(type);
		fDTO.setReview_seq(review_seq);

		if (user_id != null) {
			int result = 0;

			try {
				result = reviewService.updateReview(fDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				model.addAttribute("msg", "수정되었습니다.");
				model.addAttribute("url", "/ReviewDetail.do?review_seq=" + review_seq);

				log.info(this.getClass().getName() + "./reviewModifyProc end!");
			} else {
				model.addAttribute("msg", "수정을 실패했습니다.");
				model.addAttribute("url", "/ReviewDetail.do?review_seq=" + review_seq);

				log.info(this.getClass().getName() + "./reviewModifyProc end!");
			}

			return "/redirect";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 마이페이지 리뷰게시판 목록
	@RequestMapping(value = "mypage/dashboard_review")
	public String Dashboard_review(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./mypage/dashboard_review start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {

			log.info(this.getClass());

			ReviewDTO rDTO = new ReviewDTO();
			int pgNum = 1;
			if(request.getParameter("pgNum")!=null&&!request.getParameter("pgNum").equals("")) {
				pgNum = Integer.parseInt(request.getParameter("pgNum"));
			}
			
			// 페이징
			int startNum = (pgNum - 1) * 10 + 1;
			int endNum = (pgNum - 1) * 10 + 10;
			List<ReviewDTO> nList = new ArrayList<>();

			int total = Integer.parseInt(reviewService.getMypage_ReviewCnt(user_id));

			nList = reviewService.getMypage_reviewList(user_id, startNum, endNum);
			
			if (nList == null) {
				nList = new ArrayList<ReviewDTO>();
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

			log.info(this.getClass().getName() + "./mypage/dashboard_review end!");
			return "/mypage/dashboard_review/mypage_review";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}
	// 마이페이지 리뷰게시판 상세보기
	@RequestMapping(value = "mypage/dashboard_review_detail")
	public String Dashboard_review_detail(HttpServletRequest request, Model model, HttpSession session)
			throws Exception {

		log.info(this.getClass().getName() + "./mypage/dashboard_review_detail start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");
		
		if (user_id != null) {
			
			int review_seq = Integer.parseInt(request.getParameter("review_seq"));
			log.info("Detail 시작");
			int cnt = 0;
			try {
				cnt = Integer.parseInt(reviewService.getReviewCnt());
			} catch (Exception e) {
				e.printStackTrace();
			}
			ReviewDTO fDTO = new ReviewDTO();
			try {
				fDTO = reviewService.getReviewDetail(review_seq);

			} catch (Exception e) {
				e.printStackTrace();
			}
			if (fDTO == null) {
				model.addAttribute("msg", "게시글이 없습니다.");
				model.addAttribute("url", "/mypage/dashboard_review.do");

				log.info(this.getClass().getName() + "./mypage/dashboard_review_detail end!");
				return "redirect";
			} else {
				filterContent(fDTO);
				
				model.addAttribute("fDTO", fDTO);
				return "/mypage/dashboard_review/mypage_review_detail";
			}

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 마이페이지 리뷰 게시물 삭제
	@RequestMapping(value = "mypage/reviewDelete")
	public String ReviewDelete(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./mypage/reviewDelete start!");

		String review_seq = CmmUtil.nvl(request.getParameter("review_seq"));
		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

		if (user_id != null) {
			if (userAuthor.equals("0")) {
				int result = 0;

				try {
					result = reviewService.deleteReviewInfo(review_seq);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (result > 0) {
					model.addAttribute("url", "/mypage/dashboard_review.do");
					model.addAttribute("msg", "삭제되었습니다.");

					log.info(this.getClass().getName() + "./mypage/reviewDelete end!");
				} else {
					model.addAttribute("url", "/mypage/dashboard_review_detail.do");
					model.addAttribute("msg", "삭제에 실패했습니다.");

					log.info(this.getClass().getName() + "./mypage/reviewDelete end!");
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

	// 마이페이지 리뷰게시물 등록 페이지
	@RequestMapping(value = "mypage/ReviewReg")
	public String ReviewReg(Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./mypage/ReviewReg start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

		if (user_id != null) {
			if (userAuthor.equals("0")) {

				log.info(this.getClass().getName() + "./mypage/ReviewReg end!");
				return "/mypage/dashboard_review/mypage_review_reg";
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

	// 마이페이지 리뷰게시물 등록 실행
	@RequestMapping(value = "mypage/Mypage_ReviewRegProc")
	public String Mypage_ReviewRegProc(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./mypage/Mypage_ReviewRegProc start!");

		String title = CmmUtil.nvl(request.getParameter("title"));
		String contents = CmmUtil.nvl(request.getParameter("contents"));
		String user_id = (String) session.getAttribute("SS_USER_ID");

		String sidoCode = CmmUtil.nvl(request.getParameter("sidoCode"));
		String sggCode = CmmUtil.nvl(request.getParameter("sggCode"));
		String type = CmmUtil.nvl(request.getParameter("type"));

		if (user_id != null) {
			ReviewDTO fDTO = new ReviewDTO();
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
				result = reviewService.insertReviewInfo(fDTO);
				log.info(this.getClass().getName() + ".test!");
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				model.addAttribute("msg", "게시물이 등록되었습니다.");
				model.addAttribute("url", "/mypage/dashboard_review.do");
				session.setAttribute("SS_REG_ID", fDTO.getUser_id());

				log.info("SS_REG_ID : " + user_id);
				log.info(this.getClass().getName() + "./mypage/Mypage_ReviewRegProc end!");
			} else {
				model.addAttribute("msg", "게시물 등록에 실패했습니다.");
				model.addAttribute("url", "/mypage/ReviewReg.do");
			}

			log.info(this.getClass().getName() + ".main/ReviewRegProc end!");

			return "/redirect";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 마이페이지 자유게시물 수정 들어가는 페이지
	@RequestMapping(value = "mypage/Mypage_review_modify")
	public String Mypage_review_modify(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./mypage/Mypage_review_modify start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {
			int review_seq = Integer.parseInt(request.getParameter("review_seq"));

			ReviewDTO fDTO = new ReviewDTO();

			try {
				fDTO = reviewService.getMypage_reviewDetail(review_seq);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if(fDTO == null) {
				fDTO = new ReviewDTO();
			}else {
				filterContent(fDTO);
			}
			
			model.addAttribute("fDTO", fDTO);

			log.info(this.getClass().getName() + "./mypage/Mypage_review_modify end!");
			return "/mypage/dashboard_review/mypage_review_modify";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 마이페이지 자유게시물 수정
	@RequestMapping(value = "mypage/Mypage_review_modifyProc")
	public String Mypage_review_modifyProc(HttpServletRequest request, HttpSession session, Model model)
			throws Exception {

		log.info(this.getClass().getName() + "./mypage/Mypage_reivew_modifyProc start!");

		String title = CmmUtil.nvl(request.getParameter("title"));
		String contents = CmmUtil.nvl(request.getParameter("contents"));
		String review_seq = CmmUtil.nvl(request.getParameter("review_seq"));
		String sidoCode = CmmUtil.nvl(request.getParameter("sidoCode"));
		String sggCode = CmmUtil.nvl(request.getParameter("sggCode"));
		String type = CmmUtil.nvl(request.getParameter("type"));
		String user_id = (String) session.getAttribute("SS_USER_ID");
		
		ReviewDTO fDTO = new ReviewDTO();
		fDTO.setTitle(title);
		fDTO.setContents(contents);
		fDTO.setReview_seq(review_seq);
		fDTO.setSidoCode(sidoCode);
		fDTO.setSggCode(sggCode);
		fDTO.setType(type);

		if (user_id != null) {
			int result = 0;

			try {
				result = reviewService.updateReview(fDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				model.addAttribute("msg", "수정되었습니다.");
				model.addAttribute("url", "/mypage/dashboard_review_detail.do?review_seq=" + review_seq);

				log.info(this.getClass().getName() + "./mypage/Mypage_reivew_modifyProc end!");
			} else {
				model.addAttribute("msg", "수정을 실패했습니다.");
				model.addAttribute("url", "/mypage/Mypage_review_modify.do");

				log.info(this.getClass().getName() + "./mypage/Mypage_reivew_modifyProc end!");
			}

			return "/redirect";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 마이페이지 자유 게시물 삭제
	@RequestMapping(value = "mypage/reviewDelete2")
	public String ReviewDelete2(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./mypage/reviewDelete2 start!");

		String review_seq[] = request.getParameterValues("review_seq");
		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

		if (user_id != null) {
			if (userAuthor.equals("0")) {
				int result = 0;

				try {
					for (int i = 0; i < review_seq.length; i++) {

						log.info(this.getClass().getName() + "review_seq" + review_seq[i]);

						result = reviewService.deleteReviewInfo(review_seq[i]);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				if (result > 0) {
					model.addAttribute("url", "/mypage/dashboard_review.do");
					model.addAttribute("msg", "삭제되었습니다.");

					log.info(this.getClass().getName() + "./mypage/reviewDelete2 end!");
				} else {
					model.addAttribute("url", "/mypage/dashboard_review.do");
					model.addAttribute("msg", "삭제에 실패했습니다.");

					log.info(this.getClass().getName() + "./mypage/reviewDelete2 end!");
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

	// 관리자 리뷰게시물 화면으로 이동
	@RequestMapping(value = "manager/board_review")
	public String Board_review(HttpServletRequest request, HttpServletResponse response, Model model,
			HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/board_review start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {

			log.info(this.getClass());

			int pgNum = 1;
			if(request.getParameter("pgNum")!=null&&!request.getParameter("pgNum").equals("")) {
				pgNum = Integer.parseInt(request.getParameter("pgNum"));
			}

			// 페이징
			int startNum = (pgNum - 1) * 10 + 1;
			int endNum = (pgNum - 1) * 10 + 10;
			List<ReviewDTO> nList = new ArrayList<>();

			int total = Integer.parseInt(reviewService.getReviewCnt());

			nList = reviewService.getManager_reviewList(user_id, startNum, endNum);
			
			if (nList == null) {
				nList = new ArrayList<ReviewDTO>();
			} else {
				for (int i = 0; i < nList.size(); i++) {
					nList.get(i).setChg_dt(nList.get(i).getChg_dt().substring(2,16));
					filterContent(nList.get(i));
					log.info(nList.get(i).getTitle());
					log.info(nList.get(i).getContents());
				}
			}
			
			model.addAttribute("pgNum", pgNum);
			model.addAttribute("total", total);
			model.addAttribute("nList", nList);

			log.info(this.getClass().getName() + "./manager/board_review end!");
			return "/manager/board_review/manager_review";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 관리페이지 리뷰게시판 상세보기
	@RequestMapping(value = "manager/board_review_detail")
	public String Board_review_detail(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/board_review_detail start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {

			int review_seq = Integer.parseInt(request.getParameter("review_seq"));
			log.info("Detail 시작");
			int cnt = 0;
			try {
				cnt = Integer.parseInt(reviewService.getReviewCnt());
			} catch (Exception e) {
				e.printStackTrace();
			}
			ReviewDTO fDTO = new ReviewDTO();
			try {
				fDTO = reviewService.getReviewDetail(review_seq);

			} catch (Exception e) {
				e.printStackTrace();
			}
			if (fDTO == null) {
				model.addAttribute("msg", "게시글이 없습니다.");
				model.addAttribute("url", "/manager/board_review.do");

				log.info(this.getClass().getName() + "./manager/board_review_detail end!");
				return "redirect";
			} else {
				filterContent(fDTO);
				
				model.addAttribute("fDTO", fDTO);

				log.info(this.getClass().getName() + "./manager/board_review_detail end!");
				return "/manager/board_review/manager_review_detail";
			}

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 관리페이지 리뷰게시물 등록 페이지
	@RequestMapping(value = "manager/manager_ReviewReg")
	public String Manager_ReviewReg(Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/manager_ReviewReg start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");

		if (user_id != null) {
			if (userAuthor.equals("1")) {

				log.info(this.getClass().getName() + "./manager/manager_ReviewReg end!");
				return "/manager/board_review/manager_review_reg";
			} else {
				model.addAttribute("msg", "관리자 권한이 필요합니다.");
				model.addAttribute("url", "/manager/manager_user.do");
				return "/redirect";
			}

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 관리페이지 리뷰게시물 등록 실행
	@RequestMapping(value = "manager/Manager_ReviewRegProc")
	public String Manager_ReviewRegProc(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/Manager_ReviewRegProc start!");

		String title = CmmUtil.nvl(request.getParameter("title"));
		String contents = CmmUtil.nvl(request.getParameter("contents"));
		String user_id = (String) session.getAttribute("SS_USER_ID");

		String sidoCode = CmmUtil.nvl(request.getParameter("sidoCode"));
		String sggCode = CmmUtil.nvl(request.getParameter("sggCode"));
		String type = CmmUtil.nvl(request.getParameter("type"));

		if (user_id != null) {
			ReviewDTO fDTO = new ReviewDTO();
			fDTO.setContents(contents);
			fDTO.setTitle(title);
			fDTO.setReg_id(user_id);
			fDTO.setSidoCode(sidoCode);
			fDTO.setSggCode(sggCode);
			fDTO.setType(type);

			int result = 0;

			log.info("manager_sidoCode : " + sidoCode);
			log.info("manager_sggCode : " + sggCode);
			log.info("manager_type : " + type);
			log.info("manager_UserID : " + user_id);
			log.info("manager_title : " + title);
			log.info("manager_contents : " + contents);

			try {
				result = reviewService.insertReviewInfo(fDTO);
				log.info(this.getClass().getName() + ".test!");
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				model.addAttribute("msg", "게시물이 등록되었습니다.");
				model.addAttribute("url", "/manager/board_review.do");
				session.setAttribute("SS_REG_ID", fDTO.getUser_id());

				log.info("SS_REG_ID : " + user_id);
				log.info(this.getClass().getName() + "./manager/Manager_ReviewRegProc end!");
			} else {
				model.addAttribute("msg", "게시물 등록에 실패했습니다.");
				model.addAttribute("url", "/manager/manager_ReviewReg.do");
			}

			log.info(this.getClass().getName() + ".main/ReviewRegProc end!");

			return "/redirect";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 관리페이지 리뷰 게시물 삭제
	@RequestMapping(value = "manager/manager_reviewDelete")
	public String Manager_ReviewDelete(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "./manager/manager_reviewDelete start!");

		String review_seq = CmmUtil.nvl(request.getParameter("review_seq"));
		String user_id = (String) session.getAttribute("SS_USER_ID");
		String userAuthor = (String) session.getAttribute("SS_USERAUTHOR");
		String chk = CmmUtil.nvl(request.getParameter("chk"));

		if (user_id != null) {
			if (userAuthor.equals("1")) {
				int result = 0;

				try {
					result = reviewService.deleteReviewInfo(review_seq);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (result > 0) {
					model.addAttribute("url", "/manager/board_review.do");
					model.addAttribute("msg", "삭제되었습니다.");

					log.info(this.getClass().getName() + "./manager/manager_reviewDelete end!");
				} else {
					model.addAttribute("url", "/manager/board_review.do");
					model.addAttribute("msg", "삭제에 실패했습니다.");

					log.info(this.getClass().getName() + "./manager/manager_reviewDelete end!");
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

	// 관리페이지 자유게시물 수정 들어가는 페이지
	@RequestMapping(value = "manager/Manager_review_modify")
	public String Manager_review_modify(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./manager/Manager_reiview_modify start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {
			int review_seq = Integer.parseInt(request.getParameter("review_seq"));

			ReviewDTO fDTO = new ReviewDTO();

			try {
				fDTO = reviewService.getMypage_reviewDetail(review_seq);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if(fDTO == null) {
				fDTO = new ReviewDTO();
			}else {
				filterContent(fDTO);
			}
			
			model.addAttribute("fDTO", fDTO);

			log.info(this.getClass().getName() + "./manager/Manager_reiview_modify end!");
			return "/manager/board_review/manager_review_modify";

		} else {
			
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	// 관리페이지 자유게시물 수정
	@RequestMapping(value = "manager/Manager_review_modifyProc")
	public String Manager_review_modifyProc(HttpServletRequest request, HttpSession session, Model model)
			throws Exception {

		log.info(this.getClass().getName() + "./manager/Manager_review_modifyProc start!");

		String title = CmmUtil.nvl(request.getParameter("title"));
		String contents = CmmUtil.nvl(request.getParameter("contents"));
		String review_seq = CmmUtil.nvl(request.getParameter("review_seq"));
		String user_id = (String) session.getAttribute("SS_USER_ID");

		ReviewDTO fDTO = new ReviewDTO();
		fDTO.setTitle(title);
		fDTO.setContents(contents);
		fDTO.setReview_seq(review_seq);

		if (user_id != null) {
			int result = 0;

			try {
				result = reviewService.updateReview(fDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (result > 0) {
				model.addAttribute("msg", "수정되었습니다.");
				model.addAttribute("url", "/manager/board_review_detail.do?review_seq=" + review_seq);

				log.info(this.getClass().getName() + "./manager/Manager_review_modifyProc end!");
			} else {
				model.addAttribute("msg", "수정을 실패했습니다.");
				model.addAttribute("url", "/manager/Manager_review_modify.do?review_seq=" + review_seq);

				log.info(this.getClass().getName() + "./manager/Manager_review_modifyProc end!");
			}

			return "/redirect";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}
	}

	public ReviewDTO filterContent(ReviewDTO fDTO) {
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
	
	public R_CommentDTO filterComment(R_CommentDTO fDTO) {
		if(fDTO.getContent()!=null) {
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
