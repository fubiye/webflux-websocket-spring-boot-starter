package com.biye.rws.conf;

import com.biye.rws.handler.EchoHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocketConfiguration {

    private Logger logger = LoggerFactory.getLogger(WebSocketConfiguration.class);

    @Bean
    public WebSocketHandler echoHandler(){
        return new EchoHandler();
    }

    @Bean
    public HandlerMapping webScoketMapping(){
        Map<String,WebSocketHandler> pathToHanlder = new HashMap<>();
        pathToHanlder.put("/ws/echo",echoHandler());
        logger.info("Add handler mapping: /ws/echo ...");
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
        mapping.setUrlMap(pathToHanlder);
        return mapping;
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter(){
        return new WebSocketHandlerAdapter();
    }

}
