package kr.co.moviejd.notice.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.moviejd.notice.domain.Notice;
import kr.co.moviejd.notice.domain.PageInfo;

public interface NoticeStore {

	/**
	 * 단체 게시물 갯수 Store
	 * @param session
	 * @return
	 */
	int selectListCount(SqlSession session);
	/**
	 * 전체 게시글 조회 Store
	 * @param session
	 * @param pInfo
	 * @return
	 */
	List<Notice> selectNoticeList(SqlSession session, PageInfo pInfo);
	/**
	 * 게시글 상세 조회 Store
	 * @param session
	 * @param noticeNo
	 * @return
	 */
	Notice selectNoticeByNo(SqlSession session, Integer noticeNo);
	/**
	 * 게시글 작성 Store
	 * @param session
	 * @param notice
	 * @return
	 */
	int insertNotice(SqlSession session, Notice notice);
	/**
	 * 게시글 수정 Store
	 * @param session
	 * @param notice
	 * @return
	 */
	int updateNotice(SqlSession session, Notice notice);

}
