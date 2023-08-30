package kr.co.moviejd.notice.domain;

import java.util.Date;

public class Notice {

	private int noticeNo;
	private String noticeSubject;
	private String noticeContent;
	private String noticeWriter;
	private String noticeFilename;
	private String noticeFileRename;
	private String noticeFilepath;
	private long noticeFilelength;
	private int noticeCount;
	private Date nCreateDate;
	private Date nUpdateDate;
	private char nStatus;
	
	public Notice() {
		super();
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeSubject() {
		return noticeSubject;
	}
	public void setNoticeSubject(String noticeSubject) {
		this.noticeSubject = noticeSubject;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeWriter() {
		return noticeWriter;
	}
	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}
	public String getNoticeFilename() {
		return noticeFilename;
	}
	public void setNoticeFilename(String noticeFilename) {
		this.noticeFilename = noticeFilename;
	}
	public String getNoticeFileRename() {
		return noticeFileRename;
	}
	public void setNoticeFileRename(String noticeFileRename) {
		this.noticeFileRename = noticeFileRename;
	}
	public String getNoticeFilepath() {
		return noticeFilepath;
	}
	public void setNoticeFilepath(String noticeFilepath) {
		this.noticeFilepath = noticeFilepath;
	}
	public long getNoticeFilelength() {
		return noticeFilelength;
	}
	public void setNoticeFilelength(long noticeFilelength) {
		this.noticeFilelength = noticeFilelength;
	}
	public int getNoticeCount() {
		return noticeCount;
	}
	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}
	public Date getnCreateDate() {
		return nCreateDate;
	}
	public void setnCreateDate(Date nCreateDate) {
		this.nCreateDate = nCreateDate;
	}
	public Date getnUpdateDate() {
		return nUpdateDate;
	}
	public void setnUpdateDate(Date nUpdateDate) {
		this.nUpdateDate = nUpdateDate;
	}
	public char getnStatus() {
		return nStatus;
	}
	public void setnStatus(char nStatus) {
		this.nStatus = nStatus;
	}
	
	@Override
	public String toString() {
		return "게시글 [번호=" + noticeNo + ", 제목=" + noticeSubject + ", 내용=" + noticeContent
				+ ", 작성자=" + noticeWriter + ", 파일이름=" + noticeFilename + ", 파일리네임="
				+ noticeFileRename + ", 파일경로=" + noticeFilepath + ", 파일크기=" + noticeFilelength
				+ ", 조회수=" + noticeCount + ", 작성일=" + nCreateDate + ", 수정일=" + nUpdateDate
				+ ", 수정여부=" + nStatus + "]";
	}
}
