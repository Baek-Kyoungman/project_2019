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

import poly.dto.FreeDTO;
import poly.dto.ReviewDTO;
import poly.service.IReviewService;
import poly.service.ISearchService;
import poly.service.impl.SearchService;
import poly.util.CmmUtil;

@Controller
public class SearchController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ISearchService searchService;

	// 리뷰게시판 검색 실행
	@RequestMapping(value = "/main/ReviewList_search")
	public String ReviewList_search(HttpSession session, HttpServletRequest request, HttpServletResponse respone,
			Model model) throws Exception {

		log.info(this.getClass().getName() + "./main/ReviewList_search start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {
			String sidoCode = CmmUtil.nvl(request.getParameter("sidoCode"));
			String sggCode = CmmUtil.nvl(request.getParameter("sggCode"));
			String type = CmmUtil.nvl(request.getParameter("type"));

			ReviewDTO sDTO = new ReviewDTO();
			sDTO.setSidoCode(sidoCode);
			sDTO.setSggCode(sggCode);
			sDTO.setType(type);

			List<ReviewDTO> sList = new ArrayList<ReviewDTO>();

			sList = searchService.searchReview(sDTO);

			if (sList == null) {
				sList = new ArrayList<ReviewDTO>();
			} else {
				for (int i = 0; i < sList.size(); i++) {
					sList.get(i).setChg_dt(sList.get(i).getChg_dt().substring(0,19));
					filterContent_review(sList.get(i));
					log.info(sList.get(i).getTitle());
					log.info(sList.get(i).getContents());
				}
			}
			
			model.addAttribute("sList", sList);

			log.info(this.getClass().getName() + "./main/ReviewList_search end!");
			return "/main/review/ReviewList_search";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 자유게시판 검색 실행
	@RequestMapping(value = "/main/FreeList_search")
	public String FreeList_search(HttpSession session, HttpServletRequest request, HttpServletResponse respone,
			Model model) throws Exception {

		log.info(this.getClass().getName() + "./main/FreeList_search start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {
			String sidoCode = CmmUtil.nvl(request.getParameter("sidoCode"));
			String sggCode = CmmUtil.nvl(request.getParameter("sggCode"));
			String type = CmmUtil.nvl(request.getParameter("type"));

			FreeDTO fDTO = new FreeDTO();
			fDTO.setSidoCode(sidoCode);
			fDTO.setSggCode(sggCode);
			fDTO.setType(type);

			List<FreeDTO> fList = new ArrayList<FreeDTO>();

			fList = searchService.searchFree(fDTO);

			if (fList == null) {
				fList = new ArrayList<FreeDTO>();
			} else {
				for (int i = 0; i < fList.size(); i++) {
					fList.get(i).setChg_dt(fList.get(i).getChg_dt().substring(0,19));
					filterContent_free(fList.get(i));
					log.info(fList.get(i).getTitle());
					log.info(fList.get(i).getContents());
				}
			}

			model.addAttribute("fList", fList);

			log.info(this.getClass().getName() + "./main/FreeList_search end!");
			return "/main/free/FreeList_search";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}
	
	public FreeDTO filterContent_free(FreeDTO fDTO) {
		if(fDTO.getTitle()!=null) {
			fDTO.setTitle(fDTO.getTitle().replaceAll("scr!pt", "script"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& #39;", "&#39"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& lt;", "&lt;"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& gt;", "&gt;"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& #40;", "("));
			fDTO.setTitle(fDTO.getTitle().replaceAll("& #41;", ")"));
			fDTO.setTitle(fDTO.getTitle().replaceAll("&nbsp;", " "));
			log.info(fDTO.getTitle());
		}
		
		if(fDTO.getContents()!=null) {
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
		
		if(fDTO.getType()!=null) {
			fDTO.setType(fDTO.getType().replaceAll("& #40;", "("));
			fDTO.setType(fDTO.getType().replaceAll("& #41;", ")"));
			fDTO.setType(fDTO.getType().replaceAll("&nbsp;", " "));
			log.info(fDTO.getType());
		}
		
		return fDTO;
	}
	
	public ReviewDTO filterContent_review(ReviewDTO sDTO) {
		if (sDTO.getTitle() != null) {
			sDTO.setTitle(sDTO.getTitle().replaceAll("scr!pt", "script"));
			sDTO.setTitle(sDTO.getTitle().replaceAll("& #39;", "&#39"));
			sDTO.setTitle(sDTO.getTitle().replaceAll("& lt;", "&lt;"));
			sDTO.setTitle(sDTO.getTitle().replaceAll("& gt;", "&gt;"));
			sDTO.setTitle(sDTO.getTitle().replaceAll("& #40;", "("));
			sDTO.setTitle(sDTO.getTitle().replaceAll("& #41;", ")"));
			sDTO.setTitle(sDTO.getTitle().replaceAll("&nbsp;", " "));
			log.info(sDTO.getTitle());
		}

		if (sDTO.getContents() != null) {
			sDTO.setContents(sDTO.getContents().replaceAll("scr!pt", "script"));
			sDTO.setContents(sDTO.getContents().replaceAll("& lt;script& gt;", "&lt;script&gt;"));
			sDTO.setContents(sDTO.getContents().replaceAll("& lt;/script& gt;", "&lt;/script&gt;"));
			sDTO.setContents(sDTO.getContents().replaceAll("& #39;", "&#39;"));
			sDTO.setContents(sDTO.getContents().replaceAll("& lt;", "<"));
			sDTO.setContents(sDTO.getContents().replaceAll("& gt;", ">"));
			sDTO.setContents(sDTO.getContents().replaceAll("& #40;", "("));
			sDTO.setContents(sDTO.getContents().replaceAll("& #41;", ")"));
			sDTO.setContents(sDTO.getContents().replaceAll("&nbsp;", " "));
			log.info(sDTO.getContents());
		}

		if (sDTO.getType() != null) {
			sDTO.setType(sDTO.getType().replaceAll("& #40;", "("));
			sDTO.setType(sDTO.getType().replaceAll("& #41;", ")"));
			sDTO.setType(sDTO.getType().replaceAll("&nbsp;", " "));
			log.info(sDTO.getType());
		}

		return sDTO;
	}
}
