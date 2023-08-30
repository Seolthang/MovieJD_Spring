package kr.co.moviejd.notice.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.moviejd.notice.domain.Reply;

public interface ReplyStore {

	/**
	 * 댓글 등록 Store
	 * @param session
	 * @param reply
	 * @return
	 */
	int insertReply(SqlSession session, Reply reply);
	/**
	 * 댓글 수정 Store
	 * @param session
	 * @param reply
	 * @return
	 */
	int updateReply(SqlSession session, Reply reply);
	/**
	 * 
	 * @param session
	 * @return
	 */
	List<Reply> selectReplyList(SqlSession session, int repNoticeNo);

}
