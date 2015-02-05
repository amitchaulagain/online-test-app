package com.sumit.init;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExeceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public @ResponseBody ErrorResponse handleException(Exception ex,HttpRequest request) {
        ErrorResponse err = new ErrorResponse();
        err.setStatusCode(404);
        err.setErrorMessage(ex.getMessage());
       
        return err;
    }

}
