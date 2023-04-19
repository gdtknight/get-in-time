package io.github.gdtknight.getintime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
  @GetMapping("/sign-up")
  public String signUp() {
    return "auth/sign-up";
  }
  
  @GetMapping("/login")
  public String login() {
    return "auth/login";
  }
}
