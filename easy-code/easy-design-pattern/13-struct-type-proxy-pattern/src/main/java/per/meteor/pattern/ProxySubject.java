package per.meteor.pattern;

import java.util.logging.Logger;

/**
 * @author meteor
 * @date 2022-03-16 22:38
 */
public class ProxySubject implements BaseSubject {

    private static final Logger LOGGER = Logger.getLogger(ProxySubject.class.getName());

    private final BaseSubject subject;

    public ProxySubject(BaseSubject subject) {
        this.subject = subject;
    }

    private void beforeOperation() {
        LOGGER.info("do proxy subject before method");
    }

    @Override
    public void operation() {
        beforeOperation();
        subject.operation();
        postOperation();
    }

    private void postOperation() {
        LOGGER.info("do proxy subject post method");
    }
}
