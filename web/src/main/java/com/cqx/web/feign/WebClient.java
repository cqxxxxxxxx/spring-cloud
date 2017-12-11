package com.cqx.web.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by BG307435 on 2017/12/11.
 */
@Component
@FeignClient(name = "provider", fallback = WebClientImpl.class)
public interface WebClient {

    @RequestMapping(value = "/provider/info")
    String info();
}
