package com.poscoict.jblog.controller;

import java.util.Optional;
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
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;

	@RequestMapping({"", "/{pathNo1}", "/pathNo1/pathNo2"})
	public String main(@PathVariable("id") String id,
					   @PathVariable("pathNo1") Optional<Long> pathNo1,
					   @PathVariable("pathNo2") Optional<Long> pathNo2,
					   Model model) {
		// Map으로 저장해서 select 한번에 처리(Service에서) -- 수정
		// Optional --> PathVariable은 데이터 없으면 Null이 아니라 에러 발생 => Optional 사용
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if (pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		model.addAttribute("blog", blogService.getBlog(id));
		model.addAttribute("category", categoryService.getCategory(id));
		model.addAttribute("post", postService.getPost(id));
		model.addAttribute("view", postService.getView(id));
		
		return "blog/blog-main";
	}
	
	@AdminAuth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id) {
		return "blog/blog-admin-basic";
	}
}
