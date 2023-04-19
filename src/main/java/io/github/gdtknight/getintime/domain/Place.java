package io.github.gdtknight.getintime.domain;

import io.github.gdtknight.getintime.constant.PlaceType;

import java.time.LocalDateTime;

public class Place {
  private Long id;
  
  private PlaceType placeType;
  private String placeName;
  private String address;
  private String phoneNumber;
  private Integer capacity;
  private String memo;
  
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}
