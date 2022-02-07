package com.poscoict.jblog.controller;

import java.util.HashMap;
import java.util.Map;
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
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if (pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("categoryNo", categoryNo);
		map.put("postNo", postNo);
		
		model.addAttribute("blogVo", blogService.getBlog(map));
		model.addAttribute("categoryVo", categoryService.getCategory(map));
		model.addAttribute("postList", postService.getPostList(map));
		model.addAttribute("postVo", postService.getPost(map));
		
		return "blog/blog-main";
	}
	
	@AdminAuth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id) {
		return "blog/blog-admin-basic";
	}
	
	@AdminAuth
	@RequestMapping(value="/admin/basic/update", method=RequestMethod.POST)
	public String blogUpdate(BlogVo blogVo, @RequestParam(value="newLogo") MultipartFile newLogo) {
		blogService.update(blogVo, newLogo);
		return "redirect:/" + blogVo.getUserId();
	}
	
}
