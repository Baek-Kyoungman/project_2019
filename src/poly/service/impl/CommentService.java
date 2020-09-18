package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.F_CommentDTO;
import poly.dto.FreeDTO;
import poly.dto.R_CommentDTO;
import poly.persistance.mapper.CommentMapper;
import poly.service.ICommentService;

@Service("CommentService")
public class CommentService implements ICommentService {

	@Resource(name = "CommentMapper")
	private CommentMapper commentMapper;

	@Override
	public List<F_CommentDTO> f_commentList(String board_seq) throws Exception {
		// 자유게시판 댓글 리스트
		return commentMapper.f_commentList(board_seq);
	}

	@Override
	public int f_commentAdd_Proc(F_CommentDTO bDTO) throws Exception {
		// 자유게시판 댓글 등록
		return commentMapper.f_commentAdd_Proc(bDTO);
	}

	@Override
	public int f_commentDelete(F_CommentDTO rDTO) throws Exception {
		// 자유게시판 댓글 삭제
		return commentMapper.f_commentDelete(rDTO);
	}

	@Override
	public int f_commentModify(F_CommentDTO rDTO) throws Exception {
		// 자유게시판 댓글 수정
		return commentMapper.f_commentModify(rDTO);
	}

	@Override
	public List<R_CommentDTO> r_commentList(String board_seq) throws Exception {
		// 자유게시판 댓글 리스트
		return commentMapper.r_commentList(board_seq);
	}

	@Override
	public int r_commentAdd_Proc(R_CommentDTO bDTO) throws Exception {
		// 자유게시판 댓글 등록
		return commentMapper.r_commentAdd_Proc(bDTO);
	}

	@Override
	public int r_commentDelete(R_CommentDTO rDTO) throws Exception {
		// 자유게시판 댓글 삭제
		return commentMapper.r_commentDelete(rDTO);
	}

	@Override
	public int r_commentModify(R_CommentDTO rDTO) throws Exception {
		// 자유게시판 댓글 수정
		return commentMapper.r_commentModify(rDTO);
	}

	@Override
	public String getMypage_Review_CommentCnt(String writer) throws Exception {
		// 마이페이지_리뷰게시물_댓글 수
		return commentMapper.getMypage_Review_CommentCnt(writer);
	}

	@Override
	public List<R_CommentDTO> getMypage_review_commentList(String writer, int startNum, int endNum) throws Exception {
		// 미이페이지_리뷰게시물_댓글 리스트
		return commentMapper.getMypage_review_commentList(writer, startNum, endNum);
	}

	@Override
	public String getMypage_Free_CommentCnt(String writer) throws Exception {
		// 마이페이지_자유게시물_댓글 수
		return commentMapper.getMypage_Free_CommentCnt(writer);
	}

	@Override
	public List<F_CommentDTO> getMypage_free_commentList(String writer, int startNum, int endNum) throws Exception {
		// 마이페이지_자유게시물_댓글 리스트
		return commentMapper.getMypage_free_commentList(writer, startNum, endNum);
	}
}
