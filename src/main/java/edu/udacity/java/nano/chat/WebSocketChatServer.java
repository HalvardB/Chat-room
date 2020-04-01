package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String msg) throws IOException {
        //TODO: add send message method.
        for(Session s : onlineSessions.values()){
            s.getBasicRemote().sendText(msg);
        }
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session s) throws IOException {
        //TODO: add on open connection.
        onlineSessions.put(s.getId(), s);
        Message message = new Message(null, null, onlineSessions.size(), "ENTER");
        sendMessageToAll(JSON.toJSONString(message));
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(String jsonStr) throws IOException {
        //TODO: add send message.
        Message message = JSON.parseObject(jsonStr, Message.class);
        message.setOnlineCount(onlineSessions.values().size());
        sendMessageToAll(JSON.toJSONString(message));
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session s) throws IOException {
        //TODO: add close connection.
        onlineSessions.remove(s.getId());
        s.close();
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

}
