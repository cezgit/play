package com.elyzar.play.patterns.behavioral;

import com.elyzar.play.support.domain.common.ChatUser;
import com.elyzar.play.support.domain.common.SocialUser;
import com.elyzar.play.support.service.ChatService;
import com.elyzar.play.support.service.ChatServiceImpl;

public class MediatorPatternDemo {

    public static void main(String[] args) {
        ChatService mediator = new ChatServiceImpl();
        ChatUser user1 = new SocialUser(mediator, "Pankaj");
        ChatUser user2 = new SocialUser(mediator, "Lisa");
        ChatUser user3 = new SocialUser(mediator, "Saurabh");
        ChatUser user4 = new SocialUser(mediator, "David");
        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);

        user1.send("Hi All");
    }
}
