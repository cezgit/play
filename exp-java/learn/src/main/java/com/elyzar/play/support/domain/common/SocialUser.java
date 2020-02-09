package com.elyzar.play.support.domain.common;

import com.elyzar.play.support.service.ChatService;

public class SocialUser extends ChatUser {

    public SocialUser(ChatService med, String name) {
        super(med, name);
    }

    @Override
    public void send(String msg){
        System.out.println(this.name+": Sending Message="+msg);
        mediator.sendMessage(msg, this);
    }
    @Override
    public void receive(String msg) {
        System.out.println(this.name+": Received Message:"+msg);
    }
}
