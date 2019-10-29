package com.souyue.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.souyue.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ManagerFilter extends ZuulFilter {


    @Autowired
    private JwtUtil jwtUtil;



    /**
     * 在请求前pre或者后post执行
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }


    /**
     * 多个过滤器的执行顺序，数字越小，表示越先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }


    /**
     * 当前过滤器是否开启, true表示开启,false表示关闭(有多个过滤器时，也可用false表示弃用这个过滤器)
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }


    /**
     * 过滤器内执行的具体操作 return 任何ojbect的值都表示继续执行
     * setsendzullRespponse(false)表示不再继续执行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("经过后台过滤器了！");
        RequestContext requestContext = RequestContext.getCurrentContext();
        //request域
        HttpServletRequest request = requestContext.getRequest();

        // 该请求不拦截 放行！！
        if(request.getMethod().equals("OPTIONS")){
            return null;
        }

        // 登录请求不拦截 放行！！
        if(request.getRequestURI().indexOf("login")>0){
            return null;
        }

        //得到头信息
        String header = request.getHeader("Authorization");
        if(header!=null && !"".equals(header)){
            if(header.startsWith("Bearer ")){
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if(roles.equals("admin")){
                        //把头信息转发下去，并且放行
                        requestContext.addZuulRequestHeader("Authorization", header);//然后走 user微服务、sms微服务、、、等微服务的interceptor
                        return null;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    requestContext.setSendZuulResponse(false);//终止运行
                }
            }
        }
        requestContext.setSendZuulResponse(false);//终止运行
        requestContext.setResponseStatusCode(403);// 权限不足code
        requestContext.setResponseBody("权限不足");
        requestContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
