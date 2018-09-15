package com.madisoon.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Value;

/**
 * Description:
 * 跨域请求验证拦截
 *
 * @author Msater Zg
 * @date 2018/9/9 下午8:47
 */
public class TokenFilter extends ZuulFilter {

    @Value("${tokenFilter.enable}")
    private boolean filterEnable;

    @Override
    public String filterType() {
        return FilterConstant.FILTER_PRE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return filterEnable;
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
