package poly.dto;

public class R_CommentDTO {

	private String r_seq;
	private String board_seq;
	private String content;
	private String writer;
	private String reg_dt;

	public String getR_seq() {
		return r_seq;
	}

	public void setR_seq(String r_seq) {
		this.r_seq = r_seq;
	}

	public String getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(String board_seq) {
		this.board_seq = board_seq;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

}
