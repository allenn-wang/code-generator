package ${rootPackageName}.${commonPackageName}.${basePackageName}.exception;

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:
*/
public enum ResultCode implements IResultCode {
    SUCCESS("200", "操作成功"),

    //请求参数错误
    BAD_REQUEST("400", "请求异常"),

    //登录验证
    UNAUTHORIZED("401", "请登录"),

    //重复提交
    FORBIDDEN("403", "操作频繁，请稍后重试"),

    //接口版本控制
    METHOD_NOT_ALLOWED("405", "客户端版本过低，请检查更新"),

    ERROR("500", "出错了");

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