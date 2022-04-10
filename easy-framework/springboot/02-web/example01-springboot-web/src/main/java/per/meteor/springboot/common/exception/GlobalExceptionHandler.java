package per.meteor.springboot.common.exception;

import cn.hutool.http.server.HttpServerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import per.meteor.springboot.common.response.ResponseBody;

/**
 * 自定义异常处理器
 * @author meteor
 * @date 2022-03-22 15:18
 */
@Slf4j
@RestControllerAdvice(basePackages = "per.meteor.springboot.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseBody<Object> handleOtherException(HttpServerRequest request, Exception e) {
        log.error("exceptionHandler: {}", e.getMessage());
        return ResponseBody.error(e.getMessage());
    }

}
