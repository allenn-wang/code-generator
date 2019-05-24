package ${rootPackageName}.${basePackageName}.exception;

/**
* @author：  ${author}
* @date：    ${date}
* @description：
*/
public enum ResultCode implements IResultCode {
    SUCCESS("000000", "success"),

    UNKNOWN_ERROR("100000", "unknown error");

    private String code;
    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}