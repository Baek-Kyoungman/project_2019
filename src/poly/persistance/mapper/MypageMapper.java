package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserInfoDTO;

@Mapper("MypageMapper")
public interface MypageMapper {

	// 사용자 회원탈퇴
	int deleteUserInfo(String user_id) throws Exception;

	// 사용자 비밀번호 변경
	int updateUserPassword(UserInfoDTO uDTO) throws Exception;

	// 사용자 이메일 변경
	int updateUserEmail(UserInfoDTO uDTO) throws Exception;

	// 사용자 직업 변경
	int updateUserJob(UserInfoDTO uDTO) throws Exception;

}
