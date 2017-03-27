package com.example.wawakaka.mimicchat;

/**
 * Created by Wawakaka on 3/25/2017.
 */

public class Message {
    private String sender;
    private String message;
    private String time;
    private String webTitle;
    private String webDesc;
    private boolean is_from_me;

    public Message() {
    }

    public Message(String sender, String message, boolean is_from_me, String time) {
        this.sender = sender;
        this.message = message;
        this.is_from_me = is_from_me;
        this.time = time;
    }

    public Message(String sender, String message, String time, String webTitle, String webDesc, boolean is_from_me) {
        this.sender = sender;
        this.message = message;
        this.time = time;
        this.webTitle = webTitle;
        this.webDesc = webDesc;
        this.is_from_me = is_from_me;
    }


    public String getWebTitle() {
        return webTitle;
    }

    public String getWebDesc() {
        return webDesc;
    }

    public String getTime() {
        return time;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public boolean is_from_me() {
        return is_from_me;
    }


    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public void setWebDesc(String webDesc) {
        this.webDesc = webDesc;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setIs_from_me(boolean is_from_me) {
        this.is_from_me = is_from_me;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
