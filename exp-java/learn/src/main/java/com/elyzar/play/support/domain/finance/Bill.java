package com.elyzar.play.support.domain.finance;

import java.util.ArrayList;
import java.util.List;

// The Context class
public class Bill {
    private List<BillItem> lineItems = new ArrayList<>();
    public void addBillItem(BillItem billItem) {
        lineItems.add(billItem);
    }
    public void removeBillItem(BillItem billItem) {
        lineItems.remove(billItem);
    }
    public int getCostInCents() {
        return lineItems.stream().mapToInt(item -> item.getCostInCents()).sum();
    }
    public void pay(PaymentMethod method) {
        method.pay(getCostInCents());
    }
}
