package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	// 회원가입 / 블로그 생성(+미분류 카테고리) - INSERT
	public int joinUser(UserVo userVo) {
		System.out.println("UserService: joinUser;");
		System.out.println(userVo);
		
		String id = userVo.getId();
		
		userDao.joinUser(userVo);
		blogDao.createBlog(userVo);
		return categoryDao.createDefaultCategory(id);
	}
	
	// 로그인 - SELECTONE - userInfo / blogInfo
	public UserVo loginUserInfo(UserVo userVo) {
		System.out.println("UserService: loginUserInfo;");

		return userDao.loginUserInfo(userVo);
	}

}
