package per.meteor.springboot.common.exception;

/**
 * @author meteor
 * @date 2022-04-12 23:53
 */
public class ServiceException extends RuntimeException {

    /**
     * 异常代号
     */
    private final Integer code;

    public ServiceException(ServiceExceptionEnum serviceExceptionEnum) {
        super(serviceExceptionEnum.getMessage());
        this.code = serviceExceptionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
