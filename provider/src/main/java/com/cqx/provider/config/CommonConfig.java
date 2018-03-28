package com.cqx.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by BG307435 on 2018/3/14.
 */
@Configuration
public class CommonConfig {

    @Bean(name = "asyncExecutor1")
    public Executor asyncExecutor() {
        Executor executor = new ThreadPoolExecutor(1, 0, 5000L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        return executor;
    }
}
