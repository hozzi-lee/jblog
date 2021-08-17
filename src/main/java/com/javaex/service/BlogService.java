package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private PostDao postDao;

	// 블로그 메인 - 주소창에 다른 사람 블로그 아이디 입력 했을때 해당 아이디 정보 적용
	public Map<String, Object> blogMainMap(String id, int cateNo, int postNo) {
		System.out.println("BlogService: blogMainMap;");

		List<CategoryVo> categoryList = categoryDao.categoryList(id);
		
		System.out.println(categoryList);
		
		if (cateNo == 0) {
			cateNo = categoryList.get(0).getCateNo();
		}
		
		System.out.println(cateNo);
		
		List<PostVo> postList = postDao.postList(cateNo);
		
		System.out.println(postList);
		
		if (postNo == 0) {
			postNo = postList.get(0).getPostNo();
		}
		
		System.out.println(postNo);
		
		PostVo postVo = new PostVo(postNo, cateNo);
		PostVo getPost = postDao.getPost(postVo);
		
		Map<String, Object> blogMainMap = new HashMap<String, Object>();
		blogMainMap.put("categoryList", categoryList);
		blogMainMap.put("getPost", getPost);
		blogMainMap.put("postList", postList);

		return blogMainMap;
	}

	// 블로그 헤더 정보
	public BlogVo blogInfo(String id) {
		System.out.println("BlogService: blogInfo;");
		BlogVo blogInfo = blogDao.blogInfo(id);

		return blogInfo;
	}

	// 블로고 로고 이미지 변경
	public int uploadLogoFile(MultipartFile file, String id, String blogTitle) {
		System.out.println("BlogService: uploadLogoFile;");
		System.out.println(file.getOriginalFilename());

		BlogVo uploadInfo = new BlogVo();

		// 파일 저장 위치(path)
		String saveDirectory = "S:/javaStudy/jblog/upload/";

		// 파일 원본 이름
		String orgName = file.getOriginalFilename();

		if ("".equals(orgName)) {
			uploadInfo = new BlogVo(id, blogTitle);
		} else {

			// 확장자 발췌
			String exName = orgName.substring(orgName.lastIndexOf("."));

			// 저장할때 파일 이름(원본파일 중복)
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

			// 파일 저장 (path + name)
			String filePath = saveDirectory + saveName;

			try {
				byte[] fileData = file.getBytes();
				BufferedOutputStream bfOut = new BufferedOutputStream(new FileOutputStream(filePath));

				bfOut.write(fileData);
				bfOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			uploadInfo = new BlogVo(id, blogTitle, saveName);
			System.out.println(uploadInfo);
		}
		// DB 저장
		return blogDao.uploadLogoFile(uploadInfo);
	}

	public List<CategoryVo> categoryList(String id) {
		System.out.println("BlogService: cateListMap;");
//		List<CategoryVo> cateNoList = categoryDao.cateNoList(id);
		List<CategoryVo> categoryList = categoryDao.categoryList(id);

		/*
		 * Map<String, Object> cateListMap = new HashMap<String, Object>();
		 * cateListMap.put("cateNoList", cateNoList); cateListMap.put("categoryList",
		 * categoryList); System.out.println(cateListMap);
		 */

		return categoryList;
	}

	public CategoryVo addCategory(CategoryVo cateVo) {
		System.out.println("BlogService: addCategory;");
		System.out.println(cateVo);

		categoryDao.addCategoryKey(cateVo);

		System.out.println(cateVo);

		CategoryVo addCategoryInfo = categoryDao.addCategoryInfo(cateVo);

		return addCategoryInfo;
	}

	public int deleteCategory(CategoryVo cateVo) {
		int count = categoryDao.deleteCategory(cateVo);

		return count;
	}

	public List<CategoryVo> cateOption(String id) {
		System.out.println("BlogService: cateOption");
		System.out.println(id);

		List<CategoryVo> cateOption = categoryDao.cateOption(id);

		return cateOption;
	}

	public int writePost(PostVo postVo) {
		System.out.println("BlogService: writePost;");
		int count = postDao.writePost(postVo);

		return count;
	}

}
