package com.codeyaa;

import javax.swing.table.TableRowSorter;
import java.sql.Connection;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        intStreamTest();
    }

    private static void longStreamTest() {
        long num = 4;
        long asLong = LongStream.iterate(num, i -> --i)
                .limit(num)
                .reduce(1, (current, pre) -> {
                    System.out.printf("%d*%d\n", current, pre);
                    return current * pre;
                });
        System.out.println("asLong = " + asLong);
    }

    private static void intStreamTest() {
        System.out.println((byte) 1);
    }

    private static void doubleStreamTest() {
        Supplier<Stream<Integer>> runnable = () -> Arrays.asList(1, 2).stream();
        Stream<Integer> integerStream = runnable.get().map(row -> row += 3);
        if (1 == 1) {
            integerStream = integerStream.map(row -> row += 2);
        }
        integerStream.forEach(System.out::println);
    }

}
