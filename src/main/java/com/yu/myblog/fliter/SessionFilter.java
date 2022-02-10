package com.yu.myblog.fliter;

import com.yu.myblog.utils.TokenManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
@Log4j2
public class SessionFilter implements Filter {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //标示符：表示当前用户未登录(可根据自己项目需要改为json样式)
        servletResponse.setCharacterEncoding("gbk");

        String NO_LOGIN = "{\"code\":401,\"message\":\"no login\"}";
        log.info("加载过滤器");
        //需要登录的访问地址
        String[] includeUrls = new String[]{"/backstage/", "/user/"};

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers","Content-Type,Access-Token,uid,token,Authorization");
        response.setCharacterEncoding("utf-8");

        String uri = request.getRequestURI();
        log.info("filter url:{}", uri);

        String token = request.getHeader("token");
        String headerUid = request.getHeader("uid");
        int uid = 0;
        if(headerUid != null){
            uid = Integer.parseInt(headerUid);
        }

        //是否需要过滤
        boolean needFilter = isNeedFilter(uri, includeUrls);
        log.info("是否需要拦截:{}", needFilter);
        if (!needFilter) { //不需要过滤直接传给下一个过滤器
            if (token != null) {
                try {
                    boolean tokenIsDate = TokenManager.verifyToken(token);
                    if (tokenIsDate) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        log.info("获取uid:{}",uid);
                        Object redisToken = redisTemplate.opsForValue().get(uid);
                        log.info("redis获取token:{}", redisToken);
                        if (redisToken == null) {
                            //response.getWriter().write(NO_LOGIN);
                            response.setHeader("invalid", "login invalid");
                            filterChain.doFilter(servletRequest, servletResponse);
                        } else {
                            String newToken = TokenManager.createToken(uid + "");
                            response.setHeader("token", newToken);
                            redisTemplate.opsForValue().set(uid, newToken, 60 * 15, TimeUnit.SECONDS);
                            Object redisNewToken = redisTemplate.opsForValue().get(uid);
                            log.info("刷新redisToken值:token:{}",redisNewToken);
                            filterChain.doFilter(servletRequest, servletResponse);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("token过期");
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }

        } else { //需要过滤器
            log.info("获取token:" + token);
            boolean flag = TokenManager.verifyToken(token);
            if (!flag) {
                //redisTemplate.opsForValue().set(email,checking,60*5, TimeUnit.SECONDS);

                Object redisToken = redisTemplate.opsForValue().get(uid);
                if (redisToken != null) {
                    String newToken = TokenManager.createToken(uid + "");
                    response.setHeader("token", newToken);
                    redisTemplate.opsForValue().set(uid , newToken, 60 * 15, TimeUnit.SECONDS);
                    flag = true;
                }
            }

            if (token != null && flag) {

                log.info("token认证成功 token:" + token);

                filterChain.doFilter(request, response);
            } else {
                String requestType = request.getHeader("X-Requested-With");
                log.info("未登录或者token认证失败");
                //判断是否是ajax请求
                if (requestType != null && "XMLHttpRequest".equals(requestType)) {
                    response.getWriter().write(NO_LOGIN);
                } else {
                    //前後端分离不需要重定向
                    response.getWriter().write(NO_LOGIN);
                }
                return;
            }
        }
    }

    public boolean isNeedFilter(String uri, String[] includeUrls) {

        for (String includeUrl : includeUrls) {
            if (uri.startsWith(includeUrl)) {
                log.info("filter url:{}  需要认证token", uri);
                return true;
            }
        }
        return false;
    }

}
