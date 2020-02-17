package com.wd.play.support.domain.ordering;

import com.wd.play.support.domain.ordering.state.AckState;
import com.wd.play.support.domain.ordering.state.OrderState;

public class Order {

    private String orderId;
    private OrderState state = new AckState();

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void nextState() {
        state.next(this);
        state.printStatus();
    }

    public void previousState() {
        state.previous(this);
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", state=" + state +
                '}';
    }
}
