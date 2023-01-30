package model.dao;

import model.vo.memberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class memberDAO{
	@Autowired
	SqlSession session;

	public memberVO login(memberVO vo) {
		String statement = "Member.Login";
		memberVO user = session.selectOne(statement,vo);
		return user;
	}
	
	public boolean register(memberVO vo) {
		String statement = "Member.Register";
		if(session.insert(statement, vo) != 1){
			return false;
		}
		return true;
	}

	public boolean delete(String nick){
		System.out.println(nick);
		String statement = "Member.Delete";
		int num = session.delete(statement, nick);
		System.out.println(num);
		statement = "Member.DeleteSecond";
		int num2 = session.delete(statement,nick);
		System.out.println(num2);
		if(num2 != -1){
			return num >= 1;
		}
		return false;
	}
}
