package dev.bread.springboot.core.api.domain;

import dev.bread.springboot.core.api.controller.v1.request.CreateUserRequest;
import dev.bread.springboot.core.api.support.utils.EncryptHelper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserReader userReader;

	private final UserAppender userAppender;

	private final EncryptHelper encryptHelper;

	public UserService(UserReader userReader, UserAppender userAppender, EncryptHelper encryptHelper) {
		this.userReader = userReader;
		this.userAppender = userAppender;
		this.encryptHelper = encryptHelper;
	}

	public UserResult findUserByUserId(String userId) {
		return userReader.read(userId);
	}

	public boolean checkPassword(String userId, String password) {
		UserResult userResult = userReader.read(userId);

		return encryptHelper.isMatch(password, userResult.password());
	}

	public Long createUser(CreateUserRequest createUserRequest) {
		return userAppender.save(createUserRequest.userId(), createUserRequest.password(), createUserRequest.userName());
	}

}
