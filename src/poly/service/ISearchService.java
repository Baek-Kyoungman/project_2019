package poly.service;

import java.util.List;

import poly.dto.FreeDTO;
import poly.dto.ReviewDTO;

public interface ISearchService {

	// 리뷰게시판 검색 실행
	List<ReviewDTO> searchReview(ReviewDTO sDTO) throws Exception;

	// 자유게시판 검색 실행
	List<FreeDTO> searchFree(FreeDTO fDTO) throws Exception;
}
