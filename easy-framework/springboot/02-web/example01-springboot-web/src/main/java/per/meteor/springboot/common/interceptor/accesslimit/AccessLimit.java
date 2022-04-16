package per.meteor.springboot.common.interceptor.accesslimit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author meteor
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {

    /** 请求数 **/
    int value() default 5;
    /** 请求间隔 **/
    long interval() default 5;
    /** 时间单位 **/
    TimeUnit unit() default TimeUnit.SECONDS;
    /** 登录授权 **/
    boolean auth() default true;
}
