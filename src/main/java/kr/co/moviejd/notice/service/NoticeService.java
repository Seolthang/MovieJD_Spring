package kr.co.moviejd.notice.service;

import java.util.List;

import kr.co.moviejd.notice.domain.Notice;
import kr.co.moviejd.notice.domain.PageInfo;

public interface NoticeService {
	/**
	 * 단체 게시물 갯수 Service
	 * @return
	 */
	int getListCount();
	/**
	 * 게시글 전체 조회 Service
	 * @param pInfo
	 * @return
	 */
	List<Notice> selectNoticeList(PageInfo pInfo);
	/**
	 * 게시글 상세 조회 Service
	 * @param noticeNo
	 * @return
	 */
	Notice selectNoticeByNo(Integer noticeNo);
	/**
	 * 게시글 작성 Service
	 * @param notice
	 * @return
	 */
	int insertNotice(Notice notice);
	/**
	 * 게시글 수정 Service
	 * @param notice
	 * @return
	 */
	int updateNoticeByNo(Notice notice);

}
