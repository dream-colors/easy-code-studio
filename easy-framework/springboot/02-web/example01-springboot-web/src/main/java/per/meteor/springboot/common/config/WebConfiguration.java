package per.meteor.springboot.common.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author meteor
 * @date 2022-03-19 23:11
 */
@Configuration
public class WebConfiguration implements InitializingBean {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Override
    public void afterPropertiesSet() {
        // 基础UrlPathHelper上相同属性的快捷方式。注意：此属性与设置setPatternParser(PathPatternParser)时互斥并忽略。
        handlerMapping.setRemoveSemicolonContent(false);
    }
}
