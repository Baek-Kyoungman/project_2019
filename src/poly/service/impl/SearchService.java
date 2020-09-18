package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.FreeDTO;
import poly.dto.ReviewDTO;
import poly.persistance.mapper.SearchMapper;
import poly.service.ISearchService;

@Service("SearchService")
public class SearchService implements ISearchService {

	@Resource(name = "SearchMapper")
	private SearchMapper searchMapper;

	@Override
	public List<ReviewDTO> searchReview(ReviewDTO sDTO) throws Exception {
		// 리뷰게시판 검색 실행
		return searchMapper.searchReview(sDTO);
	}

	@Override
	public List<FreeDTO> searchFree(FreeDTO fDTO) throws Exception {
		// 리뷰게시판 검색 실행
		return searchMapper.searchFree(fDTO);
	}
}
