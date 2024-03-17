package com.kyeeego.stepikwebserver.services;

import java.util.ArrayList;
import java.util.List;

public class ChatService {

    private static ChatService self;

    public static ChatService instance() {
        if (self == null) {
            self = new ChatService();
        }

        return self;
    }

    private ChatService() {
    }

    private List<Connection> connections = new ArrayList<>();

    public void sendToEveryone(String msg) {
        for (var conn : connections) {
            conn.sendString(msg);
        }
    }

    public void add(Connection conn) {
        connections.add(conn);
    }

    public void remove(Connection conn) {
        connections.remove(conn);
    }
}
