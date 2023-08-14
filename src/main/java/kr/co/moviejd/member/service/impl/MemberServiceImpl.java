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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String memberId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Member selectCheckLogin(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member selectOneById(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}
}
