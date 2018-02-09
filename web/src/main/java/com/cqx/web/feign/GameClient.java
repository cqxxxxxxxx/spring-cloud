package com.cqx.web.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by BG307435 on 2018/2/5.
 */
@FeignClient(name = "provider", fallback = WebClientImpl.class)
public interface GameClient {

    @RequestMapping(value = "/provider/info")
    String info();
}
