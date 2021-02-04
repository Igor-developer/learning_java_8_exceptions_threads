package com.company;

public class Main {

    public static void main(String[] args) {

        runTask1();
        runTask2();
        runTask3();
    }

    //Задание 1.
    //Сделать калькулятор с функциями плюс(1), минус(2), умножить(3) и разделить(4).
    //Сделать в калькуляторе проверку на деление на 0 и на ввод не цифр.
    //(имеется ввиду отлавливание ошибок “деление на 0” и “ввод неверных цифр” с помощью блока try-catch.
    private static void runTask1() {
        new Calculator();
    }

    //Задание 2.
    //К задаче с лифтом из прошлого урока сделать свой класс ошибок TooBigWeightException,
    //который будет символизировать что слишком большой вес и дальше не поедем.
    //Обработать её соответствующим образом.
    private static void runTask2() {
        new Lift("Программа управления очередью в лифте", 500, true);
        new Lift("Программа управления очередью в лифте", 500, false);
    }

    //Задание 3.
    //Сделать потоки, внутри них цикл for на 1000 итераций.
    //В них вывести “first” + i, “second” + i соответственно.
    private static void runTask3() {
        new Thread("first") {
            @Override
            public void run() {
                loop(1000);
            }
        }.start();

        new Thread("second") {
            @Override
            public void run() {
                loop(1000);
            }
        }.start();
    }

    private static void loop(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
