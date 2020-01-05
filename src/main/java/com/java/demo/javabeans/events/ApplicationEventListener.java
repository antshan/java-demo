package com.java.demo.javabeans.events;

import java.util.EventListener;

/**
 * 事件监听器基类
 * @param <E> 事件泛型
 */
public interface ApplicationEventListener<E extends ApplicationEvent> extends EventListener {

    void onEvent(E event);
}
