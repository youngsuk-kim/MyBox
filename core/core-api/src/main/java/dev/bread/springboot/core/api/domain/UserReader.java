package dev.bread.springboot.core.api.domain;

import dev.bread.springboot.core.api.support.error.CoreApiException;
import dev.bread.springboot.core.api.support.error.ErrorType;
import dev.bread.springboot.storage.db.core.UserEntity;
import dev.bread.springboot.storage.db.core.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserReader {

    private final UserRepository userRepository;

    public UserReader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserResult read(String userId) {
        UserEntity foundUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CoreApiException(ErrorType.NOT_FOUND_ERROR));

        return new UserResult(foundUser.getId(), foundUser.getUserId(), foundUser.getName(), foundUser.getPassword());
    }

    @Transactional(readOnly = true)
    public UserEntity read(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CoreApiException(ErrorType.NOT_FOUND_ERROR));
    }

}
