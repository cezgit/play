package com.wd.play.support.service;

import com.wd.play.support.domain.common.ChatUser;

// mediator interface
public interface ChatService {
    public void sendMessage(String msg, ChatUser user);
    void addUser(ChatUser user);
}
