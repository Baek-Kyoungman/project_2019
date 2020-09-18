package poly.service;

import java.util.List;

import poly.dto.FreeDTO;

public interface IFreeService {

	// 자유게시판 등록
	int insertFreeInfo(FreeDTO fDTO) throws Exception;

	// 자유게시판 목록
	List<FreeDTO> getFreeList(int startNum, int endNum) throws Exception;

	// 자유게시판 상세 보기
	FreeDTO getFreeDetail(int free_seq) throws Exception;

	// 자유게시판 개수
	String getFreeCnt() throws Exception;

	// 자유게시판 삭제
	int deleteFreeInfo(String seq) throws Exception;

	// 마이페이지 자유게시판 리스트
	List<FreeDTO> getMypage_freeList(String user_id, int startNum, int endNum) throws Exception;

	// 마이페이지 자유게시판 개수
	String getMypage_FreeCnt(String user_id) throws Exception;
	
	// 마이페이지 자유게시판 상세 보기
	FreeDTO getMypage_freeDetail(int free_seq) throws Exception;

	// 마이페이지 자유게시판 수정
	int updateFree(FreeDTO fDTO) throws Exception;

	// 자유게시판 조회수
	int updateRead_cnt(int free_seq) throws Exception;

	// 관리페이지 자유게시판 리스트
	List<FreeDTO> getManager_freeList(String user_id, int startNum, int endNum) throws Exception;
}
