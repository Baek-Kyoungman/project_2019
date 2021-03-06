package poly.service;

import poly.dto.UserInfoDTO;

public interface IUserInfoService {

	// 회원 가입하기(회원정보 등록하기)
	int insertUserInfo(UserInfoDTO pDTO) throws Exception;

	// 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기
	UserInfoDTO getUserLoginCheck(UserInfoDTO pDTO) throws Exception;

	// 아이디 중복검사
	int idCheck(String user_id) throws Exception;

	// 비밀번호 찾기
	UserInfoDTO password_find(UserInfoDTO pDTO) throws Exception;

	// 비밀번호 변경을 위해 아이디와 이메일이 일치하는지 확인하기
	UserInfoDTO getUserPasswordCheck(UserInfoDTO pDTO) throws Exception;

	// 비밀번호 찾기 후 비밀번호 변경
	int updateUserPassword(UserInfoDTO uDTO) throws Exception;

	// 아이디 찾기
	UserInfoDTO getUserIdCheck(UserInfoDTO pDTO) throws Exception;

	UserInfoDTO getUserIdFind(UserInfoDTO pDTO) throws Exception;

}
