package io.github.gdtknight.getintime.controller.api;

import org.springframework.beans.factory.support.MethodReplacer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ApiPlaceController {
  @GetMapping("/places")
  public List<String> getPlaces() {
    return List.of("place1", "place2");
  }
  
  @PostMapping("/places")
  public Boolean createPlace() {
    return true;
  }
  
  @GetMapping("/places/{placeId}")
  public String getPlace(
      @PathVariable Integer placeId
  ) {
    return "place " + placeId;
  }
  
  @PutMapping("/places/{placeId}")
  public Boolean modifyPlace(
      @PathVariable Integer placeId
  ) {
    return true;
  }
  
  @DeleteMapping("/places/{placeId}")
  public Boolean removePlace(
      @PathVariable Integer placeId
  ) {
    return true;
  }
  
}