package com.wd.play.support.domain.finance;

public class Cash implements PaymentMethod {
    @Override
    public void pay(int cents) {
        System.out.println("Payed " + cents + " cents using cash");
    }
}
