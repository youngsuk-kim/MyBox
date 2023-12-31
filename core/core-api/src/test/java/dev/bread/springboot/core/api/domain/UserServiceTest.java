package dev.bread.springboot.core.api.domain;

import dev.bread.springboot.core.api.support.utils.EncryptHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserReader userReader;

	@Mock
	private UserAppender userAppender;

	@Mock
	private EncryptHelper encryptHelper;

	private UserService userService;

	@BeforeEach
	void setUp() {
		this.userService = new UserService(this.userReader, userAppender, encryptHelper);
	}

	@Test
    void findUserReturnFoundUser() {
        when(this.userReader.read("testUser"))
                .thenReturn(
                        new UserResult(1L, "testUser", "testName", "testPassword")
                );

        UserResult userResult = userService.findUserByUserId("testUser");

        assertThat(userResult.id()).isEqualTo(1L);
        assertThat(userResult.userId()).isEqualTo("testUser");
        assertThat(userResult.name()).isEqualTo("testName");
    }

}