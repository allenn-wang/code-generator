package ${rootPackageName}.${basePackageName}.exception;

import ${rootPackageName}.${basePackageName}.common.ResultObject;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
* @author：  ${author}
* @date：    ${date}
* @description：
*/
@ControllerAdvice
public class BusinessExceptionHandler {
    private static final Logger logger = Logger.getLogger(BusinessExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultObject handleException(BusinessException e) {
        logger.error(e.getResultCode().getMessage(), e);
        return ResultObject.failed(e.getResultCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultObject handleException(Exception e) {
        logger.error("error:", e);
        return ResultObject.failed();
    }
}