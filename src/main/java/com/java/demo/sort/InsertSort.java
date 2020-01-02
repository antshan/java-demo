package com.java.demo.sort;

import java.util.stream.Stream;

public class InsertSort<T extends Comparable> implements Sort<T> {
    /**
     *
     * @param values
     */
    @Override
    public void sort(T[] values) {
        for (int i =1;i<values.length;i++){
            T t = values[i];
            for (int j= i-1;j>=0;j--){
                if (t.compareTo(values[j]) <0){
                    values[j+1] = values[j];
                    values[j] = t;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] integers = Sort.of(4,1,8,6,9,7,2,3,5);
        Sort<Integer> sort = new InsertSort<>();
        sort.sort(integers);

        Stream.of(integers).forEach(System.out::println);
    }
}
