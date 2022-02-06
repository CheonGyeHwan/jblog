package com.poscoict.jblog.service;

import java.util.List;
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
	
	public List<CategoryVo> getCategory(String id) {
		return categoryRepository.findById(id);
	}
	
}
