package com.example.securitymvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityController {

    @GetMapping("/healthcheck")
    public String  healthCheck() {
        return "Healthcheck";
    }

    @GetMapping("/user/{user}")
    public String user(String user) {
        return "User";
    }
  @GetMapping("/najnowszy/{user}")
    public String najnowszy(String user) {
        return "Najnowszy";
    }

    @PostMapping("/delete/{user}")
    public String delete(String user) {
        return "Delete";
    }
    @GetMapping("/update/{user}")
    public String update(String user) {
        return "Update";
    }
}
