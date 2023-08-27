package kr.co.moviejd.notice.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviejd.notice.domain.Notice;
import kr.co.moviejd.notice.domain.PageInfo;
import kr.co.moviejd.notice.service.NoticeService;
import kr.co.moviejd.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private SqlSession session;
	@Autowired
	private NoticeStore nStore;

	@Override
	public int getListCount() {
		int result = nStore.selectListCount(session);
		return result;
	}

	@Override
	public List<Notice> selectNoticeList(PageInfo pInfo) {
		List<Notice> nList = nStore.selectNoticeList(session, pInfo);
		return nList;
	}

	@Override
	public int insertNotice(Notice notice) {
		int result = nStore.insertNotice(session, notice);
		return result;
	}

	@Override
	public int getListCount(Map<String, String> paramMap) {
		int result = nStore.selectListCount(session, paramMap);
		return result;
	}

	@Override
	public List<Notice> searchNoticeByKeyword(PageInfo pInfo, Map<String, String> paramMap) {
		List<Notice> searchList = nStore.selectNoticeByKeyword(session, pInfo, paramMap);
		return searchList;
	}

	@Override
	public Notice selectNoticeByNo(Integer noticeNo) {
		Notice noticeOne = nStore.selectNoticeByNo(session, noticeNo);
		return noticeOne;
	}
	
}
