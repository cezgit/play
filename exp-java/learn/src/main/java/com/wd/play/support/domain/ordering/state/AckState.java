package com.wd.play.support.domain.ordering.state;

import com.wd.play.support.domain.ordering.Order;

public class AckState implements OrderState {
    @Override
    public void next(Order order) {
        order.setState(new ProcessingState());
    }

    @Override
    public void previous(Order order) {
        System.out.println("No previous state exists");
    }

    @Override
    public void printStatus() {
        System.out.println("ACKNOWLEDGED");
    }
}
