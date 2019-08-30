package com.cqx.provider.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by BG307435 on 2018/3/14.
 */
@Service
public class AysncService {

    @Transactional
    @org.springframework.transaction.annotation.Transactional
    @Async(value = "asyncExecutor1")
    public void add() {
        System.out.println(1 + 1);
    }
}
