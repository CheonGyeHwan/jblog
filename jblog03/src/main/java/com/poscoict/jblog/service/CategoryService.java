package com.poscoict.jblog.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.UserVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public boolean join(UserVo userVo) {
		return categoryRepository.insertForJoin(userVo);
	}
	
	public List<CategoryVo> getCategory(Map<String, Object> map) {
		String id = (String)map.get("id");
		
		return categoryRepository.findById(id);
	}
	
}
