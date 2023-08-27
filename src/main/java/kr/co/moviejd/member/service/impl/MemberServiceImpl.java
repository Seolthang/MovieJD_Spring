package kr.co.moviejd.member.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviejd.member.domain.Member;
import kr.co.moviejd.member.service.MemberService;
import kr.co.moviejd.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberStore mStore;
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertMember(Member member) {
		int result = mStore.insertMember(sqlSession, member);
		return result;
	}

	@Override
	public int updateMember(Member member) {
		int result = mStore.updateMember(sqlSession, member);
		return result;
	}

	@Override
	public int deleteMember(String memberId) {
		int result = mStore.deleteMember(sqlSession, memberId);
		return result;
	}

	@Override
	public int selectCheckLogin(Member member) {
		int result = mStore.selectCheckLogin(sqlSession, member);
		return result;
	}

	@Override
	public Member selectOneById(String memberId) {
		Member member = mStore.selectOneById(sqlSession, memberId);
		return member;
	}
}
