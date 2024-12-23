package NetworkEngineeringProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import NetworkEngineeringProject.intercepter.LoginCheckIntercepter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    LoginCheckIntercepter loginCheckIntercepter;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许所有路径
                .allowedOriginPatterns("*")  // 允许所有来源
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 允许的请求方法
                .allowedHeaders("token", "Content-Type", "Authorization")  // 允许的请求头
                .allowCredentials(true)  // 允许带凭证的请求（如 Cookie 或 JWT）
                .maxAge(3600);  // 预检请求的缓存时间（单位：秒）
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 将 LoginCheckIntercepter 添加到拦截器中，拦截所有路径
        registry.addInterceptor(loginCheckIntercepter)
                .addPathPatterns("/**")  // 拦截所有请求路径
                .excludePathPatterns("/api/user/login");  // 排除不需要登录验证的路径（如登录接口）
    }
}
