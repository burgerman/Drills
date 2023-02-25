package com.wil.practice.test;

import java.util.*;
import java.util.stream.IntStream;

public class TestReduce {
    public static void main(String[] args) {

        Person p1 = new Person("Annie",16);
        Person p2 = new Person("Andy", 18);
        Person p3 = new Person("Tom", 24);
        List<Person> list = new LinkedList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        List<Integer> i = new ArrayList<>();
        OptionalInt t = IntStream.rangeClosed(2, 5).reduce((x, y)->x*y);
        System.out.println(t.getAsInt());
        Optional<Person> person = list.stream().reduce((o1, o2) -> o1.age>o2.age? o1:o2);
        int maxAge = list.stream().mapToInt(Person::getAge).reduce(0, (o1,o2)->o1>o2?o1:o2);
        System.out.println(person);
        System.out.println(maxAge);
    }

}


class Person{
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
