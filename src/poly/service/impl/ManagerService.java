package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.KidInfoDTO;
import poly.dto.UserInfoDTO;
import poly.persistance.mapper.ManagerMapper;
import poly.service.IManagerService;

@Service("ManagerService")
public class ManagerService implements IManagerService {

	@Resource(name = "ManagerMapper")
	private ManagerMapper managerMapper;

	@Override
	public int deleteUserInfo(String user_id) throws Exception {
		// 관리자 회원탈퇴
		return managerMapper.deleteUserInfo(user_id);
	}

	@Override
	public int updateUserPassword(UserInfoDTO uDTO) throws Exception {
		// 관리자 비밀번호 변경
		return managerMapper.updateUserPassword(uDTO);
	}

	@Override
	public int updateUserEmail(UserInfoDTO uDTO) throws Exception {
		// 관리자 이메일 변경
		return managerMapper.updateUserEmail(uDTO);
	}

	@Override
	public int updateUserJob(UserInfoDTO uDTO) throws Exception {
		// 관리자 직업 변경
		return managerMapper.updateUserJob(uDTO);
	}

	@Override
	public List<UserInfoDTO> getManager_UserList(String user_id, int startNum, int endNum) throws Exception {
		// 마이페이지 자유게시판 리스트
		return managerMapper.getManager_UserList(user_id, startNum, endNum);
	}

	@Override
	public UserInfoDTO getUserInfo(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return managerMapper.getUserInfo(user_id);
	}

	@Override
	public int alterAuthor(String userCheck) throws Exception {
		// TODO Auto-generated method stub
		return managerMapper.alterAuthor(userCheck);
	}

	@Override
	public int alterUser(String userCheck) throws Exception {
		// TODO Auto-generated method stub
		return managerMapper.alterUser(userCheck);
	}

	@Override
	public int alterStop(String userCheck) throws Exception {
		// TODO Auto-generated method stub
		return managerMapper.alterStop(userCheck);
	}

	@Override
	public int alterNomal(String userCheck) throws Exception {
		// TODO Auto-generated method stub
		return managerMapper.alterNomal(userCheck);
	}

	@Override
	public int alterDel(String userCheck) throws Exception {
		// TODO Auto-generated method stub
		return managerMapper.alterDel(userCheck);
	}

	@Override
	public List<KidInfoDTO> getManager_kidList(String user_id, int startNum, int endNum) throws Exception {
		// 관리페이지 유치원 리스트
		return managerMapper.getManager_kidList(user_id, startNum, endNum);
	}

	@Override
	public String getKidCnt() throws Exception {
		// 유치원 개수
		return managerMapper.getKidCnt();
	}

	@Override
	public KidInfoDTO getKidDetail(int kid_seq) throws Exception {
		// 자유게시판 상세보기
		return managerMapper.getKidDetail(kid_seq);
	}

	@Override
	public int insertKidInfo(KidInfoDTO fDTO) throws Exception {
		// 유치원 등록
		return managerMapper.insertKidInfo(fDTO);
	}

	@Override
	public int deleteKidInfo(String seq) throws Exception {
		// 유치원 삭제
		return managerMapper.deleteKidInfo(seq);
	}

	@Override
	public int updateKid(KidInfoDTO nDTO) throws Exception {
		// 자유게시판 수정
		return managerMapper.updateKid(nDTO);
	}
	
	@Override
	public String getUserCnt() throws Exception {
		// 리뷰게시판 개수
		return managerMapper.getUserCnt();
	}
}
