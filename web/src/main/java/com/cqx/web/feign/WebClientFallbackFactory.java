package com.cqx.web.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 可指定fallbackFactory，代替WebClientImpl 来获取具体的信息
 * Created by BG307435 on 2017/12/13.
 */
@Component
public class WebClientFallbackFactory implements FallbackFactory<WebClient> {
    @Override
    public WebClient create(Throwable throwable) {
        return new WebClient() {
            @Override
            public String info() {
                return "fallback error:" + throwable.getCause();
            }
        };
    }
}
