package com.cqx.web.controller;

import com.cqx.web.feign.GameClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by BG307435 on 2018/2/5.
 */
@Controller
@RequestMapping("/game")
public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);


//    @Autowired
//    GameClient gameClient;

    private SimpMessagingTemplate template;

    @Autowired
    public GameController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/hall")
    @SendTo("/topic/hall")
    public String hallMessage(String message, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        Map map = headerAccessor.getSessionAttributes();
        //当前session的id
        System.out.println(map.get(SimpMessageHeaderAccessor.SESSION_ID_HEADER));
        return message;
    }

    @GetMapping("/")
    public String toGame() {
        return "game";
    }

    @PostMapping("/{topic}")
    public String sendToAll(@RequestParam String text, @PathVariable String topic) {
        template.convertAndSend(topic, text);
        return "success";
    }

    @PostMapping("/{topic}/personal")
    public String sendToUser(@RequestParam String text, @PathVariable String topic, @RequestParam String receiver) {
        template.convertAndSendToUser(receiver, topic, text);
        return "success";
    }
}
