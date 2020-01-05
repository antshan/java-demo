package com.java.demo.javabeans.events;

/**
 * 事件监听器的注册中心
 */
public interface ApplicationEventListenerRegistry {

    void addApplicationEventListener(ApplicationEventListener<?> applicationEventListener);

    void removeApplicationEventListener(ApplicationEventListener<?> applicationEventListener);

    ApplicationEventListener[] getApplicationEventListeners();

    ApplicationEventListener[] getApplicationEventListeners(Class<? extends ApplicationEvent> type);
}
