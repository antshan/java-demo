package com.java.demo.javabeans.events;

import java.util.EventObject;

/**
 * 事件基类
 */
public class ApplicationEvent extends EventObject {

    private final long timestamp;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ApplicationEvent{" +
                "timestamp=" + timestamp +
                ", source=" + source +
                '}';
    }
}
