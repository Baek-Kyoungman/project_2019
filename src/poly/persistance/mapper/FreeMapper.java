package poly.persistance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import config.Mapper;
import poly.dto.FreeDTO;

@Mapper("FreeMapper")
public interface FreeMapper {

	// 자유게시판 등록
	int insertFreeInfo(FreeDTO fDTO) throws Exception;

	// 자유게시판 리스트
	List<FreeDTO> getFreeList(@Param("startNum") int startNum, @Param("endNum") int endNum) throws Exception;

	// 자유게시판 디테일
	FreeDTO getFreeDetail(int free_seq) throws Exception;

	// 자유게시판 개수
	String getFreeCnt() throws Exception;

	// 자유게시판 삭제
	int deleteFreeInfo(String seq) throws Exception;

	// 마이페이지 자유게시판 리스트
	List<FreeDTO> getMypage_freeList(@Param("user_id") String user_id, @Param("startNum") int startNum, @Param("endNum") int endNum) throws Exception;

	// 마이페이지 자유게시판 개수
	String getMypage_FreeCnt(String user_id) throws Exception;
	
	// 자유게시판 디테일
	FreeDTO getMypage_freeDetail(int free_seq) throws Exception;

	// 자유게시판 수정
	int updateFree(FreeDTO fDTO) throws Exception;

	// 조회수 증가
	int updateRead_cnt(int free_seq) throws Exception;

	// 관리페이지 자유게시판 리스트
	List<FreeDTO> getManager_freeList(String user_id,@Param("startNum") int startNum, @Param("endNum") int endNum) throws Exception;
}
