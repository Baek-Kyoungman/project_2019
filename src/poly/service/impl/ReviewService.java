package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.ReviewDTO;
import poly.persistance.mapper.ReviewMapper;
import poly.service.IReviewService;

@Service("ReviewService")
public class ReviewService implements IReviewService {

	@Resource(name = "ReviewMapper")
	private ReviewMapper reviewMapper;

	@Override
	public int insertReviewInfo(ReviewDTO fDTO) throws Exception {
		// 리뷰게시판 등록
		return reviewMapper.insertReviewInfo(fDTO);
	}

	@Override
	public List<ReviewDTO> getReviewList(int startNum, int endNum) throws Exception {
		// 리뷰게시판 목록
		return reviewMapper.getReviewList(startNum, endNum);
	}

	@Override
	public ReviewDTO getReviewDetail(int review_seq) throws Exception {
		// 리뷰게시판 상세보기
		return reviewMapper.getReviewDetail(review_seq);
	}

	@Override
	public String getReviewCnt() throws Exception {
		// 리뷰게시판 개수
		return reviewMapper.getReviewCnt();
	}

	@Override
	public int deleteReviewInfo(String seq) throws Exception {
		// 리뷰게시물 삭제
		return reviewMapper.deleteReviewInfo(seq);
	}

	@Override
	public List<ReviewDTO> getMypage_reviewList(String user_id, int startNum, int endNum) throws Exception {
		// 마이페이지 리뷰게시판 리스트
		return reviewMapper.getMypage_reviewList(user_id, startNum, endNum);
	}
	
	@Override
	public String getMypage_ReviewCnt(String user_id) throws Exception {
		// 마이페이지 리뷰게시판 개수
		return reviewMapper.getMypage_ReviewCnt(user_id);
	}

	@Override
	public ReviewDTO getMypage_reviewDetail(int review_seq) throws Exception {
		// 리뷰게시판 상세보기
		return reviewMapper.getMypage_reviewDetail(review_seq);
	}

	@Override
	public int updateRead_cnt(int review_seq) throws Exception {
		// 리뷰게시판 상세보기
		return reviewMapper.updateRead_cnt(review_seq);
	}

	@Override
	public int updateReview(ReviewDTO fDTO) throws Exception {
		// 리뷰게시판 수정
		return reviewMapper.updateReview(fDTO);
	}

	@Override
	public List<ReviewDTO> getManager_reviewList(String user_id, int startNum, int endNum) throws Exception {
		// 관리페이지 리뷰게시판 리스트
		return reviewMapper.getManager_reviewList(user_id, startNum, endNum);
	}

	@Override
	public List<ReviewDTO> getIndexList() throws Exception {
		// 인덱스 최근 리뷰 리스트
		return reviewMapper.getIndexList();
	}
}
