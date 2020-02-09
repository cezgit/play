package com.elyzar.play.support.domain.finance;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

public interface DiscounterCombo extends UnaryOperator<BigDecimal> {

    default DiscounterCombo combine(DiscounterCombo after) {
        // returns a discounterCombo function which first applies THIS's discount to the value yielding a result
        // and then applying the after's discount to the result
        return value -> {
            System.out.println("value: "+value);
            return after.apply(this.apply(value));
        };
    }
}
