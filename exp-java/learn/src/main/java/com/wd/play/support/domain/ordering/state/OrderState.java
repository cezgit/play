package com.wd.play.support.domain.ordering.state;

import com.wd.play.support.domain.ordering.Order;

public interface OrderState {
    void next(Order order);
    void previous(Order order);
    void printStatus();
}
