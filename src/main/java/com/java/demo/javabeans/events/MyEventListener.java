package com.java.demo.javabeans.events;

public class MyEventListener implements ApplicationEventListener<MyEvent>{
    @Override
    public void onEvent(MyEvent event) {
        System.out.println("MyEventListener 处理事件 MyEvent"+ event);
    }
}
