package kr.co.moviejd.notice.service.impl;

import java.util.List;

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
	SqlSession session;
	@Autowired
	NoticeStore nStore;
	
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
	public Notice selectNoticeByNo(Integer noticeNo) {
		Notice noticeOne = nStore.selectNoticeByNo(session, noticeNo);
		return noticeOne;
	}

	@Override
	public int insertNotice(Notice notice) {
		int result = nStore.insertNotice(session, notice);
		return result;
	}

	@Override
	public int updateNoticeByNo(Notice notice) {
		int result = nStore.updateNotice(session, notice);
		return result;
	}
}
