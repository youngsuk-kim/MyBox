package dev.bread.springboot.core.api.controller.v1.request;

public record CreateUserRequest(String userId, String password, String userName) {
}
