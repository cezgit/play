package com.wd.play.support.domain.ordering.state;

import com.wd.play.support.domain.ordering.Order;

public class ProcessingState implements OrderState {
    @Override
    public void next(Order order) {
        order.setState(new ShippedState());
    }

    @Override
    public void previous(Order order) {
        order.setState(new AckState());
    }

    @Override
    public void printStatus() {
        System.out.println("PROCESSING");
    }
}
