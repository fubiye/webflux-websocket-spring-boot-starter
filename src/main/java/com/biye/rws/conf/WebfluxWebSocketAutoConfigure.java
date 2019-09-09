package com.biye.rws.conf;

import com.biye.rws.WsControllerRegister;
import com.biye.rws.annotation.EnableWebSocket;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@EnableWebSocket
@ConditionalOnMissingBean(WsControllerRegister.class)
public class WebfluxWebSocketAutoConfigure {

}
