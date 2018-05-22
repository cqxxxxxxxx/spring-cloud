package com.cqx.individual;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author BG307435
 */
@SpringBootApplication
@Controller
public class IndividualApplication extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(IndividualApplication.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Value("${hostname}")
    String id;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IndividualApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(IndividualApplication.class, args);
    }

    @PostMapping("/redis")
    @ResponseBody
    public String addKV(@RequestParam String key, @RequestParam String value) {
        log.info("addKV -- key:{},value:{}", key, value);
        stringRedisTemplate.opsForValue().set(key, value);
        return "success";
    }

    @GetMapping("/redis/{key}")
    @ResponseBody
    public String getV(@PathVariable String key) {
        log.info("getV -- key:{}", key);
        return stringRedisTemplate.opsForValue().get(key);
    }

    @GetMapping("/index")
    public String toIndex(Model model) {
        model.addAttribute("id", id);
        return "index";
    }
}
