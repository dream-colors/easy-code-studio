package per.meteor.springboot.common.interceptor.accesslimit;

import javax.servlet.http.HttpServletRequest;

/**
 * @author meteor
 */
public interface AuthenticationService {

    /**
     * 认证服务
     * @param request /
     * @return /
     */
    boolean auth(HttpServletRequest request);

}
