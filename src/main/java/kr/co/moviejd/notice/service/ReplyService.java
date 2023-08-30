package kr.co.moviejd.notice.service;

import java.util.List;

import kr.co.moviejd.notice.domain.Reply;

public interface ReplyService {

	/**
	 * 댓글 등록 Service
	 * @param reply
	 * @return int
	 */
	int insertReply(Reply reply);
	/**
	 * 댓글 수정 Service
	 * @param reply
	 * @return
	 */
	int updateReply(Reply reply);
	/**
	 * 
	 * @return
	 */
	List<Reply> selectReplyList(int repNoticeNo);

}
