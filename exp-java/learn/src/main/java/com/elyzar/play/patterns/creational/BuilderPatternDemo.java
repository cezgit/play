package com.elyzar.play.patterns.creational;

import com.elyzar.play.support.domain.finance.BankAccount;
import com.elyzar.play.support.domain.finance.BankAccountBuilderFunctional;

/**
 * https://stackoverflow.com/questions/31754786/how-to-implement-the-builder-pattern-in-java-8
 */
public class BuilderPatternDemo {

    public static void main(String[] args) {

        System.out.println("BuilderPatternDemo");
        bankAccountBuilder();
        System.out.println("------------------------------------------------------");
        bankAccountBuilderFunctional();
        System.out.println("------------------------------------------------------");
        bankAccountBuilderGenericFunctional();
        System.out.println("======================================================");
    }

    private static void bankAccountBuilderGenericFunctional() {
        BankAccount account = GenericBuilder.of(BankAccount::new)
                .with(BankAccount::setAccountNumber, 100l).build();
        System.out.println("Account: "+account);
    }

    private static void bankAccountBuilderFunctional() {
        // https://medium.com/beingprofessional/think-functional-advanced-builder-pattern-using-lambda-284714b85ed5
        BankAccount account = new BankAccountBuilderFunctional()
                .with($ -> {
                    $.accountNumber = 1;
                    $.owner = "Me";
                    $.branch = "Some Branch";
                }).createAccount();
        System.out.println(account);
    }

    private static void bankAccountBuilder() {
        BankAccount account = new BankAccount.Builder(1234L)
                .withOwner("Marge")
                .atBranch("Springfield")
                .openingBalance(100)
                .atRate(2.5)
                .build();

        System.out.println(String.format("Account created: %s", account));

        BankAccount anotherAccount = new BankAccount.Builder(4567L)
                .withOwner("Homer")
                .atBranch("Springfield")
                .openingBalance(100)
                .atRate(2.5)
                .build();

        System.out.println(String.format("Account created: %s", anotherAccount));
    }
}
