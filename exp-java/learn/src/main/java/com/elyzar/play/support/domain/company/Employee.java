package com.elyzar.play.support.domain.company;

public class Employee implements Associate{

    private String empId;
    private String name;
    private Double salary;
    private String department;

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String empId, String name, Double salary, String department) {
        this.empId = empId;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public Employee(String id, String name, double salary) {
        this.empId = id;
        this.name = name;
        this.salary = salary;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public void salaryIncrement(double salaryIncrement) {
        if(salaryIncrement < 100) {
            throw new RuntimeException("salaryIncrement has to be greater than 100");
        }
        salary += salaryIncrement;
    }

    public Double salaryIncrementAndReturn(double salaryIncrement) {
        salaryIncrement(salaryIncrement);
        return getSalary();
    }

    @Override
    public void showDetails() {
        System.out.println(this.toString());
    }
}
