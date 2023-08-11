package dev.bread.springboot.core.api.domain;

import dev.bread.springboot.core.api.support.error.CoreApiException;
import dev.bread.springboot.storage.db.core.UserEntity;
import dev.bread.springboot.storage.db.core.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        this.userService = new UserService(this.userRepository);
    }

    @Test
    void findUserThenFoundUser() {
        when(this.userRepository.findByUserId(any()))
                .thenReturn(Optional.of(
                        new UserEntity("testUser", "testName", "testPassword"))
                );

        UserResult userResult = userService.findUserByUserId("testUser");

        assertThat(userResult.userId()).isEqualTo("testUser");
        assertThat(userResult.name()).isEqualTo("testName");
    }

    @Test
    void ifFindUserNullThenException() {
        when(this.userRepository.findByUserId("notUser")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            userService.findUserByUserId("notUser");
        }).isInstanceOf(CoreApiException.class);
    }

}