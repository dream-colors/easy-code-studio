package per.meteor.pattern;

import java.util.Map;

/**
 * @author meteor
 * @date 2022-03-17 15:06
 */
public abstract class AbstractHandler {

    protected AbstractHandler nextHandler;
    protected Map<String, Object> params;

    public void setNext(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public boolean hashNext() {
        return nextHandler != null;
    }

    public void refuseHandle() {
        throw new IllegalStateException("no handler can handle it");
    }

    /**
     * 业务处理
     * @param params /
     */
    public void handle(Map<String, Object> params) {

        this.params = params;

        if (canHandle()) {
            doHandle();
        } else if (hashNext()) {
            nextHandler.handle(params);
        } else {
            // 无法处理的情况
            refuseHandle();
        }
    }

    /**
     * 业务处理
     */
    public abstract void doHandle();

    /**
     * 判断当前handler能否处理
     * @return /
     */
    public abstract boolean canHandle();

}
