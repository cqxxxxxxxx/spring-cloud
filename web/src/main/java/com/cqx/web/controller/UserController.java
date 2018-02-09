package com.cqx.web.controller;

import com.cqx.model.UserEntity;
import com.cqx.model.UserHomeEntity;
import com.cqx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by BG307435 on 2018/2/9.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @ResponseBody
    public String addUser(@RequestBody UserEntity userEntity) {
        userService.addUser(userEntity);
        return "success";
    }

    @GetMapping("/{uid}/home")
    public String toHome(@PathVariable String uid, Model model) {
        UserEntity user = userService.findUserDetail(uid);
        model.addAttribute("user", user);
        return "home";
    }

    @PostMapping("/{uid}/friends")
    @ResponseBody
    public String addFriends(@PathVariable String uid, @RequestBody List<String> friendIds) {
        userService.addFriend(uid, friendIds);
        return "success";
    }

    @PutMapping("/{uid}/home")
    @ResponseBody
    public String updateHome(@PathVariable String uid, @RequestBody UserHomeEntity userHome) {
        userService.saveUserHome(uid, userHome.getHome());
        return "success";
    }
}
