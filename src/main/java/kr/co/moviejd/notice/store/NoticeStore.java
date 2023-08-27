package kr.co.moviejd.notice.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.co.moviejd.notice.domain.Notice;
import kr.co.moviejd.notice.domain.PageInfo;

public interface NoticeStore {

	int insertNotice(SqlSession session, Notice notice);
	
	int selectListCount(SqlSession session);

	List<Notice> selectNoticeList(SqlSession session, PageInfo pInfo);


	int selectListCount(SqlSession session, Map<String, String> paramMap);

	List<Notice> selectNoticeByKeyword(SqlSession session, PageInfo pInfo, Map<String, String> paramMap);

	Notice selectNoticeByNo(SqlSession session, Integer noticeNo);

}
