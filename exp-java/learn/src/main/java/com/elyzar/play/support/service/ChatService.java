package com.elyzar.play.support.service;

import com.elyzar.play.support.domain.common.ChatUser;

// mediator interface
public interface ChatService {
    public void sendMessage(String msg, ChatUser user);
    void addUser(ChatUser user);
}
