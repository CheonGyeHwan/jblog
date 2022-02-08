package com.poscoict.jblog.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.UserVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insertForJoin(UserVo userVo) {
		return sqlSession.insert("category.insertForJoin", userVo) == 1;
	}
	
	public List<CategoryVo> findById(String id) {
		return sqlSession.selectList("category.findById", id);
	}
	
	public boolean addCategory(CategoryVo categoryVo) {
		return sqlSession.update("category.addCategory", categoryVo) == 1;
	}
	
	public boolean deleteCategory(Long no) {
		return sqlSession.update("category.deleteCategory", no) == 1;
	}
	
}
