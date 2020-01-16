package ${rootPackageName}.${commonPackageName}.${basePackageName};

import ${rootPackageName}.${commonPackageName}.${basePackageName}.ResultObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.extern.slf4j.Slf4j;

/**
* @Author:  ${author}
* @Date:    ${date}
* @Description:
*/
@Slf4j
@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultObject handleException(BusinessException e) {
        log.warn(e.getMessage(), e);
        return ResultObject.failed(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultObject handleIllegalArgumentException(IllegalArgumentException e) {
        return ResultObject.badRequest(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultObject validationErrorHandler(MethodArgumentNotValidException ex) {
        List<String> errorInformation = ex.getBindingResult().getAllErrors()
            .stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        return ResultObject.badRequest(errorInformation.get(0));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultObject handleException(Exception e) {
        log.error("error:", e);
        return ResultObject.failed();
    }
}