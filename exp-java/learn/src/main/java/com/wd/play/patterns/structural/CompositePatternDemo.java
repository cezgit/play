package com.wd.play.patterns.structural;

import com.wd.play.support.domain.company.CompanyDirectory;
import com.wd.play.support.domain.company.Employee;
import com.wd.play.support.domain.company.Manager;

public class CompositePatternDemo {
    public static void main(String[] args) {

        System.out.println("CompositePatternDemo");

        Employee dev1 = new Employee("100", "Lokesh Sharma", 100d);
        Employee dev2 = new Employee("101", "Vinay Sharma", 500d);
        CompanyDirectory engDirectory = new CompanyDirectory();
        engDirectory.addAssociate(dev1);
        engDirectory.addAssociate(dev2);

        Manager man1 = new Manager("200", "Kushagra Garg", 1000d);
        Manager man2 = new Manager("201", "Vikram Sharma ", 5000d);

        CompanyDirectory accDirectory = new CompanyDirectory();
        accDirectory.addAssociate(man1);
        accDirectory.addAssociate(man2);

        CompanyDirectory directory = new CompanyDirectory();
        directory.addAssociate(engDirectory);
        directory.addAssociate(accDirectory);
        directory.showDetails();
        System.out.println("======================================================");
    }
}
