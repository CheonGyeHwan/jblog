package com.poscoict.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.UserVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(UserVo userVo) {
		return sqlSession.insert("blog.insert", userVo) == 1;
	}
	
	public BlogVo findById(String id) {
		return sqlSession.selectOne("blog.findById", id);
	}
	
	public boolean update(BlogVo blogVo) {
		return sqlSession.update("blog.update", blogVo) == 1;
	}
	
}
