package com.company;

import java.util.Scanner;

public class Calculator {
    private final String PROGRAM_NAME;

    public Calculator() {
        PROGRAM_NAME = "Простой консольный калькулятор, версия 0.0.1 (04/01/21)";
        System.out.println("Вас приветствует \"" + PROGRAM_NAME + "\"!");
        runCommandLine();
    }

    //Командная строка
    private void runCommandLine() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Введите комманду, help - справка:");
            String command = sc.nextLine().trim();
            String[] s = command.split("\\s+");

            if ("help".equalsIgnoreCase(command)) {
                help();
            } else if ("exit".equalsIgnoreCase(command)) {
                System.out.println(PROGRAM_NAME + " завершён.\n");
                return;
            } else if (s.length == 3) {

                try {
                    double a = Double.parseDouble(s[0]);
                    String operation = s[1];
                    double b = Double.parseDouble(s[2]);

                    System.out.println(a + " " + operation + " " + b + " = " +
                            arithmeticExecutor(a, operation, b));

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException | IllegalStateException e) {
                    System.out.println("Команда \"" + command + "\" не распознана, повторите ввод.");

                } catch (ArithmeticException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Команда \"" + command + "\" не распознана, повторите ввод.");
            }
        }
    }

    //Вывод справки
    private void help() {
        System.out.println("help - вывести данную справку;\n" +
                "exit - выход;\n" +
                "для выполнения операций с двумя числами\n" +
                "используйте формат ввода \"ЧИСЛО1 пробел ОПЕРАЦИЯ пробел ЧИСЛО2\",\n" +
                "где «+», «-», «*», «/», «^» символы математических операций.");
    }

    //Выполнение арифметических операций
    private double arithmeticExecutor(double a, String operation, double b) {
        switch (operation) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "^":
                return Math.pow(a, b);
            case "/":
                if (b != 0) {
                    return a / b;
                } else {
                    throw new ArithmeticException("Деление на ноль невозможно!");
                }
            default:
                throw new IllegalStateException();
        }
    }
}
