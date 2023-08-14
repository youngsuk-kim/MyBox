package dev.bread.springboot.core.api.domain;

import dev.bread.springboot.core.api.support.utils.EncryptHelper;
import dev.bread.springboot.storage.db.core.UserEntity;
import dev.bread.springboot.storage.db.core.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserAppender {

	private final UserRepository userRepository;

	private final EncryptHelper encryptHelper;

	public UserAppender(UserRepository userRepository, EncryptHelper encryptHelper) {
		this.userRepository = userRepository;
		this.encryptHelper = encryptHelper;
	}

	@Transactional
	public Long save(String userId, String password, String userName) {
		UserEntity saveUser = userRepository.save(new UserEntity(userId, userName, encryptHelper.encrypt(password)));
		return saveUser.getId();
	}

}
