package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.MailDTO;
import poly.dto.UserInfoDTO;
import poly.persistance.mapper.UserInfoMapper;
import poly.service.IMailService;
import poly.service.IUserInfoService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@Service("UserInfoService")
public class UserInfoService implements IUserInfoService {

	@Resource(name = "UserInfoMapper")
	private UserInfoMapper userInfoMapper;

	// 메일 발송을 위한 MailService 자바 객체 가져오기
	@Resource(name = "MailService")
	private IMailService MailService;

	@Override
	public int insertUserInfo(UserInfoDTO pDTO) throws Exception {

		// 회원가입 성공: 1, 아이디 중복으로인한 가입 취소 : 2, 기타 에러 발생 : 0
		int res = 0;

		// controller에서 값이 정상적으로 못 넘어오는 경우를 대비하기 위해 사용함
		if (pDTO == null) {
			pDTO = new UserInfoDTO();
		}

		// 회원 가입 중복 방지를 위해 DB에서 데이터 조회
		UserInfoDTO rDTO = userInfoMapper.getUserExists(pDTO);

		// mapper에서 같이 정상적으로 못 넘어오는 경우를 대비하기 위해 사용함
		if (rDTO == null) {
			rDTO = new UserInfoDTO();
		}

		// 중복된 회원정보가 있는 경우, 결과값을 2로 변경하고, 더 이상 작업을 진행하지 않음
		if (CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")) {
			res = 2;

			// 회원가입이 중복이 아니므로, 회원가입 진행함
		} else {

			// 회원가입
			int success = userInfoMapper.insertUserInfo(pDTO);

			// db에 데이터가 등록되었다면(회원가입 성공했다면.....
			if (success > 0) {
				res = 1;

				/*
				 * ################################################ 메일 발송 로직 추가 시작!!
				 * ################################################
				 */

				MailDTO mDTO = new MailDTO();

				// 회원정보화면에서 입력받은 이메일 변수(아직 암호화되어 넘어오기 때문에 복호화 수행함)
				mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));

				mDTO.setTitle("회원가입을 축하드립니다. "); // 제목

				// 메일 내용에 가입자 이름 넣어서 내용 발송
				mDTO.setContents(CmmUtil.nvl(pDTO.getUser_name()) + "님의 회원가입을 진심으로 축하드립니다.");

				// 회원 가입이 성공했기 때문에 메일을 발송함
				MailService.doSendMail(mDTO);

				/*
				 * ################################################ 메일 발송 로직 추가 시작!!
				 * ################################################
				 */

			} else {
				res = 0;

			}

		}

		return res;

	}

	/*
	 * 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기
	 * 
	 * @param UserInfoDTO 로그인을 위한 회원아이디, 비밀번호
	 * 
	 * @return UserInfoDTO 로그인한 회원아이디 정보
	 */
//	@Override
//	public int getUserLoginCheck(UserInfoDTO pDTO) throws Exception{
//		
//		// 사용자 로그인 성공 : 1, 관리자 로그인 성공 : 2,  실패 : 0
//		int res = 0;
//		
//		// 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 mapper 호출하기
//		UserInfoDTO rDTO = userInfoMapper.getUserLoginCheck(pDTO);
//		
//		if (rDTO == null) {
//			rDTO = new UserInfoDTO();
//				
//		}
//		/*
//		 * ##############################################################################
//		 * 							로그인 성공 여부 체크 시작!!
//		 * ##############################################################################
//		 * */
//		
//		/*
//		 * userInfoMapper로 부터 SELECT 쿼리의 결과로 회원아이디를 받아왔다면, 로그인 성공!!
//		 * 
//		 * DTO의 변수에 값이 있는지 확인하기 처리속도 측면에서 가장 좋은 방법은 변수의 길이를 가져오는 것이다.
//		 * 따라서 .length() 함수를 통해 회원아이디의 글자수를 가져와 0보다 큰지 비교한다.
//		 * 0보다 크다면, 굴자가 존재하는 것이기 때문에 같이 존재한다.
//		 * */
//		if (CmmUtil.nvl(rDTO.getUser_id()).length()>0) {
//			if(CmmUtil.nvl(rDTO.getUser_Author()).equals("0")) {
//				res = 1;
//			}else {
//				res = 2;
//			}
//		}
//		
//		/*
//		 * ##############################################################################
//		 * 							로그인 성공 여부 체크 끝!!
//		 * ##############################################################################
//		 * */
//		
//		return res;
//	}

	public int idCheck(String user_id) throws Exception {
		// 아이디 중복검사
		return userInfoMapper.idCheck(user_id);
	}

	@Override
	public UserInfoDTO getUserLoginCheck(UserInfoDTO pDTO) throws Exception {
		// 로그인
		return userInfoMapper.getUserLoginCheck(pDTO);
	}

	@Override
	public UserInfoDTO password_find(UserInfoDTO pDTO) throws Exception {
		// 비밀번호 찾기 1
		return userInfoMapper.password_find(pDTO);
	}

	@Override
	public UserInfoDTO getUserPasswordCheck(UserInfoDTO pDTO) throws Exception {
		// 비밀번호 찾기 2
		return userInfoMapper.getUserPasswordCheck(pDTO);
	}

	@Override
	public int updateUserPassword(UserInfoDTO uDTO) throws Exception {
		// 비밀번호 찾기 후 비밀번호 변경
		return userInfoMapper.updateUserPassword(uDTO);
	}

	@Override
	public UserInfoDTO getUserIdCheck(UserInfoDTO pDTO) throws Exception {
		// 아이디 찾기
		return userInfoMapper.getUserIdCheck(pDTO);
	}

	@Override
	public UserInfoDTO getUserIdFind(UserInfoDTO pDTO) throws Exception {
		// 아이디 찾기
		return userInfoMapper.getUserIdFind(pDTO);
	}
}
