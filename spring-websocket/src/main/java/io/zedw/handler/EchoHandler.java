package io.zedw.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author xingguan.wzt
 * @date 2020/12/04
 */
@Slf4j
public class EchoHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("Receive message: {}", message.getPayload());
        session.sendMessage(message);
    }
}
