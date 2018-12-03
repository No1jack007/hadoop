package com.zhang.hadoop.service.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.DefaultCookieSerializer;

import javax.servlet.http.HttpSessionListener;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 600)
public class RedisSessionConfig {

    @Bean
    public HttpSessionListener httpSessionListener() {
        return new SessionCounter();
    }

    /**
     * 自定义返回给前端的Cookie的项目根路径
     *
     * @return
     */
//    @Bean
    public DefaultCookieSerializer defaultCookieSerializer() {
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
        defaultCookieSerializer.setCookiePath("/");
        return defaultCookieSerializer;
    }

    //    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }
} 