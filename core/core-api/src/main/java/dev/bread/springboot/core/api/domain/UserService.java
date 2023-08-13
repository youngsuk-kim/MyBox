package dev.bread.springboot.core.api.domain;

import dev.bread.springboot.core.api.controller.v1.request.CreateUserRequest;
import dev.bread.springboot.core.api.support.util.EncryptHelper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserReader userReader;
    private final UserWriter userWriter;
    private final EncryptHelper encryptHelper;

    public UserService(UserReader userReader, UserWriter userWriter, EncryptHelper encryptHelper) {
        this.userReader = userReader;
        this.userWriter = userWriter;
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
        return userWriter.save(
                createUserRequest.userId(),
                createUserRequest.password(),
                createUserRequest.userName()
        );
    }

}
