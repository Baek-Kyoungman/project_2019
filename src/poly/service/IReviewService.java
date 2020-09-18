package poly.service;

import java.util.List;

import poly.dto.ReviewDTO;

public interface IReviewService {

	// 리뷰게시판 등록
	int insertReviewInfo(ReviewDTO fDTO) throws Exception;

	// 리뷰게시판 목록
	List<ReviewDTO> getReviewList(int startNum, int endNum) throws Exception;

	// 리뷰게시판 상세 보기
	ReviewDTO getReviewDetail(int review_seq) throws Exception;

	// 리뷰게시판 개수
	String getReviewCnt() throws Exception;

	// 리뷰게시판 삭제
	int deleteReviewInfo(String seq) throws Exception;

	// 마이페이지 리뷰게시판 리스트
	List<ReviewDTO> getMypage_reviewList(String user_id, int startNum, int endNum) throws Exception;

	// 마이페이지 리뷰게시판 개수
	String getMypage_ReviewCnt(String user_id) throws Exception;
	
	// 마이페이지 리뷰게시판 상세 보기
	ReviewDTO getMypage_reviewDetail(int review_seq) throws Exception;

	// 마이페이지 리뷰게시판 수정
	int updateReview(ReviewDTO fDTO) throws Exception;

	// 리뷰게시판 조회수
	int updateRead_cnt(int review_seq) throws Exception;

	// 관리페이지 리뷰게시판 리스트
	List<ReviewDTO> getManager_reviewList(String user_id, int startNum, int endNum) throws Exception;

	// 인덱스 최근 리뷰 리스트
	List<ReviewDTO> getIndexList() throws Exception;

}
