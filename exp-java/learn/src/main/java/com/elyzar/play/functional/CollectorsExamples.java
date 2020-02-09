package com.elyzar.play.functional;

import com.elyzar.play.support.domain.common.CollectionSamples;
import com.elyzar.play.support.domain.company.Employee;

import java.text.DecimalFormat;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.*;

public class CollectorsExamples {
    public static void main(String[] args) {

        collectorsReducingOperations();
        collectorsMappingAndJoining();
        collectorsGrouping();
        collectorsPartitioning();
    }

    private static void collectorsPartitioning() {

        List<Employee> employees = CollectionSamples.listOfEmployees();

        Double averageSalary = employees.stream().collect(averagingDouble(Employee::getSalary));
        Map<Boolean, List<Employee>> partitionedEmployees = employees.stream()
                .collect(partitioningBy(e -> e.getSalary() > averageSalary));
        System.out.println("Collectors.partitioningBy - finding employees with a salary greater than the average salary: \n"+partitionedEmployees);


        System.out.println("------------------------------");
    }

    private static void collectorsGrouping() {

        List<Employee> employees = CollectionSamples.listOfEmployees();


        System.out.println("Collectors.groupingBy - generate a map of employees keyed in by department: ");
        Map<String, List<Employee>> deptEmps = employees.stream()
                .collect(groupingBy(Employee::getDepartment));
        System.out.println(deptEmps+"\n");

        Map<String, Long> deptEmpsCount = employees.stream()
                .collect(groupingBy(Employee::getDepartment, counting()));
        System.out.println("Collectors.groupingBy and counting - counting employees by department: "+deptEmpsCount);

        Map<String, Long> deptEmpsCountConcurrent = employees.stream()
                .collect(groupingByConcurrent(Employee::getDepartment, counting()));
        System.out.println("Collectors.groupingByConcurrent and counting - counting employees by department: "+deptEmpsCountConcurrent);

        Map<String, Double> averageSalaryDeptSorted = employees.stream()
                .collect(groupingBy(Employee::getDepartment, TreeMap::new, averagingDouble(Employee::getSalary)));
        System.out.println("Collectors.groupingBy and averagingDouble - average salary per department, sorted by department: "+ averageSalaryDeptSorted);

        System.out.println("------------------------------");
    }

    private static void collectorsMappingAndJoining() {

        List<Employee> employees = CollectionSamples.listOfEmployees();

        System.out.println("Collectors.mapping - collect all employee names to a list: "
                +employees.stream().collect(mapping(Employee::getName, toList())));

        System.out.println("Collectors.joining - collect all employee names in a specific format: "
                +employees.stream().map(Employee::getName).collect(joining(", ", "Employees = {", "}")));

        System.out.println("------------------------------");
    }

    private static void collectorsReducingOperations() {
        System.out.println("Collectors reducing operations");

        List<Employee> employees = CollectionSamples.listOfEmployees();
        System.out.println("Collectors.averaging - average employee salary: "
            +employees.stream().collect(averagingDouble(Employee::getSalary)));

        System.out.println("Collectors.averaging after collectingAndThen - average salary: "
            +employees.stream().collect(collectingAndThen(averagingDouble(Employee::getSalary), new DecimalFormat("0.000")::format)));

        System.out.println("Collectors.summing - total employees salary: "
            +employees.stream().collect(summingDouble(Employee::getSalary)));

        System.out.println("Collectors.comparing after collectingAndThen - max salary: "
            +employees.stream().collect(collectingAndThen(maxBy(comparingDouble(Employee::getSalary)), emp -> emp.get().getSalary())));

        System.out.println("Collectors.summarizingDouble to generate DoubleSummaryStatistics - all stats: ");
        DoubleSummaryStatistics statistics = employees.stream().collect(summarizingDouble(Employee::getSalary));
        System.out.println("        Average: " + statistics.getAverage() + ", Total: " + statistics.getSum() +
            ", Max: " + statistics.getMax() + ", Min: "+ statistics.getMin());

        System.out.println("------------------------------");
    }
}
