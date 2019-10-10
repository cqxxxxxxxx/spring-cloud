package com.cqx.gateway.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
public class RateLimitConfig {

    @Bean
    @Primary
    public KeyResolver cqxxxxxxxxKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
    }


    @Bean
    @Primary
    public RateLimiter cqxxxxxxxxRateLimiter(ReactiveRedisTemplate<String, String> redisTemplate,
                                        RedisScript<List<Long>> script,
                                        @Qualifier("defaultValidator") Validator validator) {
        return new DefaultRedisRateLimiter(redisTemplate, script, validator);
    }


    public static class DefaultRedisRateLimiter extends RedisRateLimiter {

        Config getDefaultConfig() {
            return super.getConfig().get("defaultFilters");
        }

        public DefaultRedisRateLimiter(ReactiveRedisTemplate<String, String> redisTemplate,
                                       RedisScript<List<Long>> script,
                                       @Qualifier("defaultValidator") Validator validator) {
            super(redisTemplate, script, validator);
        }

        @Override
        public Mono<Response> isAllowed(String routeId, String id) {
            if (null == super.getConfig().get(routeId))
                getConfig().put(routeId, getDefaultConfig());
            return super.isAllowed(routeId, id);
        }
    }

}
