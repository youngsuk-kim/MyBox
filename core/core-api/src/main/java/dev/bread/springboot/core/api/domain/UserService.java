package dev.bread.springboot.core.api.domain;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserReader userReader;

    public UserService(UserReader userReader) {
        this.userReader = userReader;
    }

    public UserResult findUserByUserId(String userId) {
        return userReader.read(userId);
    }
}
