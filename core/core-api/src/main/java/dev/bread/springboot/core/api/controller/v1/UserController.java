package dev.bread.springboot.core.api.controller.v1;

import dev.bread.springboot.core.api.controller.v1.response.UserResponse;
import dev.bread.springboot.core.api.domain.UserResult;
import dev.bread.springboot.core.api.domain.UserService;
import dev.bread.springboot.core.api.support.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
