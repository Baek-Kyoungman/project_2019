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

import poly.dto.F_CommentDTO;
import poly.dto.FreeDTO;
import poly.dto.R_CommentDTO;
import poly.service.ICommentService;
import poly.util.CmmUtil;

@Controller
public class CommentController {

	private Logger log = Logger.getLogger(this.getClass());

	// 비즈니스 로직
	@Resource(name = "CommentService")
	private ICommentService commentService;

	// 자유게시판 댓글 등록
	@RequestMapping(value = "/f_commentAdd_Proc")
	public String f_commentAdd_Proc(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./f_commentAdd_Proc start!");

		String content = CmmUtil.nvl(request.getParameter("content"));
		String SS_USER_ID = (String) session.getAttribute("SS_USER_ID");
		String board_seq = CmmUtil.nvl(request.getParameter("board_seq"));

		log.info("content : " + content);
		log.info("SS_USER_ID : " + SS_USER_ID);
		log.info("board_seq : " + board_seq);

		F_CommentDTO bDTO = new F_CommentDTO();
		bDTO.setContent(content);
		bDTO.setWriter(SS_USER_ID);
		bDTO.setBoard_seq(board_seq);

		int result = 0;

		try {
			result = commentService.f_commentAdd_Proc(bDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("result : " + result);

		if (!content.equals("")) {
			if (result > 0) {
				model.addAttribute("url", "/FreeDetail.do?free_seq=" + board_seq);
				model.addAttribute("msg", "댓글 등록에 성공하였습니다.");
			} else {
				model.addAttribute("url", "/FreeDetail.do?free_seq=" + board_seq);
				model.addAttribute("msg", "댓글 등록에 실패하였습니다.");
			}
		} else {
			model.addAttribute("url", "/FreeDetail.do?free_seq=" + board_seq);
			model.addAttribute("msg", "댓글을 입력 해주세요.");
		}

		return "/redirect";
	}

	// 자유게시판 댓글 삭제
	@RequestMapping(value = "/f_commentDelete")
	public String f_commentDelete(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./f_commentDelete start!");

		String f_seq = CmmUtil.nvl(request.getParameter("f_seq"));
		String board_seq = CmmUtil.nvl(request.getParameter("board_seq"));

		log.info("f_seq : " + f_seq);
		log.info("board_seq : " + board_seq);

		F_CommentDTO rDTO = new F_CommentDTO();
		rDTO.setF_seq(f_seq);

		int result = 0;

		try {
			result = commentService.f_commentDelete(rDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("result : " + result);

		if (result > 0) {
			model.addAttribute("url", "/FreeDetail.do?free_seq=" + board_seq);
			model.addAttribute("msg", "삭제되었습니다.");
		} else {
			model.addAttribute("url", "/FreeDetail.do?free_seq=" + board_seq);
			model.addAttribute("msg", "삭제에 실패하였습니다.");
		}

		log.info(this.getClass().getName() + "./f_commentDelete end!");

		return "/redirect";
	}

	// 자유게시판 댓글 수정
	@RequestMapping(value = "/f_commentModify")
	public String f_commentModify(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./f_commentModify start!");

		String f_seq = CmmUtil.nvl(request.getParameter("f_seq"));
		String board_seq = CmmUtil.nvl(request.getParameter("board_seq"));
		String content = CmmUtil.nvl(request.getParameter("content"));

		F_CommentDTO rDTO = new F_CommentDTO();
		rDTO.setF_seq(f_seq);
		rDTO.setContent(content);

		log.info("f_seq : " + f_seq);
		log.info("content : " + content);

		int result = 0;

		try {
			result = commentService.f_commentModify(rDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("result : " + result);

		if (result > 0) {
			model.addAttribute("url", "/FreeDetail.do?free_seq=" + board_seq);
			model.addAttribute("msg", "댓글이 수정되었습니다.");
		} else {
			model.addAttribute("url", "/FreeDetail.do?free_seq=" + board_seq);
			model.addAttribute("msg", "댓글 수정에 실패하였습니다.");
		}

		log.info(this.getClass().getName() + "./f_commentModify end!");

		return "/redirect";
	}

	// 자유게시판 댓글 수정
	@RequestMapping(value = "/mypage_f_commentModify")
	public String mypage_f_commentModify(HttpServletRequest request, HttpSession session, Model model)
			throws Exception {

		log.info(this.getClass().getName() + "./f_commentModify start!");

		String f_seq = CmmUtil.nvl(request.getParameter("f_seq"));
		String board_seq = CmmUtil.nvl(request.getParameter("board_seq"));
		String content = CmmUtil.nvl(request.getParameter("content"));

		F_CommentDTO rDTO = new F_CommentDTO();
		rDTO.setF_seq(f_seq);
		rDTO.setContent(content);

		log.info("f_seq : " + f_seq);
		log.info("content : " + content);

		int result = 0;

		try {
			result = commentService.f_commentModify(rDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("result : " + result);

		if (result > 0) {
			model.addAttribute("url", "/mypage/dashboard_free_comment.do");
			model.addAttribute("msg", "댓글이 수정되었습니다.");
		} else {
			model.addAttribute("url", "/mypage/dashboard_free_comment.do");
			model.addAttribute("msg", "댓글 수정에 실패하였습니다.");
		}

		log.info(this.getClass().getName() + "./f_commentModify end!");

		return "/redirect";
	}

	// 리뷰게시판 댓글 등록
	@RequestMapping(value = "/r_commentAdd_Proc")
	public String r_commentAdd_Proc(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./r_commentAdd_Proc start!");

		String content = CmmUtil.nvl(request.getParameter("content"));
		String SS_USER_ID = (String) session.getAttribute("SS_USER_ID");
		String board_seq = CmmUtil.nvl(request.getParameter("board_seq"));

		log.info("content : " + content);
		log.info("SS_USER_ID : " + SS_USER_ID);
		log.info("board_seq : " + board_seq);

		R_CommentDTO bDTO = new R_CommentDTO();
		bDTO.setContent(content);
		bDTO.setWriter(SS_USER_ID);
		bDTO.setBoard_seq(board_seq);

		int result = 0;

		try {
			result = commentService.r_commentAdd_Proc(bDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("result : " + result);
		if (!content.equals("")) {
			if (result > 0) {
				model.addAttribute("url", "/ReviewDetail.do?review_seq=" + board_seq);
				model.addAttribute("msg", "댓글 등록에 성공하였습니다.");
			} else {
				model.addAttribute("url", "/ReviewDetail.do?review_seq=" + board_seq);
				model.addAttribute("msg", "댓글 등록에 실패하였습니다.");
			}
		} else {
			model.addAttribute("url", "/ReviewDetail.do?review_seq=" + board_seq);
			model.addAttribute("msg", "댓글을 입력 헤주세요.");
		}

		return "/redirect";
	}

	// 리뷰게시판 댓글 삭제
	@RequestMapping(value = "/r_commentDelete")
	public String r_commentDelete(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./r_commentDelete start!");

		String r_seq = CmmUtil.nvl(request.getParameter("r_seq"));
		String board_seq = CmmUtil.nvl(request.getParameter("board_seq"));

		log.info("r_seq : " + r_seq);
		log.info("board_seq : " + board_seq);

		R_CommentDTO rDTO = new R_CommentDTO();
		rDTO.setR_seq(r_seq);

		int result = 0;

		try {
			result = commentService.r_commentDelete(rDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("result : " + result);

		if (result > 0) {
			model.addAttribute("url", "/ReviewDetail.do?review_seq=" + board_seq);
			model.addAttribute("msg", "삭제되었습니다.");
		} else {
			model.addAttribute("url", "/ReviewDetail.do?review_seq=" + board_seq);
			model.addAttribute("msg", "삭제에 실패하였습니다.");
		}

		log.info(this.getClass().getName() + "./r_commentDelete end!");

		return "/redirect";
	}

	// 리뷰게시판 댓글 수정
	@RequestMapping(value = "/r_commentModify")
	public String r_commentModify(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + "./r_commentModify start!");

		String r_seq = CmmUtil.nvl(request.getParameter("r_seq"));
		String content = CmmUtil.nvl(request.getParameter("content"));
		String board_seq = CmmUtil.nvl(request.getParameter("board_seq"));

		R_CommentDTO rDTO = new R_CommentDTO();
		rDTO.setR_seq(r_seq);
		rDTO.setContent(content);

		log.info("r_seq : " + r_seq);
		log.info("content : " + content);

		int result = 0;

		try {
			result = commentService.r_commentModify(rDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("result : " + result);

		if (result > 0) {
			model.addAttribute("url", "/ReviewDetail.do?review_seq=" + board_seq);
			model.addAttribute("msg", "댓글이 수정되었습니다.");
		} else {
			model.addAttribute("url", "/ReviewDetail.do?review_seq=" + board_seq);
			model.addAttribute("msg", "댓글 수정에 실패하였습니다.");
		}

		log.info(this.getClass().getName() + "./r_commentModify end!");

		return "/redirect";
	}

	// 대시보드_리뷰게시물 댓글 화면으로 이동
	@RequestMapping(value = "mypage/dashboard_review_comment")
	public String Dashboard_review_comment(HttpServletRequest request, HttpSession session, Model model)
			throws Exception {

		log.info(this.getClass().getName() + "./mypage/dashboard_review_comment start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");
		String writer = user_id;

		if (user_id != null) {

			log.info(this.getClass());

			int pgNum = 1;
			if (request.getParameter("pgNum") != null && !request.getParameter("pgNum").equals("")) {
				pgNum = Integer.parseInt(request.getParameter("pgNum"));
			}

			// 페이징
			int startNum = (pgNum - 1) * 10 + 1;
			int endNum = (pgNum - 1) * 10 + 10;
			List<R_CommentDTO> nList = new ArrayList<>();

			int total = Integer.parseInt(commentService.getMypage_Review_CommentCnt(writer));

			nList = commentService.getMypage_review_commentList(writer, startNum, endNum);

			if (nList == null) {
				nList = new ArrayList<R_CommentDTO>();
			} else {
				for (int i = 0; i < nList.size(); i++) {
					nList.get(i).setReg_dt(nList.get(i).getReg_dt().substring(2, 16));
					filterComment_R(nList.get(i));
					log.info(nList.get(i).getContent());
				}
			}

			model.addAttribute("pgNum", pgNum);
			model.addAttribute("total", total);

			model.addAttribute("nList", nList);

			log.info(this.getClass().getName() + "./mypage/dashboard_review_comment end!");
			return "/mypage/dashboard_comment/mypage_review_comment";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 마이페이지 리뷰게시판 댓글 수정
	@RequestMapping(value = "/mypage_r_commentModify")
	public String mypage_r_commentModify(HttpServletRequest request, HttpSession session, Model model)
			throws Exception {

		log.info(this.getClass().getName() + "./mypage_r_commentModify start!");

		String r_seq = CmmUtil.nvl(request.getParameter("r_seq"));
		String content = CmmUtil.nvl(request.getParameter("content"));

		R_CommentDTO rDTO = new R_CommentDTO();
		rDTO.setR_seq(r_seq);
		rDTO.setContent(content);

		log.info("r_seq : " + r_seq);
		log.info("content : " + content);

		int result = 0;

		try {
			result = commentService.r_commentModify(rDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("result : " + result);

		if (result > 0) {
			model.addAttribute("url", "/mypage/dashboard_review_comment.do");
			model.addAttribute("msg", "댓글이 수정되었습니다.");
		} else {
			model.addAttribute("url", "/mypage/dashboard_review_comment.do");
			model.addAttribute("msg", "댓글 수정에 실패하였습니다.");
		}

		log.info(this.getClass().getName() + "./mypage_r_commentModify end!");

		return "/redirect";
	}

	// 대시보드_리뷰게시물 댓글 삭제
	@RequestMapping(value = "/mypage_r_commentDelete")
	public String mypage_r_commentDelete(HttpServletRequest request, HttpSession session, Model model)
			throws Exception {

		log.info(this.getClass().getName() + "./r_commentDelete start!");

		String r_seq = CmmUtil.nvl(request.getParameter("r_seq"));
		String board_seq = CmmUtil.nvl(request.getParameter("board_seq"));

		log.info("r_seq : " + r_seq);
		log.info("board_seq : " + board_seq);

		R_CommentDTO rDTO = new R_CommentDTO();
		rDTO.setR_seq(r_seq);

		int result = 0;

		try {
			result = commentService.r_commentDelete(rDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("result : " + result);

		if (result > 0) {
			model.addAttribute("url", "/mypage/dashboard_review_comment.do");
			model.addAttribute("msg", "삭제되었습니다.");
		} else {
			model.addAttribute("url", "/mypage/dashboard_review_comment.do");
			model.addAttribute("msg", "삭제에 실패하였습니다.");
		}

		log.info(this.getClass().getName() + "./r_commentDelete end!");

		return "/redirect";
	}

	// 대시보드_자유게시물 댓글 화면으로 이동
	@RequestMapping(value = "mypage/dashboard_free_comment")
	public String Dashboard_free_comment(HttpServletRequest request, HttpSession session, Model model)
			throws Exception {

		log.info(this.getClass().getName() + "./mypage/dashboard_free_comment start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");
		String writer = user_id;

		if (user_id != null) {

			log.info(this.getClass());

			int pgNum = 1;
			if (request.getParameter("pgNum") != null && !request.getParameter("pgNum").equals("")) {
				pgNum = Integer.parseInt(request.getParameter("pgNum"));
			}

			// 페이징
			int startNum = (pgNum - 1) * 10 + 1;
			int endNum = (pgNum - 1) * 10 + 10;
			List<F_CommentDTO> nList = new ArrayList<>();

			int total = Integer.parseInt(commentService.getMypage_Free_CommentCnt(writer));

			nList = commentService.getMypage_free_commentList(writer, startNum, endNum);

			if (nList == null) {
				nList = new ArrayList<F_CommentDTO>();
			} else {
				for (int i = 0; i < nList.size(); i++) {
					nList.get(i).setReg_dt(nList.get(i).getReg_dt().substring(2, 16));
					filterComment_F(nList.get(i));
					log.info(nList.get(i).getContent());
				}
			}

			model.addAttribute("pgNum", pgNum);
			model.addAttribute("total", total);

			model.addAttribute("nList", nList);

			log.info(this.getClass().getName() + "./mypage/dashboard_free_comment end!");
			return "/mypage/dashboard_comment/mypage_free_comment";

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 대시보드_자유게시물 댓글 삭제
	@RequestMapping(value = "/mypage_f_commentDelete")
	public String mypage_f_commentDelete(HttpServletRequest request, HttpSession session, Model model)
			throws Exception {

		log.info(this.getClass().getName() + "./f_commentDelete start!");

		String f_seq = CmmUtil.nvl(request.getParameter("f_seq"));
		String board_seq = CmmUtil.nvl(request.getParameter("board_seq"));

		log.info("f_seq : " + f_seq);
		log.info("board_seq : " + board_seq);

		F_CommentDTO rDTO = new F_CommentDTO();
		rDTO.setF_seq(f_seq);

		int result = 0;

		try {
			result = commentService.f_commentDelete(rDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("result : " + result);

		if (result > 0) {
			model.addAttribute("url", "/mypage/dashboard_free_comment.do");
			model.addAttribute("msg", "삭제되었습니다.");
		} else {
			model.addAttribute("url", "/mypage/dashboard_free_comment.do");
			model.addAttribute("msg", "삭제에 실패하였습니다.");
		}

		log.info(this.getClass().getName() + "./f_commentDelete end!");

		return "/redirect";
	}

	public R_CommentDTO filterComment_R(R_CommentDTO fDTO) {
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

	public F_CommentDTO filterComment_F(F_CommentDTO fDTO) {
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
