package com.cqx.individual;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author BG307435
 */
@SpringBootApplication
@RestController
public class IndividualApplication extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(IndividualApplication.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IndividualApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(IndividualApplication.class, args);
    }

    @PostMapping("/redis")
    public String addKV(@RequestParam String key, @RequestParam String value) {
        log.info("addKV -- key:{},value:{}", key, value);
        stringRedisTemplate.opsForValue().set(key, value);
        return "success";
    }

    @GetMapping("/redis/{key}")
    public String getV(@PathVariable String key) {
        log.info("getV -- key:{}", key);
        return stringRedisTemplate.opsForValue().get(key);
    }
}
