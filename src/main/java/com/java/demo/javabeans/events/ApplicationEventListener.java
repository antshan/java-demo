package com.java.demo.javabeans.events;

import java.util.EventListener;

/**
 * �¼�����������
 * @param <E> �¼�����
 */
public interface ApplicationEventListener<E extends ApplicationEvent> extends EventListener {

    void onEvent(E event);
}
