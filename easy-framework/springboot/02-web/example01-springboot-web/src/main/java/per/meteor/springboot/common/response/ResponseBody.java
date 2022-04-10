package per.meteor.springboot.common.response;

/**
 * @author meteor
 * @date 2022-03-22 12:58
 */
public class ResponseBody<T> {

    public static final Integer CODE_SUCCESS = 200;
    public static final Integer CODE_ERROR = 400;
    public static final String SUCCESS_TIP = "success";
    public static final String ERROR_TIP = "error";

    private final Integer code;

    private final String message;

    private final T data;

    private ResponseBody(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> ResponseBody<T> success() {
        return new ResponseBody<>(CODE_SUCCESS, SUCCESS_TIP, null);
    }

    public static <T> ResponseBody<T> success(T data) {
        return new ResponseBody<>(CODE_SUCCESS, SUCCESS_TIP, data);
    }

    public static <T> ResponseBody<T> success(Integer code, T data) {
        return new ResponseBody<>(code, SUCCESS_TIP, data);
    }

    public static <T> ResponseBody<T> success(String message, T data) {
        return new ResponseBody<>(CODE_SUCCESS, message, data);
    }

    public static <T> ResponseBody<T> success(Integer code, String message, T data) {
        return new ResponseBody<>(code, message, data);
    }

    public static <T> ResponseBody<T> error(T data) {
        return new ResponseBody<>(CODE_SUCCESS, SUCCESS_TIP, data);
    }

    public static <T> ResponseBody<T> error(Integer code, T data) {
        return new ResponseBody<>(code, SUCCESS_TIP, data);
    }

    public static <T> ResponseBody<T> error(String message, T data) {
        return new ResponseBody<>(CODE_SUCCESS, message, data);
    }

    public static <T> ResponseBody<T> error(Integer code, String message, T data) {
        return new ResponseBody<>(code, message, data);
    }
}
