package com.java.demo.sort;

import java.util.stream.Stream;

public class BubbleSort<T extends Comparable> implements Sort<T> {
    @Override
    public void sort(T[] values) {
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if (values[i].compareTo(values[j]) == 1) {
                    T temp = values[i];
                    values[i] = values[j];
                    values[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] integers = Sort.of(2, 4, 3, 7, 1, 6, 5);
        Sort<Integer> sort = new BubbleSort<>();
        sort.sort(integers);

        Stream.of(integers).forEach(System.out::println);
    }
}
