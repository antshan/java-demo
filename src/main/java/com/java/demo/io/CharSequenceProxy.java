package com.java.demo.io;

public class CharSequenceProxy {

    /**
     * ��̬����
     * �������뱻�����಻���ڱ����в�ι�ϵ
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
