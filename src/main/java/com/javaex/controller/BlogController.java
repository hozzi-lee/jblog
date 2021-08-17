package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
public class BlogController {

	@Autowired
	private BlogService blogService;

	// 블로그 메인
	@RequestMapping("")
	public String blogMain(Model model,
							@PathVariable("id") String id,
							@RequestParam(value = "selectCateNo", required = false, defaultValue = "0") int cateNo,
							@RequestParam(value = "selectPostNo", required = false, defaultValue = "0") int postNo) {

		System.out.println("BlogController: blogMain;");
		System.out.println(id);
		System.out.println(cateNo);
		System.out.println(postNo);

		Map<String, Object> blogMainMap = blogService.blogMainMap(id, cateNo,postNo);
		model.addAttribute("blogMainMap", blogMainMap); // Map 사용

		BlogVo blogInfo = blogService.blogInfo(id);
		model.addAttribute("blogInfo", blogInfo); // blog-includes

		return "/blog/blog-main";
	}

	// 블로그 기본설정
	@RequestMapping("/admin/basic")
	public String adminBasci(Model model, HttpSession session) {
		System.out.println("BlogController: adminBasic;");

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String authUserID = authUser.getId();
		BlogVo blogInfo = blogService.blogInfo(authUserID);
		model.addAttribute("blogInfo", blogInfo);

		System.out.println(authUser);
		System.out.println(blogInfo);

		return "/blog/admin/blog-admin-basic";
	}

	// 블로그 기본설정 - 블로그 타이틀 / 로고 이미지 변경 (Ajax 시도해보기)
	@RequestMapping("/upload")
	public String modifyAdminBasic(Model model, @RequestParam("file") MultipartFile file, @PathVariable("id") String id,
			@RequestParam("blogTitle") String blogTitle) {
		System.out.println("BlogController: modifyAdminBasic;");
		System.out.println(id);
		System.out.println(file.getOriginalFilename());

		blogService.uploadLogoFile(file, id, blogTitle);

		return "redirect:/{id}/admin/basic";
	}

	// 블로그 카테고리 설정
	@RequestMapping("/admin/category")
	public String adminCategory(Model model, HttpSession session) {
		System.out.println("BlogController: category;");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String authUserID = authUser.getId();
		BlogVo blogInfo = blogService.blogInfo(authUserID);
		model.addAttribute("blogInfo", blogInfo);

		return "/blog/admin/blog-admin-cate";
	}

	// 블로그 카테고리설정 - 리스트
	@ResponseBody
	@RequestMapping("/admin/category/list")
	public List<CategoryVo> categoryList(HttpSession session) {
		System.out.println("BlogController: categoryList;");

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String id = authUser.getId();

		System.out.println(id);

		List<CategoryVo> categoryList = blogService.categoryList(id);

		return categoryList;
	}

	// 블로그 카테고리설정 - 추가
	@ResponseBody
	@RequestMapping("/admin/category/add")
	public CategoryVo addCategory(HttpSession session, @ModelAttribute CategoryVo cateVo) {
		System.out.println("BlogController: addCategory;");

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String id = authUser.getId();
		cateVo.setId(id);

		System.out.println(cateVo);

		CategoryVo addCategory = blogService.addCategory(cateVo);

		return addCategory;
	}

	// 블로그 카테고리설정 - 삭제
	@ResponseBody
	@RequestMapping("/admin/category/delete")
	public int deleteCategory(HttpSession session, @ModelAttribute CategoryVo cateVo) {
		System.out.println("BlogController: deleteCategory");
		System.out.println(cateVo);

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String id = authUser.getId();
		cateVo.setId(id);

		System.out.println(cateVo);

		int count = blogService.deleteCategory(cateVo);

		return count;
	}

	// 블로그 포스트 작성 폼
	@RequestMapping("/admin/writeForm")
	public String writeForm(Model model, HttpSession session) {
		System.out.println("BlogController: writeForm;");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String authUserID = authUser.getId();
		BlogVo blogInfo = blogService.blogInfo(authUserID);
		model.addAttribute("blogInfo", blogInfo);

		return "/blog/admin/blog-admin-write";
	}

	// 블로그 포스트 작성 카테고리 리스트
	@ResponseBody
	@RequestMapping("admin/writeForm/cateOptionList")
	public List<CategoryVo> cateOption(HttpSession session) {
		System.out.println("BlogController: cateOption;");

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String id = authUser.getId();

		System.out.println(id);

		List<CategoryVo> cateOption = blogService.cateOption(id);

		System.out.println(cateOption);

		return cateOption;
	}

	// 블로그 포스트 작성
	@RequestMapping("/admin/write")
	public String write(@ModelAttribute PostVo postVo) {
		System.out.println("BlogController: write;");
		System.out.println(postVo);

		int count = blogService.writePost(postVo);
		System.out.println(count);

		return "redirect:/{id}/admin/writeForm";
	}

}
