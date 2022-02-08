package com.poscoict.jblog.service;

import java.util.HashMap;
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
	
	public List<PostVo> getPostList(String id, Long categoryNo) {
		if (categoryNo != null) {
			return postRepository.findByCategoryNo(categoryNo);
		} else {
			return postRepository.findById(id);
		}
	
	}
	
	public PostVo getPost(String id, Long categoryNo, Long postNo) {
		if (postNo != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", id);
			map.put("categoryNo", categoryNo);
			map.put("postNo", postNo);
			
			return postRepository.findByCategoryNoAndPostNo(map);
		}
		
		List<PostVo> postList = getPostList(id, categoryNo);
		if (postList.isEmpty()) {
			return null;
		}
		
		return postList.get(0);
	}
	
	public List<Map<String, Long>> getPostCount(String id) {
		return postRepository.getPostCount(id);
	}
	
	public boolean addPost(PostVo postVo) {
		return postRepository.addPost(postVo);
	}
	
}
