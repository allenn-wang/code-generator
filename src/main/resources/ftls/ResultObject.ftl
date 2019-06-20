package ${rootPackageName}.${basePackageName}.common;

import ${rootPackageName}.${basePackageName}.exception.IResultCode;
import ${rootPackageName}.${basePackageName}.exception.ResultCode;

/**
* @author：  ${author}
* @date：    ${date}
* @description：
*/
public class ResultObject<T> {
    private String code;
    private String message;
    private T data;

    protected ResultObject(String code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
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
        return new ResultObject<T>(ResultCode.UNKNOWN_ERROR.getCode(), message, null);
    }

    public static <T> ResultObject<T> failed() {
        return failed(ResultCode.UNKNOWN_ERROR);
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
