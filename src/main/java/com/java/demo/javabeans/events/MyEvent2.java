package com.java.demo.javabeans.events;

public class MyEvent2 extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MyEvent2(Object source) {
        super(source);
    }
}
