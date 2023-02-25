package com.wil.practice.lambda;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.junit.Assert.assertEquals;

public class UnaryTest {
    private UnaryOperator<Integer> func1;

    @Test
    public void testUnary() {
        UnaryOperator<Integer> func1 = x -> x * 2;
        UnaryOperator<Integer> func2 = x -> (int)Math.pow(x, 2);
        // the result of func1 serves as 'x' inputted parameter into the func2
        int t1 = func1.andThen(func2).apply(5);
        // the result of func2 serves as 'x' inputted parameter into the func1
        int t2 = func1.compose(func2).apply(5);
        // the result of func1 serves as 'x' inputted parameter into the func2
        int t3 = func2.compose(func1).apply(5);

        // 100
        assertEquals(100, t1, 0);
        // 50
        assertEquals(50, t2, 0);
        // 100
        assertEquals(100, t3, 0);
    }

    @Test
    public void testBiFunc() {
        BiFunction<Integer, Integer, String> biFun = (x, y) -> x +":" + y;
        System.out.println(biFun.apply(3,5));
    }

    @Test
    public void testBiFunc2(){
        BiFunction<Integer, Integer, Integer> biFunc = (x, y) -> x+y;
        Function<Integer, String> up = n->"Result "+n;
        System.out.println(biFunc.andThen(up).apply(3,5));
    }

    @Test
    public void testConsumer() {
        Consumer<Integer> con = Person::sleeping;
        con.accept(7);
    }
}

class Person {
    String name;

    public Person(){
    }

    public Person(String name) {
        this.name=name;
    }

    public static void sleeping(int hrs) {
        System.out.println("sleeping hours: "+hrs+" hrs");
    }
}