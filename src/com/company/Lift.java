package com.company;

import java.util.*;

public class Lift {
    String programName;
    LinkedList<Person> persons;
    private boolean reorganization; //указывает нужно ли людям в лифте перестраиваться в порядке выхода на этажах
    double payload;

    public Lift(String programName, double payload, boolean reorganization) {
        this.programName = programName;
        persons = new LinkedList<>();
        this.reorganization = reorganization;
        this.payload = payload;

        runCommandLine();
    }

    //Запуск командной строки
    void runCommandLine() {
        System.out.println("Вас приветствует \"" + programName + "\"!\n"
                + "Люди в лифте " + (reorganization ? "" : "не ") + "построены в порядке выхода на этажах.");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Введите комманду (help для вызова справки):");
            String command = sc.nextLine().trim().toLowerCase();
            switch (command) {
                case "help":
                    help();
                    break;
                case "1":
                    letPassInLift();
                    break;
                case "2":
                    displayQueue();
                    break;
                case "3":
                    removePerson();
                    break;
                case "4":
                    System.out.println(programName + " завершена.\n");
                    return;
                default:
                    System.out.println("Комманда \"" + command + "\" не распознана, повторите ввод:");
            }
        }
    }

    //Вывод справки
    void help() {
        System.out.println("help - вывести данную справку;\n" +
                "1 – добавить человека в очередь;\n" +
                "2 - вывести список людей в очереди;\n" +
                "3 – удалить человека из очереди;\n" +
                "4 – завершить программу.");
    }

    //Впустить Person в лифт
    private void letPassInLift() {
        try {
            addPerson();
        } catch (TooBigWeightException e) {
            System.out.println("Доступ в лифт запрещён, так как превышена максимальная грузоподъёмность лифта - " + payload + " кг!");
        }
    }

    //Добавление Person в очередь
    private void addPerson() throws TooBigWeightException {
        String name = inputPersonName();

        double weight = inputPersonWeight();
        if (getPersonsTotalWeight() + weight > payload) {
            throw new TooBigWeightException();
        }

        int floor = inputPersonFloor();

        Person person = new Person(name, floor, weight);

        if (persons.offerFirst(person)) {
            System.out.println(person + " добавлен.");
        }
    }

    //Ввод имени Person из консоли
    private String inputPersonName() {
        Scanner sc = new Scanner(System.in);

        String name;
        do {
            System.out.println("Введите имя человека:");
            name = sc.nextLine().trim();

            if (name.length() == 0) {
                System.out.print("Ничего не введено. ");
            } else {
                break;
            }
        } while (true);

        return name;
    }

    //Ввод веса Person из консоли
    private double inputPersonWeight() {
        Scanner sc = new Scanner(System.in);

        double weight;
        do {
            System.out.println("Введите вес человека:");
            try {
                weight = Double.parseDouble(sc.nextLine().trim());
                if (weight <= 0) {
                    System.out.print("Вес не может быть ноль или меньше нуля! ");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Неверный ввод! ");
            }
        } while (true);

        return weight;
    }

    //Ввод этажа Person из консоли
    private int inputPersonFloor() {
        Scanner sc = new Scanner(System.in);

        int floor;
        do {
            System.out.println("Введите этаж, на котором человек выходит:");
            try {
                floor = Integer.parseInt(sc.nextLine().trim());
                if (floor <= 0) {
                    System.out.print("Этаж не может быть ноль или меньше нуля! ");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Неверный ввод! ");
            }
        } while (true);

        return floor;
    }

    //Отображение очереди
    private void displayQueue() {
        if (reorganization) {
            Collections.sort(persons);  //построение людей в порядке выхода из лифта
        }

        if (persons.isEmpty()) {
            System.out.println("Нет людей.");
            return;
        }

        System.out.println("Порядок людей:");
        for (int i = 0; i < persons.size(); i++) {
            System.out.println((i + 1) + " - " + persons.get(i));
        }

    }

    //Принудительное удаление Person
    private void removePerson() {
        displayQueue();

        if (!persons.isEmpty()) {
            System.out.println(persons.removeFirst() + ", принудительно удалён из лифта.");
        }

    }

    //Вычисление текущей суммарной массы Person
    private double getPersonsTotalWeight() {
        double total_weight = 0;
        for (Person i : persons) {
            total_weight += i.getWeight();
        }

        return total_weight;
    }

    private static class TooBigWeightException extends Exception {
    }
}
