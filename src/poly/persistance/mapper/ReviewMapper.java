package poly.persistance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import config.Mapper;
import poly.dto.ReviewDTO;

@Mapper("ReviewMapper")
public interface ReviewMapper {

	// 리뷰게시판 등록
	int insertReviewInfo(ReviewDTO fDTO) throws Exception;

	// 리뷰게시판 리스트
	List<ReviewDTO> getReviewList(@Param("startNum") int startNum, @Param("endNum") int endNum) throws Exception;

	// 리뷰게시판 디테일
	ReviewDTO getReviewDetail(int review_seq) throws Exception;

	// 리뷰게시판 개수
	String getReviewCnt() throws Exception;

	// 리뷰게시판 삭제
	int deleteReviewInfo(String seq) throws Exception;

	// 마이페이지 리뷰게시판 리스트
	List<ReviewDTO> getMypage_reviewList(@Param("user_id") String user_id, @Param("startNum") int startNum, @Param("endNum") int endNum) throws Exception;

	// 마이페이지 리뷰게시판 개수
	String getMypage_ReviewCnt(String user_id) throws Exception;
	
	// 마이페이지 리뷰게시판 디테일
	ReviewDTO getMypage_reviewDetail(int review_seq) throws Exception;

	// 리뷰게시판 수정
	int updateReview(ReviewDTO fDTO) throws Exception;

	// 조회수 증가
	int updateRead_cnt(int review_seq) throws Exception;

	// 관리페이지 리뷰게시판 리스트
	List<ReviewDTO> getManager_reviewList(String user_id, @Param("startNum") int startNum, @Param("endNum") int endNum) throws Exception;

	// 인데스 최근 리뷰 리스트
	List<ReviewDTO> getIndexList() throws Exception;
}
