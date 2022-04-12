package per.meteor.springboot.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * @author meteor
 * @date 2022-03-22 16:21
 */
@Slf4j
public class CustomInterceptor implements HandlerInterceptor {

    /**
     * 前置处理逻辑。当返回 true 时，继续后续 handler 的执行；当返回 false 时，不进行后续 handler 的执行。
     * 例如说，判断用户是否已经登录，如果未登录，返回 false ，不进行后续 handler 的执行。
     * @param request /
     * @param response /
     * @param handler /
     * @return /
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("[preHandler][handler({})]", handler);
        return true;
    }

    /**
     * 后置处理逻辑。在视图 View 在渲染之前，做一些处理。不过因为目前都前后端分离，所以这个后置拦截点，使用的就已经比较少了
     * @param request /
     * @param response /
     * @param handler /
     * @param modelAndView /
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("[postHandle][handler({})]", handler);
    }

    /**
     * 最终处理器，整个 handler 执行完成执行
     * 例如说，释放资源。比如，清理认证拦截器产生的 ThreadLocal 线程变量，避免“污染”下一个使用到该线程的请求
     * 又例如说，处理 handler 执行过程中发生的异常，并记录异常日志。不过因为现在一般通过全局异常处理来处理，所以很少这么做了
     * 再例如说，记录请求结束时间，这样我们就可以计算出整个请求的耗时
     * @param request /
     * @param response /
     * @param handler /
     * @param ex /
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("[afterCompletion][handler({})]", handler);
    }
}
