package com.java.demo.sort;

public interface Sort<T> {

    void sort(T[] values);

    static <T> T[] of(T... values) {
        return values;
    }
}
