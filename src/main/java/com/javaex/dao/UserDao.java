package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 회원가입 - INSERT
	public int joinUser(UserVo userVo) {
		System.out.println("UserDao: joinUser;");
		System.out.println(userVo);
		
		return sqlSession.insert("user.joinUser", userVo);
	}
	
	// 로그인 - SELECTONE - userInfo
	public UserVo loginUserInfo(UserVo userVo) {
		System.out.println("UserDao: loginUserInfo;");
		
		return sqlSession.selectOne("user.loginUserInfo", userVo);
	}

}
