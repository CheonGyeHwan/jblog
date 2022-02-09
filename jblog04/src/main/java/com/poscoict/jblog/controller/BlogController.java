package com.poscoict.jblog.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.poscoict.jblog.security.AdminAuth;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.CategoryService;
import com.poscoict.jblog.service.PostService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;

	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String main(@PathVariable("id") String id,
					   @PathVariable("pathNo1") Optional<Long> pathNo1,
					   @PathVariable("pathNo2") Optional<Long> pathNo2,
					   Model model) {
		Long categoryNo = null;
		Long postNo = null;
		
		if (pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		model.addAttribute("blogVo", blogService.getBlog(id));
		model.addAttribute("categoryVo", categoryService.getCategory(id));
		model.addAttribute("postList", postService.getPostList(id, categoryNo));
		model.addAttribute("postVo", postService.getPost(id, categoryNo, postNo));
		
		return "blog/blog-main";
	}
	
	@AdminAuth
	@RequestMapping(value="/admin/basic", method=RequestMethod.GET)
	public String adminBasic(@PathVariable("id") String id) {
		return "blog/blog-admin-basic";
	}
	
	@AdminAuth
	@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
	public String updateBlog(BlogVo blogVo, @RequestParam(value="newLogo") MultipartFile newLogo) {
		blogService.update(blogVo, newLogo);
		return "redirect:/" + blogVo.getUserId();
	}
	
	@AdminAuth
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id, Model model) {
		model.addAttribute("categoryVo", categoryService.getCategory(id));
		model.addAttribute("postCount", postService.getPostCount(id));
		return "blog/blog-admin-category";
	}
	
	@AdminAuth
	@RequestMapping(value="/admin/category/add", method=RequestMethod.POST)
	public String addCategory(@PathVariable("id") String id, CategoryVo categoryVo) {
		categoryService.addCategory(categoryVo);
		return "redirect:/" + id + "/admin/category";
	}
	
	@AdminAuth
	@RequestMapping("/admin/category/delete/{no}")
	public String deleteCategory(@PathVariable("id") String id, @PathVariable("no") Long no) {
		categoryService.deleteCategory(id, no);
		return "redirect:/" + id + "/admin/category";
	}
	
	@AdminAuth
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String adminWrite(@PathVariable("id") String id, Model model) {
		model.addAttribute("categoryVo", categoryService.getCategory(id));
		return "blog/blog-admin-write";
	}
	
	@AdminAuth
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id, PostVo postVo, Model model) {
		postService.addPost(postVo);
		return "redirect:/" + id;
	}
	
}
