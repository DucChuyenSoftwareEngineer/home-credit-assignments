package com.example.homecredit.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        log.info("PING OK");
        return "OK";
    }

    @GetMapping("/time")
    public String time() {
        log.info("Get time ok");
        return String.valueOf(System.currentTimeMillis());
    }


}