package com.siworae.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Description: websocket配置
 * @param： null
 * @Return:
 * @Author: siworae
 * @Date: 2019/1/14 14:25
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        String websocket_url = "/websocket/socketServer.do";
        registry.addHandler(new MyWebSocketHandler(), websocket_url).addInterceptors(new MyHandshakeInterceptor());

        String sockjs_url = "/sockjs/socketServer.do";
        registry.addHandler(new MyWebSocketHandler(), sockjs_url).addInterceptors(new MyHandshakeInterceptor()).withSockJS();

    }

}

