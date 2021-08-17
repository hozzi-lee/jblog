package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;

	// 회원가입시 블로그 생성
	public int createBlog(UserVo userVo) {
		System.out.println("BlogDao: createBlog;");
		System.out.println(userVo);

		return sqlSession.insert("blog.createBlog", userVo);
	}

	// 주소창에 다른 사람 블로그 아이디 입력 했을때 해당 아이디 정보 적용
	public BlogVo blogInfo(String id) {
		System.out.println("BlogDao: blogInfo;");
		BlogVo info = sqlSession.selectOne("blog.blogInfo", id);

		return info;
	}
	
	// 블로그 로고 이미지 변경
	public int uploadLogoFile(BlogVo uploadInfo) {
		System.out.println("BlogDao: uploadLogoFile;");
		System.out.println(uploadInfo);
		
		return sqlSession.update("blog.uploadLogoFile", uploadInfo);
	}
	
}
