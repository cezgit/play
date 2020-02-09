package com.elyzar.play.patterns.behavioral;

import com.elyzar.play.support.domain.computer.Computer;
import com.elyzar.play.support.domain.computer.HighEndComputerBuilder;
import com.elyzar.play.support.domain.computer.StandardComputerBuilder;

public class TemplateMethodPatternDemo {

    public static void main(String[] args) {

        System.out.println("TemplateMethodPatternDemo");
        Computer computer = new StandardComputerBuilder().buildComputer();
        computer.getComputerParts()
                .forEach((k, v) -> System.out.println("Part : " + k + " Value : " + v));

        computer = new HighEndComputerBuilder().buildComputer();
        computer.getComputerParts()
                .forEach((k, v) -> System.out.println("Part : " + k + " Value : " + v));

        System.out.println("======================================================");
    }
}
