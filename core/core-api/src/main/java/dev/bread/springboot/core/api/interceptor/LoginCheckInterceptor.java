package dev.bread.springboot.core.api.interceptor;

import dev.bread.springboot.core.api.domain.UserReader;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.HandlerInterceptor;

import static dev.bread.springboot.core.api.support.Constants.LOGIN_SESSION;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

	private final UserReader userReader;

	public LoginCheckInterceptor(UserReader userReader) {
		this.userReader = userReader;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		HttpSession session = request.getSession();
		Assert.notNull(session, "session must not be null");

		String userId = (String) session.getAttribute(LOGIN_SESSION);
		Assert.notNull(userId, "user id must not be null");

		session.setAttribute("userInfo", userReader.read(userId));

		return true;
	}

}
