package dev.bread.springboot.core.api.support.config;

import dev.bread.springboot.core.api.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final LoginCheckInterceptor loginCheckInterceptor;

	public WebConfig(LoginCheckInterceptor loginCheckInterceptor) {
		this.loginCheckInterceptor = loginCheckInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginCheckInterceptor).excludePathPatterns("/v1/users/login", "/v1/users");
	}

}
