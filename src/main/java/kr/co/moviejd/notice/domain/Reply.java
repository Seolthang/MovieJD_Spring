package kr.co.moviejd.notice.domain;

import java.sql.Date;

public class Reply {
	private int replyNo;
	private int repNoticeNo;
	private String repNoticeContent;
	private String repNoticeWriter;
	private Date repCreateDate;
	private Date repUpdateDate;
	private char repUpdateYn;
	private char repStatus;
	
	public Reply() {
		super();
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public int getRepNoticeNo() {
		return repNoticeNo;
	}
	public void setRepNoticeNo(int repNoticeNo) {
		this.repNoticeNo = repNoticeNo;
	}
	public String getRepNoticeContent() {
		return repNoticeContent;
	}
	public void setRepNoticeContent(String repNoticeContent) {
		this.repNoticeContent = repNoticeContent;
	}
	public String getRepNoticeWriter() {
		return repNoticeWriter;
	}
	public void setRepNoticeWriter(String repNoticeWriter) {
		this.repNoticeWriter = repNoticeWriter;
	}
	public Date getRepCreateDate() {
		return repCreateDate;
	}
	public void setRepCreateDate(Date repCreateDate) {
		this.repCreateDate = repCreateDate;
	}
	public Date getRepUpdateDate() {
		return repUpdateDate;
	}
	public void setRepUpdateDate(Date repUpdateDate) {
		this.repUpdateDate = repUpdateDate;
	}
	public char getRepUpdateYn() {
		return repUpdateYn;
	}
	public void setRepUpdateYn(char repUpdateYn) {
		this.repUpdateYn = repUpdateYn;
	}
	public char getRepStatus() {
		return repStatus;
	}
	public void setRepStatus(char repStatus) {
		this.repStatus = repStatus;
	}
	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", repNoticeNo=" + repNoticeNo + ", repNoticeContent=" + repNoticeContent
				+ ", repNoticeWriter=" + repNoticeWriter + ", repCreateDate=" + repCreateDate + ", repUpdateDate="
				+ repUpdateDate + ", repUpdateYn=" + repUpdateYn + ", repStatus=" + repStatus + "]";
	}
}
