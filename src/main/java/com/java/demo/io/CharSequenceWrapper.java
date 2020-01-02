package com.java.demo.io;

import java.util.stream.IntStream;

public class CharSequenceWrapper implements CharSequence {

    /**
     * ×°ÊÎÆ÷Ä£Ê½
     */
    private final CharSequence delegate;

    public CharSequenceWrapper(CharSequenceWrapper delegate){
        this.delegate = delegate;
    }

    @Override
    public int length() {
        return delegate.length()*2;
    }

    @Override
    public char charAt(int index) {
        return delegate.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return delegate.subSequence(start,end);
    }

    @Override
    public IntStream chars() {
        return delegate.chars();
    }

    @Override
    public IntStream codePoints() {
        return delegate.codePoints();
    }

    @Override
    public String toString(){
        return delegate.toString();
    }

    public String getDesc(){
        return delegate.toString();
    }
}
