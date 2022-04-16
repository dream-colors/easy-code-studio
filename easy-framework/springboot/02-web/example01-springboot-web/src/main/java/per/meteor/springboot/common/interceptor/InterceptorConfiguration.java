package per.meteor.springboot.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import per.meteor.springboot.common.interceptor.accesslimit.AccessLimitInterceptor;
import per.meteor.springboot.common.interceptor.accesslimit.AuthenticationService;
import per.meteor.springboot.common.interceptor.accesslimit.CacheService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 拦截器配置
 * @author meteor
 * @date 2022-03-22 16:20
 */
@Slf4j
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Bean
    public CustomInterceptor customHandlerInterceptor() {
        return new CustomInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationService authenticationService() {
        return request -> true;
    }

    @Bean
    @ConditionalOnMissingBean
    public CacheService cacheService() {
        log.info("注入成功");
        // 缓存简单实现，可根据需求使用redis或者数据存储
        return new CacheService() {
            public final Map<String, DataInfo> data = new ConcurrentHashMap<>(16);

            @Override
            public String get(String key) {
                DataInfo dataInfo = data.get(key);
                if (dataInfo == null || dataInfo.isExpire()) {
                    data.remove(key);
                    return null;
                }
                return dataInfo.getValue();
            }

            @Override
            public void set(String key, String value, long expire) {
                data.putIfAbsent(key, new DataInfo(value, expire));
            }

            @Override
            public void incr(String key) {
                DataInfo dataInfo = data.get(key);
                String value = dataInfo.getValue();
                if (value != null && value.length() > 0) {
                    dataInfo.setValue(Integer.valueOf(value) + 1 + "");
                }
            }

            class DataInfo {
                private String value;
                private final Long expire;
                private final LocalDateTime createTime = LocalDateTime.now();

                public DataInfo(String value, long expire) {
                    this.value = value;
                    this.expire = expire;
                }

                public String getValue() {
                    return isExpire() ? null : value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public boolean isExpire() {
                    return createTime.plus(expire, ChronoUnit.SECONDS).isBefore(LocalDateTime.now());
                }
            }
        };
    }

    @Bean
    public AccessLimitInterceptor accessLimitInterceptor() {
        return new AccessLimitInterceptor(authenticationService(), cacheService());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customHandlerInterceptor());
        registry.addInterceptor(accessLimitInterceptor());
    }
}
