package com.yu.myblog.config;
import com.yu.myblog.fliter.SessionFilter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @ProjectName: carwash
 * @Package: com.yu.carwash.config
 * @ClassName: SessionConfig
 * @Author: 钟洪强
 * @Description: session配置类
 * @Date: 2021/8/7 17:35
 * @Version: 1.0
 */
/*@Configuration*/
@Log4j2
public class SessionConfig {

    @Bean
    public FilterRegistrationBean someFilterRegistration1() {
        //新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 添加我们写好的过滤器
        registration.setFilter( new SessionFilter());
        // 设置过滤器的URL模式
        registration.addUrlPatterns("/*");
        return registration;
    }
}
