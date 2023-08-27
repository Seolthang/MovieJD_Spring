package kr.co.moviejd.notice.service;

import java.util.List;
import java.util.Map;

import kr.co.moviejd.notice.domain.Notice;
import kr.co.moviejd.notice.domain.PageInfo;

public interface NoticeService {

	int getListCount();

	List<Notice> selectNoticeList(PageInfo pInfo);

	int insertNotice(Notice notice);

	int getListCount(Map<String, String> paramMap);

	List<Notice> searchNoticeByKeyword(PageInfo pInfo, Map<String, String> paramMap);

	Notice selectNoticeByNo(Integer noticeNo);

}
