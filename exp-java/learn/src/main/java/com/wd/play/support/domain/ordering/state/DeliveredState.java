package com.wd.play.support.domain.ordering.state;

import com.wd.play.support.domain.ordering.Order;

public class DeliveredState implements OrderState {
    @Override
    public void next(Order order) {
        System.out.println("Order has been delivered. No next state exists.");
    }

    @Override
    public void previous(Order order) {
        order.setState(new ShippedState());
    }

    @Override
    public void printStatus() {
        System.out.println("DELIVERED");
    }
}
