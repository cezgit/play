package com.elyzar.play.support.domain.finance;

import java.math.BigDecimal;

public interface Discounter<B extends Number> {

    BigDecimal applyDiscount(BigDecimal amount);

    static Discounter<Number> christmasDiscounter =
            amount -> amount.multiply(BigDecimal.valueOf(0.9));

    static Discounter<Number> newYearDiscounter =
            amount -> amount.multiply(BigDecimal.valueOf(0.8));

    static Discounter<Number> easterDiscounter =
            amount -> amount.multiply(BigDecimal.valueOf(0.5));
}
