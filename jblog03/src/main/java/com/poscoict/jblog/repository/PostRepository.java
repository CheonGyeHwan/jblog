package com.poscoict.jblog.repository;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.poscoict.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> findById(String id) {
		return sqlSession.selectList("post.findById", id);
	}
	
	public List<PostVo> findByCategoryNo(Long categoryNo) {
		return sqlSession.selectList("post.findByCategoryNo", categoryNo);
	}
	
	public PostVo findByCategoryNoAndPostNo(Map<String, Object> map) {
		return sqlSession.selectOne("post.findByCategoryNoAndPostNo", map);
	}

}
