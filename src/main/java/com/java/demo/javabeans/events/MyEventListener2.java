package com.java.demo.javabeans.events;

public class MyEventListener2 implements ApplicationEventListener<MyEvent> {
    @Override
    public void onEvent(MyEvent event) {
        System.out.println("MyEventListener2 �����¼� MyEvent"+ event);
    }
}
