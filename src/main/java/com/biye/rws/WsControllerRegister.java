package com.biye.rws;

import com.biye.rws.annotation.WsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

public class WsControllerRegister extends ApplicationObjectSupport implements SmartInitializingSingleton {
    private Logger logger = LoggerFactory.getLogger(WsControllerRegister.class);
    @Override
    public void afterSingletonsInstantiated() {
        printStartupInfos();
        ApplicationContext ctx = getApplicationContext();
        if(null == ctx) return;
        String[] beanNames = ctx.getBeanNamesForAnnotation(WsController.class);
        for(String beanName: beanNames){
            logger.info("bean name: " + beanName);
        }

    }

    private void printStartupInfos(){
        logger.info("******************************************************************");
        logger.info("*  Auto configuring WebSocket Handlers......");
        logger.info("******************************************************************");
    }
}
