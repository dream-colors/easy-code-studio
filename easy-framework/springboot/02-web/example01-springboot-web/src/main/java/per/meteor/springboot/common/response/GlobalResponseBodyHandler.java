package per.meteor.springboot.common.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import per.meteor.springboot.controller.ParamParseDemoController;

/**
 * 统一接口返回值类型
 * 1、需指定重写的接口、可采用类名的形式、包形式
 * <p></p>
 * 扩展：也可以统一请求值，创建拦截器并实现RequestBodyAdvice，并添加RestControllerAdvice注解使其生效
 * @author meteor
 * @date 2022-03-22 14:32
 */
@RestControllerAdvice(basePackageClasses = ParamParseDemoController.class)
public class GlobalResponseBodyHandler implements ResponseBodyAdvice<Object> {

    /**
     * 重写支持的类型
     * @param returnType 返回值类型
     * @param converterType  转换器类型
     * @return 是否需要重写
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    /**
     * 重写返回值
     * @param body  原返回值
     * @param returnType 返回值类型
     * @param selectedContentType /
     * @param selectedConverterType /
     * @param request /
     * @param response /
     * @return /
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResponseBody) {
            return body;
        }
        return ResponseBody.success(body);
    }
}
