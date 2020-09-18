package poly.dto;

public class FreeDTO {

	private String free_seq; // 기본키, 순번
	private String title; // 제목
	private String free_yn; // 공지글 여부
	private String contents; // 글 내용
	private String user_id; // 작성자
	private String read_cnt; // 조회수
	private String reg_id; // 등록자 아이디
	private String reg_dt; // 등록일
	private String chg_id; // 수정자 아이디
	private String chg_dt; // 수정일
	private String user_name; // 등록자명

	private String sidoCode; // 지역
	private String sggCode; // 행정구역
	private String type; // 유형별

	public String getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}

	public String getSggCode() {
		return sggCode;
	}

	public void setSggCode(String sggCode) {
		this.sggCode = sggCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFree_seq() {
		return free_seq;
	}

	public void setFree_seq(String free_seq) {
		this.free_seq = free_seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFree_yn() {
		return free_yn;
	}

	public void setFree_yn(String free_yn) {
		this.free_yn = free_yn;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getRead_cnt() {
		return read_cnt;
	}

	public void setRead_cnt(String read_cnt) {
		this.read_cnt = read_cnt;
	}

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	public String getChg_id() {
		return chg_id;
	}

	public void setChg_id(String chg_id) {
		this.chg_id = chg_id;
	}

	public String getChg_dt() {
		return chg_dt;
	}

	public void setChg_dt(String chg_dt) {
		this.chg_dt = chg_dt;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}
