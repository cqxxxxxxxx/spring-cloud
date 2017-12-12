package com.cqx.web.controller;

import com.cqx.web.feign.WebClient;
import com.cqx.web.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by BG307435 on 2017/12/11.
 */
@RestController
@RequestMapping("/web")
public class WebController {

    @Autowired
    WebClient webClient;
    @Autowired
    HystrixService hystrixService;

//    @Value("${info.description}")
//    String descFromConfig;

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

//    @GetMapping("/config/desc")
//    public String configDesc() {
//        return descFromConfig;
//    }

    @GetMapping("/provider")
    public String getProviderInfo() {
        return webClient.info();
    }

    @GetMapping("/zuul")
    public String zuulTest() {
        return "request though zuul";
    }

    @GetMapping("random")
    public String hystrixTest() {
        return hystrixService.getRandomNumber();
    }
}
