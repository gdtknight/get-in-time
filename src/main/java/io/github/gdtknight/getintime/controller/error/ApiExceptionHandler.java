package io.github.gdtknight.getintime.controller.error;

import io.github.gdtknight.getintime.constant.ErrorCode;
import io.github.gdtknight.getintime.dto.ApiErrorResponse;
import io.github.gdtknight.getintime.exception.GeneralException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler
  public ResponseEntity<Object> general(
      GeneralException e,
      WebRequest request
  ) {
    ErrorCode errorCode = e.getErrorCode();
    HttpStatus status = errorCode.isClientSideError()
        ? HttpStatus.BAD_REQUEST
        : HttpStatus.INTERNAL_SERVER_ERROR;
    
//    return ResponseEntity
//        .status(status)
//        .body(ApiErrorResponse.of(
//            false, errorCode, errorCode.getMessage(e)
//        ));
    return super.handleExceptionInternal(
        e,
        ApiErrorResponse.of(
            false,
            errorCode.getCode(),
            errorCode.getMessage(e)
        ),
        HttpHeaders.EMPTY,
        status,
        request
    );
  }
  
  @ExceptionHandler
  public ResponseEntity<Object> exception(
      Exception e,
      WebRequest request
  ) {
    ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    
//    return ResponseEntity
//        .status(status)
//        .body(ApiErrorResponse.of(
//            false, errorCode, errorCode.getMessage(e)
//        ));
    return super.handleExceptionInternal(
        e,
        ApiErrorResponse.of(
            false,
            errorCode.getCode(),
            errorCode.getMessage(e)
        ),
        HttpHeaders.EMPTY,
        status,
        request
    );
  }
  
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex,
      @Nullable Object body,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request
  ) {
    ErrorCode errorCode = status.is4xxClientError()
        ? ErrorCode.SPRING_BAD_REQUEST
        : ErrorCode.SPRING_INTERNAL_ERROR;
    
    if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
      request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
    }
    
    return super.handleExceptionInternal(
        ex,
        ApiErrorResponse.of(
            false,
            errorCode.getCode(),
            errorCode.getMessage(ex)
        ),
        headers,
        status,
        request
    );
  }
}
