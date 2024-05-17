package com.dmg.dmgngbe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @RequestMapping({"", "/", "/health"})
  public String home() {
    return "ok";
  }

}
