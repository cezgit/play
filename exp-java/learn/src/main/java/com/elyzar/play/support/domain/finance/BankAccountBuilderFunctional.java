package com.elyzar.play.support.domain.finance;

import java.util.function.Consumer;

public class BankAccountBuilderFunctional {

    public long accountNumber;
    public String owner;
    public String branch;
    public double balance;
    public double interestRate;

    public BankAccountBuilderFunctional with(Consumer<BankAccountBuilderFunctional> builderFunction) {
        builderFunction.accept(this);
        return this;
    }

    public BankAccount createAccount() {
        return new BankAccount(accountNumber, owner, branch, balance, interestRate);
    }
}
