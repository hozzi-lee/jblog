package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int writePost(PostVo postVo) {
		System.out.println("PostDao: writePost;");
		int count = sqlSession.insert("post.writePost", postVo);
		
		return count;
	}
	
	public List<PostVo> postList(int cateNo) {
		System.out.println("postDao: postList;");
		System.out.println(cateNo);
		
		List<PostVo> postList = sqlSession.selectList("post.postList", cateNo);
		
		System.out.println(postList);
		
		return postList;
	}
	
	public PostVo getPost(PostVo postVo) {
		System.out.println("postDao: getPost;");
		System.out.println(postVo);
		
		PostVo getPost = sqlSession.selectOne("post.getPost", postVo);
		
		System.out.println(getPost);
		
		return getPost;
	}
	
}
