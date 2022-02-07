package com.poscoict.jblog.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poscoict.jblog.repository.PostRepository;
import com.poscoict.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	public List<PostVo> getPostList(Map<String, Object> map) {
		String id = (String)map.get("id");
		Long categoryNo = (Long)map.get("categoryNo");
		
		if (categoryNo != null) {
			return postRepository.findByCategoryNo(categoryNo);
		} else {
			return postRepository.findById(id);
		}
	
	}
	
	public PostVo getPost(Map<String, Object> map) {
		if (map.get("postNo") != null) {
			return postRepository.findByCategoryNoAndPostNo(map);
		}
		
		List<PostVo> postList = getPostList(map);
		if (postList.isEmpty()) {
			return null;
		}
		
		return postList.get(0);
	}
	
}
