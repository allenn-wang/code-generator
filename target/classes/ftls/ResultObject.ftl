package ${rootPackageName}.${commonPackageName}.${basePackageName};

<#if swaggerEnable == "true">
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:
*/
<#if swaggerEnable == "true">
@ApiModel(value = "返回对象")
</#if>
public class ResultObject<T> {
    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "返回码")
    </#if>
    private String code;

    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "返回消息")
    </#if>
    private String message;

    <#if swaggerEnable == "true">
    @ApiModelProperty(value = "返回数据")
    </#if>
    private T data;

    public ResultObject() {

    }

    protected ResultObject(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultObject<T> success() {
        return new ResultObject<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    public static <T> ResultObject<T> success(T data) {
        return new ResultObject<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> ResultObject<T> success(T data, String message) {
        return new ResultObject<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static <T> ResultObject<T> failed(IResultCode resultCode) {
        return new ResultObject<T>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    public static <T> ResultObject<T> failed(String message) {
        return new ResultObject<T>(ResultCode.ERROR.getCode(), message, null);
    }

    public static <T> ResultObject<T> badRequest(String message) {
        return new ResultObject<T>(ResultCode.BAD_REQUEST.getCode(), message, null);
    }

    public static <T> ResultObject<T> failed() {
        return failed(ResultCode.ERROR);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}