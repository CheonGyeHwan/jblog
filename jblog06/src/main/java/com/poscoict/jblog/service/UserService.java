package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poscoict.jblog.repository.UserRepository;
import com.poscoict.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public boolean join(UserVo userVo) {
		if (userRepository.findById(userVo) != null) {
			return false;
		}
		return userRepository.insert(userVo);
	}
	
	public UserVo getUser(String id, String password) {
		UserVo userVo = new UserVo();
		userVo.setId(id);
		userVo.setPassword(password);
		
		return userRepository.findByIdAndPassword(userVo);
	}
	
}
