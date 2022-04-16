package per.meteor.springboot.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import per.meteor.springboot.common.response.ResponseBody;

/**
 * 自定义异常处理器
 * <p></p>
 * RestControllerAdvice注解用于接口拦截，是ControllerAdvice注解和ResponseBody注解的组合
 * <P></P>
 * ExceptionHandler注解用于标明异常处理方法，入参可以是异常参数、请求对象、响应对象、会话、WebRequest、Local、InputStream/Reader、OutputStream/Writer、Model
 * 返回值可以是ModelAndView、Model、Map、View、String、@ResponseBody、HttpEntity<?>或ResponseEntity<?>，以及void
 * @author meteor
 * @date 2022-03-22 15:18
 */
@Slf4j
@RestControllerAdvice(basePackages = "per.meteor.springboot.controller")
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseBody<Object> serviceExceptionHandler(ServiceException exception) {
        log.error("【serviceExceptionHandler】", exception);
        return ResponseBody.error(exception.getCode(), exception.getMessage());
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseBody<Object> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException exception) {
        log.error("【missingServletRequestParameterExceptionHandler】", exception);
        return ResponseBody.error(ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getCode(), ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getMessage());
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseBody<Object> handleOtherException(Exception exception) {
        log.error("【exceptionHandler】", exception);
        return ResponseBody.error(ServiceExceptionEnum.SYS_ERROR.getCode(), ServiceExceptionEnum.SYS_ERROR.getMessage());
    }

}
