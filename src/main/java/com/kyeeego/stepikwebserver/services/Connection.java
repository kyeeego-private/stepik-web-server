package com.kyeeego.stepikwebserver.services;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

@WebSocket
public class Connection {
    private Session session;

    @OnWebSocketConnect
    public void onOpen(Session session) {
        ChatService.instance().add(this);
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        ChatService.instance().sendToEveryone(data);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        ChatService.instance().remove(this);
    }

    public void sendString(String data) {
        try {
            session.getRemote().sendString(data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
