package com.zhang.hadoop.service.redis.redisSession;

import com.zhang.hadoop.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionCounter implements HttpSessionListener {

    @Autowired
    private RedisService redisService;

//    private static final Logger logger = LoggerFactory.getLogger(SessionCounter.class);

    /* Session创建事件 */
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("创建session链接");
    }

    /* Session失效事件 */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("销毁session链接");
        HttpSession session = event.getSession();
        if(null != session.getAttribute("USER_ID")){
            String userId = session.getAttribute("USER_ID").toString();
            String key =  "USER_ID" + userId;
            redisService.del(key);
        }
    }
}