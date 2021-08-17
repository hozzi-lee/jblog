package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int createDefaultCategory(String id) {
		int count = sqlSession.insert("category.createDefaultCategory", id);
		
		return count;
	}
	
	/*
	public List<CategoryVo> cateNoList(String id) {
		List<CategoryVo> cateNoList = sqlSession.selectList("category.cateNoList", id);
		
		return cateNoList;
	}
	*/
	
	public List<CategoryVo> categoryList(String id) {
		System.out.println("categoryDao: categoryList;");
		List<CategoryVo> categoryList = sqlSession.selectList("category.categoryList", id);
		System.out.println(categoryList);
		
		return categoryList;
	}
	
	public int addCategoryKey(CategoryVo cateVo) {
		System.out.println("categoryDao: addCategoryKey;");
		
		int count = sqlSession.insert("category.addCategoryKey", cateVo);
		
		return count;
	}
	
	public CategoryVo addCategoryInfo(CategoryVo cateVo) {
		System.out.println("categoryDao: addCategoryInfo;");
		CategoryVo addCategoryInfo = sqlSession.selectOne("category.addCategoryInfo", cateVo);
		
		return addCategoryInfo;
	}
	
	public int deleteCategory(CategoryVo cateVo) {
		int count = sqlSession.delete("category.deleteCategory", cateVo);
		
		return count;
	}
	
	
	public List<CategoryVo> cateOption(String id) {
		System.out.println("CategoryDao: cateOption");
		System.out.println(id);
		
		List<CategoryVo> cateOption = sqlSession.selectList("category.cateOption", id);
		
		return cateOption;
	}
}
