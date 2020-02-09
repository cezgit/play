package com.elyzar.play.functional;

import com.elyzar.play.support.domain.common.CollectionSamples;
import com.elyzar.play.support.domain.company.Employee;
import com.elyzar.play.support.util.CheckedFunction;
import com.elyzar.play.support.util.Either;
import io.vavr.CheckedFunction1;

import java.util.function.Function;

// https://www.baeldung.com/vavr-either
// https://www.baeldung.com/exceptions-using-vavr

public class StreamExceptions {
    public static void main(String[] args) {

        wrapCallThrowingExceptionInMethod();
        wrapCallThrowingExceptionWithCustomFunction();

        wrapCallThrowingExceptionWithCustomEitherAndLift();
        wrapCallThrowingExceptionWithCustomEitherAndLiftWithValue();

        checkedExceptionsUsingVavrLift();
        checkedExceptionsUsingVavrLiftTry();
    }

    private static void wrapCallThrowingExceptionWithCustomEitherAndLiftWithValue() {

        System.out.println("Use a custom Either.liftWithValue to wrap an Exception into the left side and keep the original into the right side");
        CollectionSamples.listOfEmployees().stream()
                .map(Either.liftWithValue(emp -> emp.salaryIncrementAndReturn(99d)))
                .forEach(System.out::println);
        System.out.println("------------------------------");
    }

    private static void wrapCallThrowingExceptionWithCustomEitherAndLift() {

        System.out.println("Use a custom Either.lift to wrap an Exception");
        CollectionSamples.listOfEmployees().stream()
            .map(Either.lift(emp -> emp.salaryIncrementAndReturn(99d)))
            .forEach(System.out::println);
        System.out.println("------------------------------");
    }

    private static void checkedExceptionsUsingVavrLiftTry() {

        System.out.println("Vavr CheckedFunction1.Try - Exceptions are wrapped into Try Failures");
        CollectionSamples.listOfEmployees().stream()
                .map(CheckedFunction1.liftTry(emp -> emp.salaryIncrementAndReturn(99d)))
                .forEach(System.out::println);
        System.out.println("------------------------------");
    }

    private static void checkedExceptionsUsingVavrLift() {

        System.out.println("Vavr CheckedFunction1.Lift - Exceptions are swallowed and wrapped by Option.None");
        CollectionSamples.listOfEmployees().stream()
                .map(CheckedFunction1.lift(emp -> emp.salaryIncrementAndReturn(99d)))
                // .map(k -> k.getOrElse(-1d)); // in case we want a value instead of Option.None
                .forEach(System.out::println);
        System.out.println("------------------------------");
    }

    private static void wrapCallThrowingExceptionWithCustomFunction() {
        System.out.println("Throw custom exception during Stream processing and stop");

        try {
            CollectionSamples.listOfEmployees().stream()
                    .map(wrap(emp -> emp.salaryIncrementAndReturn(99d)))
                    .forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("------------------------------");
    }

    private static void wrapCallThrowingExceptionInMethod() {
        System.out.println("Throw exception during Stream processing and continue");
        CollectionSamples.listOfEmployees().stream()
                .map(StreamExceptions::trySomething)
                .forEach(System.out::println);

        System.out.println("------------------------------");
    }

    private static Double trySomething(Employee employee) {
        try {
            employee.salaryIncrement(99d);
        } catch (Exception e) {
            System.out.println("Exception caught: "+e.getMessage());
        }
        return employee.getSalary();
    }

    // write your own general utility function that accepts a CheckedFunction
    private static <T,R> Function<T,R> wrap(CheckedFunction<T,R> checkedFunction) {
        return t -> {
            try {
                return checkedFunction.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
