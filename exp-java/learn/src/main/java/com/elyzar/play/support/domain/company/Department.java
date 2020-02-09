package com.elyzar.play.support.domain.company;

import java.util.Optional;

public class Department {
    private Manager boss;
    private String name;
    public Department(String name) { this.name = name;}
    public Optional<Manager> getBoss() { return Optional.ofNullable(boss); }
    public void setBoss(Manager boss) { this.boss = boss; }

    @Override
    public String toString() {
        return "Department{" +
                "boss=" + boss +
                ", name='" + name + '\'' +
                '}';
    }
}
