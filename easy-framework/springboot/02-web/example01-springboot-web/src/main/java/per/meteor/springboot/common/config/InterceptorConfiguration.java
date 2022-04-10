package per.meteor.springboot.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import per.meteor.springboot.common.interceptor.CustomInterceptor;

/**
 * 拦截器配置
 * @author meteor
 * @date 2022-03-22 16:20
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Bean
    public CustomInterceptor customHandlerInterceptor() {
        return new CustomInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customHandlerInterceptor());
    }
}
