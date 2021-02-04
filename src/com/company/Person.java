package com.company;

public class Person implements Comparable<Person> {
    private String name;
    private double weight;
    private int floor;

    public Person(String name, int floor, double weight) {
        this.name = name;
        this.weight = weight;
        this.floor = floor;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Person o) {
        return this.floor - o.floor;
    }

    @Override
    public String toString() {
        return name +
                (weight != 0 ? ", " + weight + " кг" : "") +
                (floor != 0 ? ", " + floor + " этаж" : "");
    }
}
