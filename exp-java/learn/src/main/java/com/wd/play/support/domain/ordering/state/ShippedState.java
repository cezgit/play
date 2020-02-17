package com.wd.play.support.domain.ordering.state;

import com.wd.play.support.domain.ordering.Order;

public class ShippedState implements OrderState {
    @Override
    public void next(Order order) {
        order.setState(new DeliveredState());
    }

    @Override
    public void previous(Order order) {
        order.setState(new ProcessingState());
    }

    @Override
    public void printStatus() {
        System.out.println("SHIPPED");
    }
}
