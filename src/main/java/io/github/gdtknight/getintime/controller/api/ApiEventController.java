package io.github.gdtknight.getintime.controller.api;

import io.github.gdtknight.getintime.exception.GeneralException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiEventController {
  @GetMapping("/events")
  public List<String> getEvents() throws Exception {
    throw new HttpRequestMethodNotSupportedException("스프링 에러 테스트");
//    return List.of("event1", "event2");
  }
  
  @PostMapping("/events")
  public Boolean createEvent() {
    throw new GeneralException("일반적인 예외");
//    return true;
  }
  
  @GetMapping("/events/{eventId}")
  public String getEvent(
      @PathVariable Integer eventId
  ) {
    throw new RuntimeException("런타임 에러");
//    return "event " + eventId;
  }
  
  @PutMapping("/events/{eventId}")
  public Boolean modifyEvent(
      @PathVariable Integer eventId
  ) {
    return true;
  }
  
  @DeleteMapping("/events/{eventId}")
  public Boolean removeEvent(
      @PathVariable Integer eventId
  ) {
    return true;
  }
}