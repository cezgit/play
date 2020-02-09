package com.elyzar.play.support.domain.company;

import java.util.Optional;

public class Company {
    private String name;
    private Department department;

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

    public Optional<Department> getDepartment() { return Optional.ofNullable(department); }
    public void setDepartment(Department department) { this.department = department; }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", department=" + department +
                '}';
    }
}
