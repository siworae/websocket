package com.siworae.websocket;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;


/**
 * @Description: websocket处理器
 * @param： null
 * @Return:
 * @Author: siworae
 * @Date: 2019/1/14 14:25
 */
public class MyWebSocketHandler extends TextWebSocketHandler {


    //已建立连接的用户
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();



    /**
     * 当新连接建立的时候，被调用
     * 连接成功时候，会触发页面上onOpen方法
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("afterConnectionEstablished");
        users.add(session);
        System.out.println("在线人数:zxrs:---->"+users.size());
        super.afterConnectionEstablished(session);
    }

    /**
     * 处理前端发送的文本信息
     * js调用websocket.send时候，会调用该方法
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        // 获取提交过来的消息详情
        System.out.println("收到用户 " + username + "的消息:" + message.toString());
        //回复一条信息，
        session.sendMessage(new TextMessage("reply msg:" + message.getPayload()));

    }

    /**
     * 当连接关闭时被调用
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("afterConnectionClosed");
        users.remove(session);
        System.out.println("zxrs:---->"+users.size());
        super.afterConnectionClosed(session, status);
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        System.out.println(users.toString());
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {

        for (WebSocketSession user : users) {
            if (user.getAttributes().get("WEBSOCKET_USERNAME").equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }else {
                        System.out.println("------------>连接关闭了!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

        }
    }

    public static void main(String[] args){
        System.out.println("中文");
    }
}
