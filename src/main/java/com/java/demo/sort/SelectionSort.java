package com.java.demo.sort;

import java.util.stream.Stream;

public class SelectionSort<T extends Comparable<T>> implements Sort<T> {
    @Override
    public void sort(T[] values) {
        for (int i = 0; i < values.length; i++) {
            int min = i;
            for (int j = i+1; j < values.length; j++) {
                if (values[j].compareTo(values[min]) < 0) {
                    min = j;
                }
            }
            if (i != min) {
                T temp = values[i];
                values[i] = values[min];
                values[min] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] integers = Sort.of(4, 1, 8, 6, 9, 7, 2, 3, 5);
        Sort<Integer> sort = new SelectionSort<>();
        sort.sort(integers);

        Stream.of(integers).forEach(System.out::println);
    }
}
