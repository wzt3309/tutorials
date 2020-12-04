package io.zedw.config;

import io.zedw.handler.EchoHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author xingguan.wzt
 * @date 2020/12/04
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
            .addHandler(new EchoHandler(), "/echoHandler")
            // 握手拦截器 - 比如 将http session中的属性复制到 websocketSession中
            //.setHandshakeHandler()
            //.addInterceptors()
            // 跨域请求
            //.setAllowedOrigins()
            // 允许使用 sockJS
            .withSockJS()
        ;
    }

}
