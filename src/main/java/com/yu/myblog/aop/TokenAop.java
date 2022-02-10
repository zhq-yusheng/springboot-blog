package com.yu.myblog.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yu.myblog.utils.ResultDao;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.aop
 * @ClassName: TokenAop
 * @Author: 钟洪强
 * @Description: 将token切面到controller中
 * @Date: 2022/1/26 9:11
 * @Version: 1.0
 */
@Aspect
@Component
public class TokenAop {

    RedisTemplate redisTemplate;

    @Around("execution(* com.yu.myblog.controller.IndexController.*(..))") //环绕增强
    public Object aroundIndexController(ProceedingJoinPoint pt) throws Throwable {
        System.out.println("执行前");
        Object proceed = pt.proceed();//执行方法相当于动态代理中的invoke方法 返回的对象什么都不是是个null
        System.out.println("执行后");
        ResultDao resultDao = (ResultDao) proceed;
        /*if(resultDao.getToken() == null){
            redisTemplate.opsForValue().get("");
        }*/
        //System.out.println(proceed);
        return proceed;
    }
}
