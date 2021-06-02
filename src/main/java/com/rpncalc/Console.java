package com.rpncalc;

import java.util.Scanner;

public class Console {

    public static void main(String[] args) {

        System.out.println("======================================================");
        System.out.println("Reverse Polish Notation Calculator                 ");
        System.out.println("Supported operators: +, -, *, /, sqrt, clear, undo ");
        System.out.println("Please place space between numbers and operators.  ");
        System.out.println("Enter 'q' to exit                                  ");
        System.out.println("======================================================");

        Calculator calculator = new Calculator();
        while (true) {
            String line = new Scanner(System.in).nextLine();

            if ("q".equals(line)) {
                break;

            }
            ExecuteResult result = calculator.execute(line);
            result.display();
        }
    }
}
