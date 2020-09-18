package poly.persistance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import config.Mapper;
import poly.dto.FreeDTO;
import poly.dto.ReviewDTO;

@Mapper("SearchMapper")
public interface SearchMapper {

	// 리뷰게시판 검색 실행
	List<ReviewDTO> searchReview(ReviewDTO sDTO) throws Exception;

	// 자유게시판 검색 실행
	List<FreeDTO> searchFree(FreeDTO fDTO) throws Exception;
}
