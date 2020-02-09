package com.wd.play.patterns.behavioral;

import com.wd.play.support.domain.finance.*;

import java.math.BigDecimal;
import java.util.List;

public class StrategyPatternDemo {

    public static void main (String[] args) {

        System.out.println("StrategyPatternDemo");

        payBillUsingDifferentPaymentStrategies();
        System.out.println("------------------------------------------------------");
        discounters();

        System.out.println("======================================================");
    }

    private static void discounters() {

        BigDecimal amountBeforeDiscounts = BigDecimal.valueOf(100);
        BigDecimal discount = Discounter.christmasDiscounter.applyDiscount(amountBeforeDiscounts);
        System.out.println("Christmas discount: "+discount);

        DiscounterCombo christmasDiscounter = amount -> {
            BigDecimal result = amount.multiply(BigDecimal.valueOf(0.9));
            System.out.println(String.format("christmas discounting of %f to amount %f yields %f:", 0.9, amount, result));
            return result;
        };
        DiscounterCombo easterDiscounter = amount -> {
            BigDecimal result = amount.multiply(BigDecimal.valueOf(0.8));
            System.out.println(String.format("easter discounting of %f to amount %f yields %f:", 0.8, amount, result));
            return result;
        };
        DiscounterCombo newYearsDiscounter = amount -> {
            BigDecimal result = amount.multiply(BigDecimal.valueOf(0.5));
            System.out.println(String.format("newYear discounting of %f to amount %f yields %f:", 0.5, amount, result));
            return result;
        };

        List<DiscounterCombo> discounters = List.of(
            christmasDiscounter,
            easterDiscounter,
            newYearsDiscounter
        );

        // Pay special attention to the first reduce argument. When no discounts provided, we need to return the unchanged value.
        // This can be achieved by providing an identity function as the default discounter.
        DiscounterCombo combinedDiscounters = discounters
            .stream()
            .reduce(v -> v, DiscounterCombo::combine);

        BigDecimal discountAmount = combinedDiscounters.apply(amountBeforeDiscounts);
        System.out.println(discountAmount);
    }

    private static void payBillUsingDifferentPaymentStrategies() {
        Bill bill = new Bill();
        bill.addBillItem(new BillItem("Milk", 200));
        bill.addBillItem(new BillItem("Bread", 150));
        bill.pay(PaymentMethodFactory.getPaymentMethod("credit"));
    }
}
