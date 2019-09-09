package com.biye.rws.controller;

import com.biye.rws.annotation.WsController;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@WsController("/ws/eco")
public class AnnotatedEchoController implements WebSocketHandler {
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(session.receive().
                map(msg -> session.textMessage("SERVER RECEIVED:" + msg.getPayloadAsText())));
    }
}
