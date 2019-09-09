package com.biye.rws.conf;

import com.biye.rws.annotation.WsController;
import com.biye.rws.controller.EchoWsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocketConfiguration implements ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(WebSocketConfiguration.class);

    private ApplicationContext ctx;

    @Bean
    public WebSocketHandler echoHandler(){
        return new EchoWsController();
    }

    @Bean
    public HandlerMapping webScoketMapping(){
        Map<String,WebSocketHandler> pathToHanlder = new HashMap<>();
        pathToHanlder.put("/ws/echo",echoHandler());
        logger.info("Add handler mapping: /ws/echo ...");
        printStartupInfos();
        String[] beanNames = ctx.getBeanNamesForAnnotation(WsController.class);
        for(String beanName: beanNames){
            logger.info("bean name: " + beanName);
            WebSocketHandler handler = ctx.getBean(beanName, WebSocketHandler.class);
            WsController controller = AnnotatedElementUtils.findMergedAnnotation(handler.getClass(), WsController.class);
            pathToHanlder.put(controller.value(),handler);
        }
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
        mapping.setUrlMap(pathToHanlder);
        return mapping;
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter(){
        return new WebSocketHandlerAdapter();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    private void printStartupInfos(){
        logger.info("******************************************************************");
        logger.info("*  Auto configuring WebSocket Handlers......");
        logger.info("******************************************************************");
    }
}
