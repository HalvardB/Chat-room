package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.

    @JSONField(name = "username")
    private String userName;

    @JSONField(name = "msg")
    private String message;

    @JSONField(name = "onlineCount")
    private int onlineCount;

    @JSONField(name = "type")
    private String type = "SPEAK";

    public Message(){};

    public Message(String userName, String message, int onlineCount, String type) {
        this.userName = userName;
        this.message = message;
        this.onlineCount = onlineCount;
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public String getType() {
        return type;
    }
}
