package com.cqx.web.spring;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/3
 */
public class Usages {

    /**
     * org.springframework.util 包下的类
     * 用来统计启动结束的时间，打印一些东西
     * spring容器启动时有用到
     * @throws InterruptedException
     */
    @Test
    public void stopWatchUsage() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        TimeUnit.SECONDS.sleep(3);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
