package com.elyzar.play.support.domain.common;

import com.elyzar.play.support.domain.company.Employee;
import com.elyzar.play.support.domain.fruits.Apple;
import com.elyzar.play.support.domain.fruits.Fruit;
import com.elyzar.play.support.domain.ordering.Customer;
import com.elyzar.play.support.domain.ordering.Order;
import com.elyzar.play.support.util.GenericBuilder;

import java.util.List;

public class CollectionSamples {

    public static List<Fruit> listOfFruits() {
        Fruit apple1 = GenericBuilder.of(Apple::new)
                .with(Apple::setName, "big red apple")
                .with(Apple::setColor, "red")
                .with(Apple::setWeight, 200).build();

        Fruit apple2 = GenericBuilder.of(Apple::new)
                .with(Apple::setName, "big yellow apple")
                .with(Apple::setColor, "yellow")
                .with(Apple::setWeight, 200).build();

        Fruit apple3 = GenericBuilder.of(Apple::new)
                .with(Apple::setName, "red apple")
                .with(Apple::setColor, "red")
                .with(Apple::setWeight, 100).build();

        List<Fruit> fruits = List.of(apple1, apple2, apple3);
        return fruits;
    }

    public static List<Employee> listOfEmployees() {
        Employee john = new Employee("E123", "John Nhoj", 200.99, "IT");
        Employee south = new Employee("E223", "South Htuos", 299.99, "Sales");
        Employee reet = new Employee("E133", "Reet Teer", 300.99, "IT");
        Employee prateema = new Employee("E143", "Prateema Rai", 300.99, "Benefits");
        Employee yogen = new Employee("E323", "Yogen Rai", 200.99, "Sales");
        List<Employee> employees = List.of(john, south, reet, prateema, yogen);
        return employees;
    }

    public static List<Customer> listOfCustomersWithOrders() {
        Customer c1 = new Customer();
        c1.setOrders(List.of(new Order("1"), new Order("2"), new Order("3")));

        Customer c2 = new Customer();
        c2.setOrders(List.of(new Order("10"), new Order("11"), new Order("12")));

        return List.of(c1, c2);
    }
}
