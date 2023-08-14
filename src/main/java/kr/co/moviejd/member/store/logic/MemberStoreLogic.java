package kr.co.moviejd.member.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.moviejd.member.domain.Member;
import kr.co.moviejd.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore{

	@Override
	public int insertMember(SqlSession sqlSession, Member member) {
		int result = sqlSession.insert("MemberMapper.insertMember", member);
		return result;
	}

	@Override
	public int updateMember(SqlSession sqlSession, Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(SqlSession sqlSession, String memberId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Member selectCheckLogin(SqlSession sqlSession, Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member selectOneById(SqlSession sqlSession, String memberId) {
		// TODO Auto-generated method stub
		return null;
	}
}
