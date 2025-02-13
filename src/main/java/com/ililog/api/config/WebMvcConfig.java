package com.ililog.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final AppConfig appConfig;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // front에서 vite proxy로 사용하기 때문에 중지 시켜놓음
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:5173"); // 허용할 출처 : 특정 도메인만 받을 수 있음
    }

    // 인터셉터와 아규먼트리졸브의 차이
    // 인터셉터는 path로 필터링을함
    // 아규먼트리졸브는 parameter타입으로 필터링을함 ex)user Session
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(new AuthInterceptor());
        // .addPathPatterns("")
        // ..excludePathPatterns("")
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // Security로 변경함
        // resolvers.add(new AuthResolver(appConfig));
    }
}