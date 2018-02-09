package com.cqx.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by BG307435 on 2018/2/9.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String toIndex() {
        return "index";
    }
}
