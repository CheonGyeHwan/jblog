package com.poscoict.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.poscoict.jblog.security.AdminAuth;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.CategoryService;
import com.poscoict.jblog.service.PostService;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;

	@RequestMapping("/{id}")
	public String main(@PathVariable("id") String id, Model model) {
		model.addAttribute("blog", blogService.getBlog(id));
		model.addAttribute("category", categoryService.getCategory(id));
		model.addAttribute("post", postService.getPost(id));
		model.addAttribute("view", postService.getView(id));
		
		return "blog/blog-main";
	}
	
	@AdminAuth
	@RequestMapping("/{id}/admin/basic")
	public String adminBasic(@PathVariable("id") String id) {
		return "blog/blog-admin-basic";
	}
}
