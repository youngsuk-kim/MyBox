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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserReaderTest {

    @Mock
    private UserRepository userRepository;

    private UserReader userReader;

    @BeforeEach
    void setUp() {
        this.userReader = new UserReader(this.userRepository);
    }

    @Test
    void readReturnUser() {
        when(userRepository.findByUserId("testUser"))
                .thenReturn(Optional.of(new UserEntity("testUser", "testName", "testPassword")));

        UserResult userResult = userReader.read("testUser");

        assertThat(userResult.userId()).isEqualTo("testUser");
        assertThat(userResult.name()).isEqualTo("testName");
    }

    @Test
    void readByNotExistsUserIdThrowException() {
        when(userRepository.findByUserId("notUser"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            userReader.read("notUser");
        }).isInstanceOf(CoreApiException.class);
    }
}