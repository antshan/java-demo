package com.java.demo.javabeans.events;

/**
 * �¼���������ע������
 */
public interface ApplicationEventListenerRegistry {

    void addApplicationEventListener(ApplicationEventListener<?> applicationEventListener);

    void removeApplicationEventListener(ApplicationEventListener<?> applicationEventListener);

    ApplicationEventListener[] getApplicationEventListeners();

    ApplicationEventListener[] getApplicationEventListeners(Class<? extends ApplicationEvent> type);
}
