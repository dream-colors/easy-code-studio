package per.meteor.springboot.common.interceptor.accesslimit;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import per.meteor.springboot.common.exception.ServiceException;
import per.meteor.springboot.common.exception.ServiceExceptionEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author 钟宗兵
 * @date 2022/4/13 10:14
 */
public class AccessLimitInterceptor implements HandlerInterceptor {

    private final AuthenticationService authenticationService;
    private final CacheService cacheService;

    public AccessLimitInterceptor(AuthenticationService authenticationService, CacheService cacheService) {
        this.authenticationService = authenticationService;
        this.cacheService = cacheService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
            if (accessLimit == null || !accessLimit.auth()) {
                return true;
            }

            // 授权认证
            if (!authenticationService.auth(request)) {
                throw new IllegalAccessException("auth error");
            }

            // 获取访问次数,并进行相应自增操作，由于操作不是原子性，在大并发下，数据可能会出错，所以需要枷锁操作
            String key = request.getRequestURI();
            Integer accessCount = Optional.ofNullable(cacheService.get(key)).map(Integer::parseInt).orElse(null);

            if (accessCount == null) {
                long expire = TimeUnit.SECONDS.convert(accessLimit.interval(), accessLimit.unit());
                cacheService.set(key, String.valueOf(1), expire);
                return true;
            }

            if (accessCount >= accessLimit.value()) {
                throw new ServiceException(ServiceExceptionEnum.ACCESS_LIMIT_ERROR);
            }

            cacheService.incr(key);
        }

        return true;
    }
}
