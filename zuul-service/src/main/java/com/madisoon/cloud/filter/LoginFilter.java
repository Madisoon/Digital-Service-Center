package com.madisoon.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Description:
 * 登陆拦截器，主要为了验证是否登陆
 *
 * @author Msater Zg
 * @date 2018/9/9 下午8:47
 */

public class LoginFilter extends ZuulFilter {

    @Value("${loginFilter.enable}")
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
        System.out.println("123123123");
        return filterEnable;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("123123123");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpSession session = request.getSession();
        if (session != null) {
            //对请求进行路由
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            // 对下一个路由可见
            ctx.set("isSuccess", true);
            return null;
        } else {
            //不对其进行路由
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("Token is Wrong ！");
            ctx.set("isSuccess", false);
            return null;
        }
    }
}
