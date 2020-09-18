package poly.service;

import java.util.List;

import poly.dto.F_CommentDTO;
import poly.dto.R_CommentDTO;

public interface ICommentService {

	// 자유게시판 댓글 리스트
	List<F_CommentDTO> f_commentList(String board_seq) throws Exception;
	
	// 자유게시판 댓글 등록
	int f_commentAdd_Proc(F_CommentDTO bDTO) throws Exception;
	
	// 자유게시판 댓글 삭제
	int f_commentDelete(F_CommentDTO rDTO) throws Exception;

	// 자유게시판 댓글 수정
	int f_commentModify(F_CommentDTO rDTO) throws Exception;
	
	// 리뷰게시판 댓글 리스트
	List<R_CommentDTO> r_commentList(String board_seq) throws Exception;
	
	// 리뷰게시판 댓글 등록
	int r_commentAdd_Proc(R_CommentDTO bDTO) throws Exception;
	
	// 리뷰게시판 댓글 삭제
	int r_commentDelete(R_CommentDTO rDTO) throws Exception;

	// 리뷰게시판 댓글 수정
	int r_commentModify(R_CommentDTO rDTO) throws Exception;

	// 마이페이지_리뷰게시물_댓글 수
	String getMypage_Review_CommentCnt(String writer) throws Exception;

	// 미이페이지_리뷰게시물_뎃글 리스트
	List<R_CommentDTO> getMypage_review_commentList(String writer, int startNum, int endNum) throws Exception;
	
	// 마이페이지_자유게시물_댓글 수
	String getMypage_Free_CommentCnt(String writer) throws Exception;

	// 미이페이지_자유게시물_댓글 리스트
	List<F_CommentDTO> getMypage_free_commentList(String writer, int startNum, int endNum) throws Exception;
}
