package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVO;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void join(UserVO vo) {
		userRepository.insert(vo);
	}

	public UserVO getUser(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	public UserVO getUser(Long no) {
		return userRepository.findEmailByNo(no);
	}

	public void updateUser(UserVO vo) {
		userRepository.updateByNo(vo);
		
	}

	public UserVO getUser(String email) {
		return userRepository.findByEmail(email);
	}

}
