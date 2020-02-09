package com.elyzar.play.support.domain.common;

import com.elyzar.play.support.service.ChatService;

public abstract class ChatUser {

    protected ChatService mediator;
    protected String name;

    public ChatUser(ChatService med, String name){
        this.mediator=med;
        this.name=name;
    }

    public abstract void send(String msg);

    public abstract void receive(String msg);
}
