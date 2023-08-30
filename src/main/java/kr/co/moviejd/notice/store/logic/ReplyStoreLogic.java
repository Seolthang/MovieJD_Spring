package kr.co.moviejd.notice.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.moviejd.notice.domain.Reply;
import kr.co.moviejd.notice.store.ReplyStore;

@Repository
public class ReplyStoreLogic implements ReplyStore{

	@Override
	public int insertReply(SqlSession session, Reply reply) {
		int result = session.insert("ReplyMapper.insertReply", reply);
		return result;
	}

	@Override
	public int updateReply(SqlSession session, Reply reply) {
		int result = session.update("ReplyMapper.updateReply", reply);
		return result;
	}

	@Override
	public List<Reply> selectReplyList(SqlSession session, int repNoticeNo) {
		List<Reply> rList = session.selectList("ReplyMapper.selectReplyList", repNoticeNo);
		return rList;
	}

}
