package com.elyzar.play.support.service;

import com.elyzar.play.support.domain.common.ChatUser;

import java.util.ArrayList;
import java.util.List;

// concrete mediator
public class ChatServiceImpl implements ChatService {

    private List<ChatUser> users;

    public ChatServiceImpl(){
        this.users=new ArrayList<>();
    }

    @Override
    public void addUser(ChatUser user){
        this.users.add(user);
    }

    @Override
    public void sendMessage(String msg, ChatUser user) {
        for(ChatUser u : this.users){
            //message should not be received by the user sending it
            if(u != user){
                u.receive(msg);
            }
        }
    }
}
