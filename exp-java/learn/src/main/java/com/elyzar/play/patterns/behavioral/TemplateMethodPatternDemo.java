package com.wd.play.patterns.behavioral;

import com.wd.play.support.domain.computer.Computer;
import com.wd.play.support.domain.computer.HighEndComputerBuilder;
import com.wd.play.support.domain.computer.StandardComputerBuilder;

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
