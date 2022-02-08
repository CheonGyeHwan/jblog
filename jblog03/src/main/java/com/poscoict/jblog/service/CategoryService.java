package com.poscoict.jblog.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.repository.PostRepository;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.UserVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public boolean join(UserVo userVo) {
		return categoryRepository.insertForJoin(userVo);
	}
	
	public List<CategoryVo> getCategory(String id) {
		return categoryRepository.findById(id);
	}
	
	public boolean addCategory(CategoryVo categoryVo) {
		return categoryRepository.addCategory(categoryVo);
	}
	
	public boolean deleteCategory(String id, Long no) {
		List<Map<String, Long>> postCountList = postRepository.getPostCount(id); 
		
		for(Map<String, Long> map : postCountList) {
			String categoryNo = String.valueOf(map.get("categoryNo"));
			String count = String.valueOf(map.get("count"));
			
			if (no.toString().equals(categoryNo) && "0".equals(count)) {
				return categoryRepository.deleteCategory(no);
			}
		}
		
		return false;
	}
	
}
