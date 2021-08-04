package com.ssafy.api.service;

import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public User getUser(String id) {
		return userRepository.findById(id)
			       .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}
}
