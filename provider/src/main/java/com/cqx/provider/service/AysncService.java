package com.cqx.provider.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by BG307435 on 2018/3/14.
 */
@Service
public class AysncService {

    @Async(value = "asyncExecutor1")
    public void add() {
        System.out.println(1 + 1);
    }
}
