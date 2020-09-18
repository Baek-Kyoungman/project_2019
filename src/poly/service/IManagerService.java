package poly.service;

import java.util.List;

import poly.dto.KidInfoDTO;
import poly.dto.UserInfoDTO;

public interface IManagerService {

	// 관리자 회원탈퇴
	int deleteUserInfo(String user_id) throws Exception;

	// 관리자 비밀번호 변경
	int updateUserPassword(UserInfoDTO uDTO) throws Exception;

	// 관리자 이메일 변경
	int updateUserEmail(UserInfoDTO uDTO) throws Exception;

	// 관리자 직업 변경
	int updateUserJob(UserInfoDTO uDTO) throws Exception;

	List<UserInfoDTO> getManager_UserList(String user_id, int startNum, int endNum) throws Exception;

	// 관리자 회원관리 페이지 권한 변경
	UserInfoDTO getUserInfo(String user_id) throws Exception;

	int alterAuthor(String userCheck) throws Exception;

	int alterUser(String userCheck) throws Exception;

	int alterStop(String userCheck) throws Exception;

	int alterNomal(String userCheck) throws Exception;

	int alterDel(String userCheck) throws Exception;

	// 관리페이지 리뷰게시판 리스트
	List<KidInfoDTO> getManager_kidList(String user_id, int startNum, int endNum) throws Exception;

	// 유치원 개수
	String getKidCnt() throws Exception;

	// 유치원 상세 보기
	KidInfoDTO getKidDetail(int kid_seq) throws Exception;

	// 유치원 등록
	int insertKidInfo(KidInfoDTO fDTO) throws Exception;

	// 유치원 삭제
	int deleteKidInfo(String seq) throws Exception;

	// 유치원 수정
	int updateKid(KidInfoDTO nDTO) throws Exception;
	
	// 회원리스트 개수
	String getUserCnt() throws Exception;
}
