package com.example.movieedu.model.dao;

import com.example.movieedu.model.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class memberDAO{
	@Autowired
	SqlSession session = null;

	public MemberVO login(MemberVO vo) {
		String statement = "resource.MemberMapper.Login";
		MemberVO user = session.selectOne(statement,vo);
		System.out.println("member vo (dao) : "+ user);

		return user;
	}
	
	public boolean register(MemberVO vo) {
		String statement = "resource.MemberMapper.Register";
		if(session.insert(statement, vo) != 1){
			return false;
		}
		return true;
	}

	public boolean delete(String nick){
		String statement = "resource.MemberMapper.Delete";
		int num = session.delete(statement, nick);
		return num >= 1;
	}
}
