package com.elyzar.play.functional;

import com.elyzar.play.support.domain.company.Company;
import com.elyzar.play.support.domain.company.Department;
import com.elyzar.play.support.domain.company.Manager;

import java.util.List;
import java.util.Optional;

public class OptionalExamples {

    public static void main(String[] args) {

        optionalOfNullable("foo");
        optionalOfNullable(null);
        optionalOrElse(List.of("four", "five"));
        optionalOrElse(List.of("five", "three", "four"));
        optionalIfPresentWithSupplier(List.of("five", "three", "four"));
        optionalFlatMap();
        optionalOrElseGet();

    }

    private static void optionalOrElseGet() {

        // orElse() will always call the given function whether you want it or not, regardless of Optional.isPresent() value
        // orElseGet() will only call the given function when the Optional.isPresent() == false

        Company company = null;
        System.out.println("Using orElse");
        System.out.println(Optional.ofNullable(company).orElse(new Company("Company: orElse")));
        System.out.println("Using orElseGet");
        System.out.println(Optional.ofNullable(company).orElseGet(() -> new Company("Company: orElseGet")));
        System.out.println("------------------------------");
    }

    private static void optionalFlatMap() {
        Company co = new Company();
        Department department = new Department("HR");
        Manager manager = new Manager("Mr. Slate");
        department.setBoss(manager);
        co.setDepartment(department);

        //  Company Dept: Optional[Department{boss=Manager{name='Mr. Slate'}}]
        System.out.println("Company Dept: " + co.getDepartment());

        //  Company Dept Manager: Optional[Optional[Manager{name='Mr. Slate'}]]
        System.out.println("Company Dept Manager: " + co.getDepartment().map(Department::getBoss));

        System.out.println(co.getDepartment()             // Optional<Department>
                    .flatMap(Department::getBoss)         // Optional<Manager>
                    .map(Manager::getName));              // Optional<String>

        Optional<Company> company = Optional.of(co);
        System.out.println(company                        // Optional<Company>
                        .flatMap(Company::getDepartment)  // Optional<Department>
                        .flatMap(Department::getBoss)     // Optional<Manager>
                        .map(Manager::getName)            // Optional<String>
        );
        System.out.println("------------------------------");
    }

    private static void optionalIfPresentWithSupplier(List<String> list) {
        System.out.println("Optional findFirst with a Supplier");
        Optional<String> first = list.stream()
                        .filter(s -> s.length() % 2 == 0)
                        .findFirst();
        first.ifPresent(val -> System.out.println("Found an even-length string"));
        System.out.println("------------------------------");
    }

    private static void optionalOrElse(List<String> list) {
        System.out.println(String.format("Find an odd length string in %s and return it or return a message with orElse", list));
        System.out.println(list.stream().filter(s -> s.length() % 2 != 0).findFirst().orElse("no odd length strings"));
        System.out.println("------------------------------");
    }

    private static void optionalOfNullable(String s) {
        System.out.println("Create an optional of nullable from "+s);
        System.out.println(createOptionalTheEasyWay(s));
        System.out.println("------------------------------");
    }

    private static <T> Optional<T> createOptionalTheEasyWay(T value) {
        return Optional.ofNullable(value);
    }
}
