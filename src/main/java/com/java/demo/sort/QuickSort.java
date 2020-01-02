package com.java.demo.sort;

import java.util.stream.Stream;

public class QuickSort<T extends Comparable<T>> implements Sort<T> {
    @Override
    public void sort(T[] values) {
        int low = 0;
        int high = values.length - 1;
        sort(values, low, high);
    }

    private void sort(T[] values, int low, int high) {
        if (low < high) {
            int partIndex = partion(values, low, high);
            sort(values, low, partIndex - 1);
            sort(values, partIndex + 1, high);
        }
    }

    private int partion(T[] values, int low, int high) {
        T t = values[high];
        int i = low;
        //让小元素和大元素交换位置
        for (int j = low; j < high; j++) {
            if (t.compareTo(values[j]) > 0) {
                T temp = values[j];
                values[j] = values[i];
                values[i] = temp;
                i++;
            }
        }
        T temp = t;
        values[high] = values[i];
        values[i] = temp;
        return i;
    }

    public static void main(String[] args) {
        Integer[] integers = Sort.of(4, 1, 8, 6, 9, 7, 2, 3, 5);
        Sort<Integer> sort = new QuickSort<>();
        sort.sort(integers);

        Stream.of(integers).forEach(System.out::println);
    }
}
