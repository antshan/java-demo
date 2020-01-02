package com.java.demo.io;

public class CharSequenceProxy {

    /**
     * 静态代理
     * 代理类与被代理类不存在必须有层次关系
     */
    private final CharSequence proxy;

    public CharSequenceProxy(CharSequence proxy){
        this.proxy = proxy;
    }

    public int length(){
        return proxy.length()+2;
    }

    @Override
    public String toString(){
        return proxy.toString()+"--proxy";
    }

    public static void main(String[] args) {
        CharSequenceProxy proxy = new CharSequenceProxy(new String("abc"));
        System.out.println(proxy.length());
        System.out.println(proxy.toString());
    }
}
