package com.cqx.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by BG307435 on 2018/2/9.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping
    public String toIndex() {
        return "index";
    }

    @GetMapping("/log4j/{level}")
    @ResponseBody
    public String log(@RequestParam String info, @PathVariable Integer level) {
        if (level == 1) {
            logger.debug(info);
        }
        if (level == 2) {
            logger.info(info);
        }
        if (level == 3) {
            logger.error(info);
        }
        return "success";
    }
}
