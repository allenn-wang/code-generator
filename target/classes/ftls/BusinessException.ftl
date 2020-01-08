package ${rootPackageName}.${commonPackageName}.${basePackageName};

import java.io.Serializable;

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:
*/
public class BusinessException extends RuntimeException implements Serializable{
    private static final long serialVersionUID = 1L;

    private String code;
    private String message;

    public BusinessException(IResultCode resultCode, Throwable e) {
        super(e);
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public BusinessException(IResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public BusinessException() {
        this(ResultCode.ERROR);
    }

    public BusinessException(String message) {
        this.code = ResultCode.ERROR.getCode();
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}