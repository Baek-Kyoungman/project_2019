package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.KidInfoDTO;
import poly.service.IManagerService;

@Controller
public class Map_searchController {
	private Logger log = Logger.getLogger(this.getClass());

	// 비즈니스 로직
	@Resource(name = "ManagerService")
	private IManagerService managerService;

	// 유치원 조회 들어가는 페이지
	@RequestMapping(value = "/Map_search")
	public String Map_search(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + ".main/Map_search_detail start!");

		String user_id = (String) session.getAttribute("SS_USER_ID");

		if (user_id != null) {

			log.info(this.getClass().getName() + ".main/Map_search_detail end!");
			return "/main/map/map_search";
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	// 유치원관리 상세화면으로 이동
	@RequestMapping(value = "/Map_search_detail")
	public String Map_search_detail(HttpSession session, HttpServletRequest request, Model model) throws Exception {

		log.info(this.getClass().getName() + "./Map_search_detail start!");

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
				model.addAttribute("msg", "게시글이 없습니다.");
				model.addAttribute("url", "/Map_search.do");

				log.info(this.getClass().getName() + "./Map_search_detail end!");
				return "redirect";
			} else {
				filterKinder(nDTO); 

				model.addAttribute("nDTO", nDTO);
				log.info(this.getClass().getName() + "./Map_search_detail end!");
				return "/main/map/map_search_detail";
			}

		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			model.addAttribute("url", "/user/loginForm.do");
			return "/redirect";
		}

	}

	public KidInfoDTO filterKinder(KidInfoDTO nDTO) {
		if (nDTO.getEstablish() != null) {
			nDTO.setEstablish(nDTO.getEstablish().replaceAll("scr!pt", "script"));
			nDTO.setEstablish(nDTO.getEstablish().replaceAll("& #39;", "&#39"));
			nDTO.setEstablish(nDTO.getEstablish().replaceAll("& lt;", "&lt;"));
			nDTO.setEstablish(nDTO.getEstablish().replaceAll("& gt;", "&gt;"));
			nDTO.setEstablish(nDTO.getEstablish().replaceAll("& #40;", "("));
			nDTO.setEstablish(nDTO.getEstablish().replaceAll("& #41;", ")"));
			nDTO.setEstablish(nDTO.getEstablish().replaceAll("&nbsp;", " "));
			log.info(nDTO.getEstablish());
		}

		return nDTO;
	}
}
