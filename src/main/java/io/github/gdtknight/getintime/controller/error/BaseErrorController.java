package io.github.gdtknight.getintime.controller.error;

import io.github.gdtknight.getintime.constant.ErrorCode;
import io.github.gdtknight.getintime.dto.ApiErrorResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class BaseErrorController implements ErrorController {
  @GetMapping(value = "/error", produces = MediaType.TEXT_HTML_VALUE)
  public ModelAndView errorHtml(HttpServletResponse response) {
    HttpStatus status = HttpStatus.valueOf(response.getStatus());
    
    ErrorCode errorCode = status.is4xxClientError()
        ? ErrorCode.BAD_REQUEST
        : ErrorCode.INTERNAL_ERROR;
    
    return new ModelAndView(
        "error",
        Map.of("statusCode", response.getStatus(),
            "errorCode", errorCode,
            "message", errorCode.getMessage(status.getReasonPhrase())
        )
    );
  }
  
  @GetMapping("/error")
  public ResponseEntity<ApiErrorResponse> error(HttpServletResponse response) {
    HttpStatus status = HttpStatus.valueOf(response.getStatus());
    
    ErrorCode errorCode = status.is4xxClientError()
        ? ErrorCode.BAD_REQUEST
        : ErrorCode.INTERNAL_ERROR;
    
    return ResponseEntity
        .status(status)
        .body(ApiErrorResponse.of(false, errorCode));
  }
}
