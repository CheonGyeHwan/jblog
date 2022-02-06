package com.poscoict.jblog.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poscoict.jblog.repository.PostRepository;
import com.poscoict.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	public List<PostVo> getPost(String id) {
		return postRepository.findById(id);
	}
	
	public PostVo getView(String id) {
		List<PostVo> postList = getPost(id);
		
		if (postList.isEmpty()) {
			return null;
		}
		
		return postList.get(0);
	}
	
}
