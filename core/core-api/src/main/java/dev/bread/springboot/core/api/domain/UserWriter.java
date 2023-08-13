package dev.bread.springboot.core.api.domain;

import dev.bread.springboot.core.api.support.util.EncryptHelper;
import dev.bread.springboot.storage.db.core.UserEntity;
import dev.bread.springboot.storage.db.core.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserWriter {

    private final UserRepository userRepository;

    private final EncryptHelper encryptHelper;

    public UserWriter(UserRepository userRepository, EncryptHelper encryptHelper) {
        this.userRepository = userRepository;
        this.encryptHelper = encryptHelper;
    }

    @Transactional
    public Long save(String userId, String password, String userName) {
        return userRepository.save(new UserEntity(userId, userName, encryptHelper.encrypt(password))).getId();
    }
}
