package ${rootPackageName}.${basePackageName}.exception;

import java.io.Serializable;

/**
* @author：  ${author}
* @date：    ${date}
* @description：
*/
public class BusinessException extends RuntimeException implements Serializable{
    private static final long serialVersionUID = 1L;

    private IResultCode resultCode;

    public BusinessException(IResultCode code, Throwable e) {
        super(e);
        this.resultCode = code;
    }

    public BusinessException(IResultCode code) {
        this.resultCode = code;
    }

    public IResultCode getResultCode() {
        return resultCode;
    }
}