package kr.co.moviejd.notice.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviejd.notice.domain.Reply;
import kr.co.moviejd.notice.service.ReplyService;
import kr.co.moviejd.notice.store.ReplyStore;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyStore rStore;
	@Autowired
	private SqlSession session;
	
	@Override
	public int insertReply(Reply reply) {
		int result = rStore.insertReply(session, reply);
		return result;
	}

	@Override
	public int updateReply(Reply reply) {
		int result = rStore.updateReply(session, reply);
		return result;
	}

	@Override
	public List<Reply> selectReplyList(int repNoticeNo) {
		List<Reply> rList = rStore.selectReplyList(session, repNoticeNo);
		return rList;
	}

}
