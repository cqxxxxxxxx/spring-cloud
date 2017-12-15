package com.cqx.zuul;

import com.netflix.zuul.ZuulFilter;

/**
 * 对请求进行拦截处理
 * Created by BG307435 on 2017/12/14.
 */
public class ApiFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        return null;
    }
}
