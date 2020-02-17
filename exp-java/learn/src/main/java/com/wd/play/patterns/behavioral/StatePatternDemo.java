package com.wd.play.patterns.behavioral;

import com.wd.play.support.domain.media.mediaplayer.MediaPlayer;
import com.wd.play.support.domain.media.mediaplayer.MediaPlayerUI;
import com.wd.play.support.domain.ordering.Order;

public class StatePatternDemo {

    public static void main(String[] args) {
        System.out.println("StatePatternDemo");
//        MediaPlayer player = new MediaPlayer();
//        MediaPlayerUI ui = new MediaPlayerUI(player);
//        ui.init();
        System.out.println("======================================================");
        Order order = new Order("1");
        order.nextState();
        order.nextState();
        order.previousState();
        order.nextState();
        order.nextState();
        order.nextState();
    }
}
