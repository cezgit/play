package com.elyzar.play.support.domain.company;

public class Manager implements Associate {
    private String empId;
    private String name;
    private Double salary;
    private String department;

    public Manager(String empId, String name, Double salary) {
        this.empId = empId;
        this.name = name;
        this.salary = salary;
    }

    public Manager(String name) { this.name = name; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void showDetails() {
        System.out.println(this.toString());
    }
}
