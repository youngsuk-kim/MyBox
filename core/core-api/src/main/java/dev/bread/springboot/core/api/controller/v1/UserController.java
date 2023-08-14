package dev.bread.springboot.core.api.controller.v1;

import dev.bread.springboot.core.api.controller.v1.request.CreateUserRequest;
import dev.bread.springboot.core.api.controller.v1.response.UserResponse;
import dev.bread.springboot.core.api.domain.UserResult;
import dev.bread.springboot.core.api.domain.UserService;
import dev.bread.springboot.core.api.support.error.CoreApiException;
import dev.bread.springboot.core.api.support.error.ErrorType;
import dev.bread.springboot.core.api.support.response.ApiResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import static dev.bread.springboot.core.api.support.Constants.LOGIN_SESSION;

@RestController
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/v1/users")
	public ApiResponse<UserResponse> find(@RequestParam String userId) {
		UserResult userResult = userService.findUserByUserId(userId);

		return ApiResponse.success(new UserResponse(userResult.id(), userResult.userId(), userResult.name()));
	}

	@PostMapping("/v1/users/login")
	public ApiResponse<?> login(@RequestParam String userId, @RequestParam String password, HttpSession httpSession) {
		if (!userService.checkPassword(userId, password)) {
			throw new CoreApiException(ErrorType.USER_PASSWORD_NOT_COLLECT);
		}

		httpSession.setAttribute(LOGIN_SESSION, userId);

		return ApiResponse.success();
	}

	@PostMapping("/v1/users")
	public ApiResponse<Long> create(@RequestBody CreateUserRequest request) {
		return ApiResponse.success(userService.createUser(request));
	}

}
