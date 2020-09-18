package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.UserInfoDTO;
import poly.persistance.mapper.MypageMapper;
import poly.service.IMypageService;

@Service("MypageService")
public class MypageService implements IMypageService {

	@Resource(name = "MypageMapper")
	private MypageMapper mypageMapper;

	@Override
	public int deleteUserInfo(String user_id) throws Exception {
		// 사용자 회원탈퇴
		return mypageMapper.deleteUserInfo(user_id);
	}

	@Override
	public int updateUserPassword(UserInfoDTO uDTO) throws Exception {
		// 사용자 비밀번호 변경
		return mypageMapper.updateUserPassword(uDTO);
	}

	@Override
	public int updateUserEmail(UserInfoDTO uDTO) throws Exception {
		// 사용자 이메일 변경
		return mypageMapper.updateUserEmail(uDTO);
	}

	@Override
	public int updateUserJob(UserInfoDTO uDTO) throws Exception {
		// 사용자 직업 변경
		return mypageMapper.updateUserJob(uDTO);
	}

}
