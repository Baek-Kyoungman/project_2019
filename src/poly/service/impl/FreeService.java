package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.FreeDTO;
import poly.persistance.mapper.FreeMapper;
import poly.service.IFreeService;

@Service("FreeService")
public class FreeService implements IFreeService {

	@Resource(name = "FreeMapper")
	private FreeMapper freeMapper;

	@Override
	public int insertFreeInfo(FreeDTO fDTO) throws Exception {
		// 자유게시판 등록
		return freeMapper.insertFreeInfo(fDTO);
	}

	@Override
	public List<FreeDTO> getFreeList(int startNum, int endNum) throws Exception {
		// 자유게시판 목록
		return freeMapper.getFreeList(startNum, endNum);

	}

	@Override
	public FreeDTO getFreeDetail(int free_seq) throws Exception {
		// 자유게시판 상세보기
		return freeMapper.getFreeDetail(free_seq);
	}

	@Override
	public String getFreeCnt() throws Exception {
		// 자유게시판 개수
		return freeMapper.getFreeCnt();
	}

	@Override
	public int deleteFreeInfo(String seq) throws Exception {
		// 공지사항 삭제
		return freeMapper.deleteFreeInfo(seq);
	}

	@Override
	public List<FreeDTO> getMypage_freeList(String user_id, int startNum, int endNum) throws Exception {
		// 마이페이지 자유게시판 리스트
		return freeMapper.getMypage_freeList(user_id, startNum, endNum);
	}

	@Override
	public String getMypage_FreeCnt(String user_id) throws Exception {
		// 마이페이지 자유게시판 개수
		return freeMapper.getMypage_FreeCnt(user_id);
	}
	
	@Override
	public FreeDTO getMypage_freeDetail(int free_seq) throws Exception {
		// 자유게시판 상세보기
		return freeMapper.getMypage_freeDetail(free_seq);
	}

	@Override
	public int updateRead_cnt(int free_seq) throws Exception {
		// 자유게시판 상세보기
		return freeMapper.updateRead_cnt(free_seq);
	}

	@Override
	public int updateFree(FreeDTO fDTO) throws Exception {
		// 자유게시판 수정
		return freeMapper.updateFree(fDTO);
	}

	@Override
	public List<FreeDTO> getManager_freeList(String user_id, int startNum, int endNum) throws Exception {
		// 관리페이지 자유게시판 리스트
		return freeMapper.getManager_freeList(user_id, startNum, endNum);
	}

}
