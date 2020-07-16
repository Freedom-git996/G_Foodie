package com.vectory.config.exception;

import com.vectory.response.CommonReturnType;
import com.vectory.response.error.BusinessException;
import com.vectory.response.error.EmBusinessResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    public CommonReturnType handlerBusinessException(BusinessException ex) {
        return CommonReturnType.fail(ex.getStatus(), ex.getMsg());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public CommonReturnType handlerNotFoundException(NoHandlerFoundException ex)
    {
        return CommonReturnType.fail(EmBusinessResult.NO_HANDLER_FOUND);
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public CommonReturnType handleHttpMediaTypeNotSupportedException(Exception ex) {
        return CommonReturnType.fail(EmBusinessResult.MEDIATYPE_NOT_SUPPORTED);
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonReturnType handleHttpRequestMethodNotSupportedException(Exception ex) {
        return CommonReturnType.fail(EmBusinessResult.REQUESTMETHOD_NOT_SUPPORTED);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public CommonReturnType handleException(Exception ex) {
        return CommonReturnType.fail(EmBusinessResult.SERVER_INNER_ERROR);
    }
}
