package dev.bread.springboot.core.api.domain;

import dev.bread.springboot.core.api.support.utils.EncryptHelper;
import dev.bread.springboot.storage.db.core.UserEntity;
import dev.bread.springboot.storage.db.core.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAppenderTest {

    @Mock
    private EncryptHelper encryptHelper;

    @Mock
    private UserRepository userRepository;

    private UserAppender userAppender;

    @BeforeEach
    void setUp() {
        this.userAppender = new UserAppender(this.userRepository, this.encryptHelper);
    }

    @Test
    void createUserVerifyCall() {
        when(this.userRepository.save(any()))
                .thenReturn(new UserEntity("testUser", "testName", "testPassword"));
        when(this.encryptHelper.encrypt("testPassword")).thenReturn("encryptPassword");

        userAppender.save("testUser", "testPassword", "testName");

        verify(this.userRepository, times(1)).save(any());
        verify(this.encryptHelper, times(1)).encrypt("testPassword");
    }
}