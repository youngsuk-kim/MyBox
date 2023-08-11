package dev.bread.springboot.core.api.domain;

import dev.bread.springboot.core.api.support.error.CoreApiException;
import dev.bread.springboot.core.api.support.error.ErrorType;
import dev.bread.springboot.storage.db.core.UserEntity;
import dev.bread.springboot.storage.db.core.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserResult findUserByUserId(String userId) {
        UserEntity foundUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CoreApiException(ErrorType.NOT_FOUND_ERROR));

        return new UserResult(foundUser.getId(), foundUser.getUserId(), foundUser.getName());
    }
}
